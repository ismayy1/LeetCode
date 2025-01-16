package hard;
/*
    An array is squareful if the sum of every pair of adjacent elements is a perfect square.
    Given an integer array nums, return the number of permutations of nums that are squareful. Two
    permutations perm1 and perm2 are different if there is some index i such that perm1[i] != perm2[i].

    Example 1:
    Input: nums = [1,17,8]
    Output: 2
    Explanation: [1,8,17] and [17,8,1] are the valid permutations.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Exc22_NumberOfSquarefulArrays {
    public int numSquarefulPerms(int[] nums) {
        Arrays.sort(nums); // Sort to handle duplicates
        boolean[] visited = new boolean[nums.length];
        return backtrack(nums, new ArrayList<>(), visited);
    }

    private int backtrack(int[] nums, List<Integer> current, boolean[] visited) {
        if (current.size() == nums.length) {
            return 1; // Found a valid permutation
        }

        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            // Skip if already visited or if it's a duplicate and the previous duplicate wasn't used
            if (visited[i] || (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1])) {
                continue;
            }

            // Check if the current number forms a valid pair with the previous number
            if (!current.isEmpty() && !isPerfectSquare(current.get(current.size() - 1) + nums[i])) {
                continue;
            }

            // Choose
            visited[i] = true;
            current.add(nums[i]);

            // Explore
            count += backtrack(nums, current, visited);

            // Unchoose
            visited[i] = false;
            current.remove(current.size() - 1);
        }

        return count;
    }

    private boolean isPerfectSquare(int x) {
        int sqrt = (int) Math.sqrt(x);
        return sqrt * sqrt == x;
    }

    public static void main(String[] args) {
        Exc22_NumberOfSquarefulArrays solution = new Exc22_NumberOfSquarefulArrays();

        // Example 1
        int[] nums1 = {1, 17, 8};
        System.out.println(solution.numSquarefulPerms(nums1)); // Output: 2

        // Example 2
        int[] nums2 = {2, 2, 2};
        System.out.println(solution.numSquarefulPerms(nums2)); // Output: 1
    }
}
