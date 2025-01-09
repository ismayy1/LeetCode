package hard;
/*
    You are given an integer array nums and an integer k. Split the array into some number of
    non-empty subArrays. The cost of a split is the sum of the importance value of each subarray in
    the split. Let trimmed(subarray) be the version of the subarray where all numbers which appear
    only once are removed.
    For example, trimmed([3,1,2,4,3,4]) = [3,4,3,4].
    The importance value of a subarray is k + trimmed(subarray).length.
    For example, if a subarray is [1,2,3,3,3,4,4], then trimmed([1,2,3,3,3,4,4]) = [3,3,3,4,4]. The
    importance value of this subarray will be k + 5. Return the minimum possible cost of a split
    of nums. A subarray is a contiguous non-empty sequence of elements within an array.

    Example 1:
    Input: nums = [1,2,1,2,1,3,3], k = 2
    Output: 8
    Explanation: We split nums to have two subArrays: [1,2], [1,2,1,3,3].
    The importance value of [1,2] is 2 + (0) = 2.
    The importance value of [1,2,1,3,3] is 2 + (2 + 2) = 6.
    The cost of the split is 2 + 6 = 8. It can be shown that this is the minimum possible cost among all the
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Exc11_MinCostToSplitArray {
    public int minCost(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0; // Base case: no cost for an empty split

        for (int i = 1; i <= n; i++) {
            Map<Integer, Integer> freq = new HashMap<>();
            int trimmedLength = 0;

            for (int j = i; j > 0; j--) {
                int num = nums[j - 1];
                freq.put(num, freq.getOrDefault(num, 0) + 1);

                if (freq.get(num) == 2) {
                    trimmedLength += 2; // Add the second occurrence
                } else if (freq.get(num) > 2) {
                    trimmedLength++; // Add subsequent occurrences
                }

                dp[i] = Math.min(dp[i], dp[j - 1] + k + trimmedLength);
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        Exc11_MinCostToSplitArray solution = new Exc11_MinCostToSplitArray();

        int[] nums1 = {1, 2, 3, 3, 4, 4, 5};
        int k1 = 5;
        System.out.println("Minimum cost: " + solution.minCost(nums1, k1)); // Example test case

        int[] nums2 = {3, 1, 2, 3, 3, 4, 4};
        int k2 = 3;
        System.out.println("Minimum cost: " + solution.minCost(nums2, k2)); // Example test case

        int[] nums3 = {1, 1, 1, 1};
        int k3 = 2;
        System.out.println("Minimum cost: " + solution.minCost(nums3, k3)); // All numbers identical

        int[] nums4 = {5, 5, 5, 6, 6};
        int k4 = 4;
        System.out.println("Minimum cost: " + solution.minCost(nums4, k4)); // Test case with repetitions
    }
}
