package madium;
/*
    You are given two 0-indexed arrays, nums1 and nums2, consisting of non-negative integers. There
    exists another array, nums3, which contains the bitwise XOR of all pairings of integers between
    nums1 and nums2 (every integer in nums1 is paired with every integer in nums2 exactly once).
    Return the bitwise XOR of all integers in nums3.

    Example 1:
    Input: nums1 = [2,1,3], nums2 = [10,2,5,0]
    Output: 13
    Explanation:
    A possible nums3 array is [8,0,7,2,11,3,4,1,9,1,6,3].
    The bitwise XOR of all these numbers is 13, so we return 13.
 */

public class Exc33_BitwiseXORofAllPairings {
    public int xorAllNums(int[] nums1, int[] nums2) {
        int xor1 = 0, xor2 = 0;

        // XOR of all elements in nums1
        for (int num : nums1) {
            xor1 ^= num;
        }

        // XOR of all elements in nums2
        for (int num : nums2) {
            xor2 ^= num;
        }

        int n1 = nums1.length, n2 = nums2.length;
        int result = 0;

        // Include xor1 if nums2.length is odd
        if (n2 % 2 != 0) {
            result ^= xor1;
        }

        // Include xor2 if nums1.length is odd
        if (n1 % 2 != 0) {
            result ^= xor2;
        }

        return result;
    }

    public static void main(String[] args) {
        Exc33_BitwiseXORofAllPairings solution = new Exc33_BitwiseXORofAllPairings();

        // Test cases
        int[] nums1 = {2, 1, 3};
        int[] nums2 = {10, 2, 5, 0};
        System.out.println(solution.xorAllNums(nums1, nums2)); // Output: 13

        nums1 = new int[]{1, 2};
        nums2 = new int[]{3, 4};
        System.out.println(solution.xorAllNums(nums1, nums2)); // Output: 0
    }
}
