package hard;
/*
    You are given a 0-indexed array of integers nums of length n, and two positive integers k and dist.
    The cost of an array is the value of its first element. For example, the cost of [1,2,3] is 1 while
    the cost of [3,4,1] is 3.
    You need to divide nums into k disjoint contiguous subArrays, such that the difference between the
    starting index of the second subarray and the starting index of the kth subarray should be less than
    or equal to dist. In other words, if you divide nums into the subarrays nums[0..(i1 - 1)],
    nums[i1..(i2 - 1)], ..., nums[ik-1..(n - 1)], then ik-1 - i1 <= dist.
    Return the minimum possible sum of the cost of these subArrays.

    Example 1:
    Input: nums = [1,3,2,6,4,2], k = 3, dist = 3
    Output: 5
    Explanation: The best possible way to divide nums into 3 subarrays is: [1,3], [2,6,4], and [2].
    This choice is valid because ik-1 - i1 is 5 - 2 = 3 which is equal to dist. The total cost is
    nums[0] + nums[2] + nums[5] which is 1 + 2 + 2 = 5.
    It can be shown that there is no possible way to divide nums into 3 subarrays at a cost lower than 5.
 */

import java.util.Comparator;
import java.util.TreeSet;

public class Exc10_DivideArrayIntoSubArraysWithMinCost_2 {
    public long minimumCost(int[] nums, int k, int dist) {
        //here i, j denotes index
        //if both values are same compare index, else compare value
        Comparator<Integer> comparator = (i, j)->nums[i]==nums[j]? Integer.compare(i, j): Integer.compare(nums[i], nums[j]);

        //tree remove takes O(log n) time, heap takes O(n)
        //Set uses index so there is no duplicates, comparator uses nums[] array
        //both sets acts as minheap
        TreeSet<Integer> used = new TreeSet<>(comparator), //minimum value index stored here
                unused = new TreeSet<>(comparator); //if unused values can be added here

        //k[0] subarray = nums[0],..... can be only one element or more
        //so pick only k-1 subarray
        k--;

        //sum will hold all the subarray starting value except nums[0]
        long sum = 0, n = nums.length, answer = Long.MAX_VALUE;

        //add all the values between 1....dist+1 into set
        //because our max distance between |1-st subarray index to k-th subarray index| <= dist
        for(int currentWindow = 1; currentWindow<=Math.min(dist+1, n-1); currentWindow++) {
            sum+=nums[currentWindow];
            used.add(currentWindow);
        }

        //added index between 1,....dist+1 exceeds max subarray size
        //remove all large value index from used and add to unused
        while(used.size()>k) {
            int largeValueIndex = used.pollLast();
            sum-=nums[largeValueIndex];
            unused.add(largeValueIndex);
        }

        //now all the indexes in used set satifies `dist` condition
        //take minimum sum value till `k` sub-array
        answer = Math.min(sum, answer);

        //now try to slide window and check, is there a optimal value better than before
        //`prevWindow` starts with 1, remove `prevWindow` and add dist+2 index because till `dist+1` taken before
        for(int currentWindow = dist+2, prevWindow = 1; currentWindow<n; currentWindow++, prevWindow++) {

            unused.add(currentWindow); //this can be a smaller value, we utilize later

            if(used.contains(prevWindow)) { //if `prevWindow` is available in current used
                sum-=nums[prevWindow];
                used.remove(prevWindow);

                //now used.size()<k so add element
                //take smallest element from unused
                int smallValueIndex = unused.pollFirst();
                sum+=nums[smallValueIndex];
                used.add(smallValueIndex);
            } else {
                unused.remove(prevWindow); //`prevWindow` is not available in current used

                //but currently added `currentWindow` can be smallest
                //check if current `currentWindow` can be added to used
                if(nums[used.last()]>nums[unused.first()]) {
                    //currently added `currentWindow` is small
                    //remove highest value from used and add smallest value from unused
                    int largeValueIndex = used.pollLast();
                    sum-=nums[largeValueIndex];
                    unused.add(largeValueIndex);

                    int smallValueIndex = unused.pollFirst();
                    sum+=nums[smallValueIndex];
                    used.add(smallValueIndex);
                }
            }
            answer = Math.min(answer, sum);
        }
        return nums[0]+answer;
    }
}
