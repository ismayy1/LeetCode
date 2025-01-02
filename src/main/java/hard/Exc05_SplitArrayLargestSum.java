package hard;

/*
    Given an integer array nums and an integer k, split nums into k non-empty
    subArrays such that the largest sum of any subarray is minimized. Return the minimized
    largest sum of the split. A subarray is a contiguous part of the array.

    Example 1:
    Input: nums = [7,2,5,10,8], k = 2
    Output: 18
    Explanation: There are four ways to split nums into two subarrays.
    The best way is to split it into [7,2,5] and [10,8], where the largest sum among the two subarrays is only 18.

    Example 2:
    Input: nums = [1,2,3,4,5], k = 2
    Output: 9
    Explanation: There are four ways to split nums into two subarrays.
    The best way is to split it into [1,2,3] and [4,5], where the largest sum among the two subarrays is only 9.

    Constraints:
    1 <= nums.length <= 1000
    0 <= nums[i] <= 106
    1 <= k <= min(50, nums.length)
 */

public class Exc05_SplitArrayLargestSum {

    public int splitArray(int[] nums, int k) {
        int left = 0, right = 0;
        for (int num : nums) {
            left = Math.max(left, num); // Minimum possible largest sum
            right += num; // Maximum possible largest sum
        }

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (canSplit(nums, k, mid)) {
                right = mid; // Try for a smaller largest sum
            } else {
                left = mid + 1; // Increase the largest sum
            }
        }
        return left;
    }

    private boolean canSplit(int[] nums, int k, int maxSum) {
        int currentSum = 0;
        int subarrays = 1;

        for (int num : nums) {
            if (currentSum + num > maxSum) {
                subarrays++;
                currentSum = num;
                if (subarrays > k) {
                    return false;
                }
            } else {
                currentSum += num;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Exc05_SplitArrayLargestSum solution = new Exc05_SplitArrayLargestSum();

        int[] nums1 = {7, 2, 5, 10, 8};
        int k1 = 2;
        System.out.println("Minimized largest sum: " + solution.splitArray(nums1, k1)); // Expected: 18

        int[] nums2 = {1, 2, 3, 4, 5};
        int k2 = 2;
        System.out.println("Minimized largest sum: " + solution.splitArray(nums2, k2)); // Expected: 9

        int[] nums3 = {1, 4, 4};
        int k3 = 3;
        System.out.println("Minimized largest sum: " + solution.splitArray(nums3, k3)); // Expected: 4
    }
}
