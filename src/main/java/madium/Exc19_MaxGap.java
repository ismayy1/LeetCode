package madium;
/*
    Given an integer array nums, return the maximum difference between two successive elements in
    its sorted form. If the array contains less than two elements, return 0.
    You must write an algorithm that runs in linear time and uses linear extra space.

    Example 1:
    Input: nums = [3,6,9,1]
    Output: 3
    Explanation: The sorted form of the array is [1,3,6,9], either (3,6) or (6,9) has the maximum
    difference 3.

    Example 2:
    Input: nums = [10]
    Output: 0
    Explanation: The array contains less than 2 elements, therefore return 0.

    Constraints:
    1 <= nums.length <= 105
    0 <= nums[i] <= 109
 */

import java.util.Arrays;

public class Exc19_MaxGap {
    public int maximumGap(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }

        // Find the maximum and minimum values in nums
        int max = Arrays.stream(nums).max().getAsInt();
        int min = Arrays.stream(nums).min().getAsInt();

        // Calculate bucket size and count
        int n = nums.length;
        int bucketSize = Math.max(1, (max - min) / (n - 1));
        int bucketCount = (max - min) / bucketSize + 1;

        // Create buckets to store the min and max values
        int[] bucketMin = new int[bucketCount];
        int[] bucketMax = new int[bucketCount];
        Arrays.fill(bucketMin, Integer.MAX_VALUE);
        Arrays.fill(bucketMax, Integer.MIN_VALUE);

        // Place each number into a bucket
        for (int num : nums) {
            int bucketIndex = (num - min) / bucketSize;
            bucketMin[bucketIndex] = Math.min(bucketMin[bucketIndex], num);
            bucketMax[bucketIndex] = Math.max(bucketMax[bucketIndex], num);
        }

        // Calculate the maximum gap
        int maxGap = 0;
        int previousMax = min;
        for (int i = 0; i < bucketCount; i++) {
            if (bucketMin[i] == Integer.MAX_VALUE) {
                // Skip empty buckets
                continue;
            }
            // Update max gap
            maxGap = Math.max(maxGap, bucketMin[i] - previousMax);
            previousMax = bucketMax[i];
        }

        return maxGap;
    }

    public static void main(String[] args) {
        Exc19_MaxGap solution = new Exc19_MaxGap();

        int[] nums1 = {3, 6, 9, 1};
        System.out.println("Maximum Gap: " + solution.maximumGap(nums1)); // Output: 3

        int[] nums2 = {10};
        System.out.println("Maximum Gap: " + solution.maximumGap(nums2)); // Output: 0

        int[] nums3 = {1, 10000000};
        System.out.println("Maximum Gap: " + solution.maximumGap(nums3)); // Output: 9999999
    }
}
