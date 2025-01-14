package madium;
/*
    You are given two 0-indexed integer permutations A and B of length n.
    A prefix common array of A and B is an array C such that C[i] is equal to the count of numbers
    that are present at or before the index i in both A and B.
    Return the prefix common array of A and B. A sequence of n integers is called a permutation if
    it contains all integers from 1 to n exactly once.

    Example 1:
    Input: A = [1,3,2,4], B = [3,1,2,4]
    Output: [0,2,3,4]
    Explanation: At i = 0: no number is common, so C[0] = 0.
    At i = 1: 1 and 3 are common in A and B, so C[1] = 2.
    At i = 2: 1, 2, and 3 are common in A and B, so C[2] = 3.
    At i = 3: 1, 2, 3, and 4 are common in A and B, so C[3] = 4.
 */

import java.util.HashSet;
import java.util.Set;

public class Exc27_FindPrefixCommonArrayOfTwoArrays {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length;
        int[] C = new int[n];
        Set<Integer> seen = new HashSet<>();
        int commonCount = 0;

        for (int i = 0; i < n; i++) {
            // Add current elements of A and B to the set
            if (seen.contains(A[i])) {
                commonCount++;
            } else {
                seen.add(A[i]);
            }

            if (seen.contains(B[i])) {
                commonCount++;
            } else {
                seen.add(B[i]);
            }

            // Store the current common count in C
            C[i] = commonCount;
        }

        return C;
    }

    // Main method for testing
    public static void main(String[] args) {
        Exc27_FindPrefixCommonArrayOfTwoArrays solution = new Exc27_FindPrefixCommonArrayOfTwoArrays();

        int[] A = {1, 3, 2, 4};
        int[] B = {3, 1, 2, 4};

        int[] result = solution.findThePrefixCommonArray(A, B);

        // Print the result
        for (int num : result) {
            System.out.print(num + " ");
        }
        // Expected output: 0 2 3 4
    }
}
