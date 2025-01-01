package easy;

/*
    Given an array of integers nums and an integer target, return indices of the two
    numbers such that they add up to target.

    You may assume that each input would have exactly one solution,
    and you may not use the same element twice.

    You can return the answer in any order.

    Example 1:

    Input: nums = [2,7,11,15], target = 9
    Output: [0,1]
    Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
    Example 2:

    Input: nums = [3,2,4], target = 6
    Output: [1,2]
    Example 3:

    Input: nums = [3,3], target = 6
    Output: [0,1]
 */

import java.util.Arrays;

public class Exc02_TwoSum {
    public int[] twoSum(int[] nums, int target) {

        int[] output = new int[0];
        int sum = 0;

        for (int a : nums) {
            sum = a + nums[a+1];
            if (sum == target) {
                output[0] = a;
                output[1] = nums[a+1];
            }
            return output;
        }
        return null;
    }

    public static void main(String[] args) {
        int[] nums = new int[5];
        nums[0] = 2;
        nums[1] = 3;
        nums[2] = 5;
        nums[3] = 6;
        nums[4] = 8;

        Exc02_TwoSum exc02TwoSum = new Exc02_TwoSum();
        System.out.println(Arrays.toString(exc02TwoSum.twoSum(nums, 11)));

    }
}
