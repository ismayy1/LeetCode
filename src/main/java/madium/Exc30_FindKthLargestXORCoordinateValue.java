package madium;
/*
    You are given a 2D matrix of size m x n, consisting of non-negative integers. You are also given
    an integer k.
    The value of coordinate (a, b) of the matrix is the XOR of all matrix[i][j] where 0 <= i <= a < m
    and 0 <= j <= b < n (0-indexed).
    Find the kth largest value (1-indexed) of all the coordinates of matrix.

    Example 1:
    Input: matrix = [[5,2],[1,6]], k = 1
    Output: 7
    Explanation: The value of coordinate (0,1) is 5 XOR 2 = 7, which is the largest value.
 */

import java.util.Arrays;
import java.util.PriorityQueue;

public class Exc30_FindKthLargestXORCoordinateValue {
    public int kthLargestValue(int[][] matrix, int k) {
        int M = matrix.length, N = matrix[0].length;
        int[][] dp = new int[M][N];

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {

                int x = (i - 1 < 0 ? 0 : dp[i - 1][j]);
                int y = (j - 1 < 0 ? 0 : dp[i][j - 1]);
                int z = (i - 1 < 0 || j - 1 < 0 ? 0 : dp[i - 1][j - 1]);

                dp[i][j] = (x ^ y ^ z ^ matrix[i][j]);

                pq.add(dp[i][j]);
                if (pq.size() > k) pq.poll();
            }
        }
        return pq.poll();
    }

    // Main method for testing
    public static void main(String[] args) {
        Exc30_FindKthLargestXORCoordinateValue solution = new Exc30_FindKthLargestXORCoordinateValue();

        int[][] points = {{1, 3}, {-2, 2}, {5, 8}, {0, 1}};
        int k = 2;

        int result = solution.kthLargestValue(points, k);

        // Print the result
        System.out.println(result);
        // Example Output: [[-2, 2], [0, 1]] (or similar valid arrangement)
    }
}
