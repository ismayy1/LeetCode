package hard;
/*
    You are given an integer array nums and two integers indexDiff and valueDiff.
    Find a pair of indices (i, j) such that:
    i != j,
    abs(i - j) <= indexDiff.
    abs(nums[i] - nums[j]) <= valueDiff, and
    Return true if such pair exists or false otherwise.

    Example 1:
    Input: nums = [1,2,3,1], indexDiff = 3, valueDiff = 0
    Output: true
    Explanation: We can choose (i, j) = (0, 3).
    We satisfy the three conditions:
    i != j --> 0 != 3
    abs(i - j) <= indexDiff --> abs(0 - 3) <= 3
    abs(nums[i] - nums[j]) <= valueDiff --> abs(1 - 1) <= 0
 */

import java.util.TreeSet;

public class Exc16_ContainsDuplicate_3 {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        if (nums == null || nums.length < 2 || indexDiff <= 0 || valueDiff < 0) {
            return false;
        }

        TreeSet<Long> set = new TreeSet<>();

        for (int i = 0; i < nums.length; i++) {
            // Check for a value within range
            Long floor = set.floor((long) nums[i] + valueDiff);
            Long ceiling = set.ceiling((long) nums[i] - valueDiff);

            if ((floor != null && floor >= nums[i]) || (ceiling != null && ceiling <= nums[i])) {
                return true;
            }

            // Add current element to the set
            set.add((long) nums[i]);

            // Maintain the sliding window of size indexDiff
            if (i >= indexDiff) {
                set.remove((long) nums[i - indexDiff]);
            }
        }

        return false;
    }
}
