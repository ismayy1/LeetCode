package madium;
/*
    Given an integer array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
    You may assume the input array always has a valid answer.

    Example 1:
    Input: nums = [1,5,1,1,6,4]
    Output: [1,6,1,5,1,4]
    Explanation: [1,4,1,5,1,6] is also accepted.
 */

import java.util.Arrays;

public class Exc28_WiggleSort_2 {
    public void wiggleSort(int[] nums) {
        int n = nums.length;

        // Step 1: Sort the array
        Arrays.sort(nums);

        // Step 2: Create a new array to store the result
        int[] sorted = nums.clone();

        // Step 3: Fill the array from the end for wiggle order
        int smallIndex = (n - 1) / 2; // Middle index for smaller numbers
        int largeIndex = n - 1;       // Last index for larger numbers

        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                nums[i] = sorted[smallIndex--]; // Take from smaller half
            } else {
                nums[i] = sorted[largeIndex--]; // Take from larger half
            }
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        Exc28_WiggleSort_2 solution = new Exc28_WiggleSort_2();

        int[] nums = {1, 5, 1, 1, 6, 4};
        solution.wiggleSort(nums);

        // Print the result
        System.out.println(Arrays.toString(nums));
        // Example Output: [1, 6, 1, 5, 1, 4] (or similar valid arrangement)
    }
}
