package hard;
/*
    There are n piles of coins on a table. Each pile consists of a positive number of coins of assorted
    denominations.
    In one move, you can choose any coin on top of any pile, remove it, and add it to your wallet.
    Given a list piles, where piles[i] is a list of integers denoting the composition of
    the ith pile from top to bottom, and a positive integer k, return the maximum total value of coins
    you can have in your wallet if you choose exactly k coins optimally.
 */

import java.util.Arrays;
import java.util.List;

public class Exc12_MaxValueOfKCoinsFromPiles {
    public int maxValueOfCoins(List<List<Integer>> piles, int k) {
        int n = piles.size();
        int[][] dp = new int[n + 1][k + 1];

        for (int i = 1; i <= n; i++) {
            List<Integer> pile = piles.get(i - 1);
            int m = pile.size();

            int[] prefixSum = new int[m + 1];
            for (int j = 0; j < m; j++) {
                prefixSum[j + 1] = prefixSum[j] + pile.get(j);
            }

            for (int coins = 0; coins <= k; coins++) {
                dp[i][coins] = dp[i - 1][coins];
                for (int x = 1; x <= Math.min(coins, m); x++) {
                    dp[i][coins] = Math.max(dp[i][coins], dp[i - 1][coins - x] + prefixSum[x]);
                }
            }
        }
        return dp[n][k];
    }

    public static void main(String[] args) {
        Exc12_MaxValueOfKCoinsFromPiles solution = new Exc12_MaxValueOfKCoinsFromPiles();

        List<List<Integer>> piles1 = Arrays.asList(
                Arrays.asList(1, 100, 3),
                Arrays.asList(7, 8, 9)
        );
        System.out.println("Maximum value: " + solution.maxValueOfCoins(piles1, 2)); // Expected output: 101

        List<List<Integer>> piles2 = Arrays.asList(
                Arrays.asList(100, 200, 300),
                Arrays.asList(50, 50),
                Arrays.asList(1, 2, 3, 4, 5)
        );
        System.out.println("Maximum value: " + solution.maxValueOfCoins(piles2, 5)); // Expected output: 550

        List<List<Integer>> piles3 = Arrays.asList(
                Arrays.asList(1),
                Arrays.asList(2),
                Arrays.asList(3)
        );
        System.out.println("Maximum value: " + solution.maxValueOfCoins(piles3, 3)); // Expected output: 6
    }
}
