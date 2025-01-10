package madium;
/*
    You are given an integer array coins representing coins of different denominations and an integer
    amount representing a total amount of money. Return the fewest number of coins that you need to
    make up that amount. If that amount of money cannot be made up by any combination of the coins,
    return -1. You may assume that you have an infinite number of each kind of coin.

    Example 1:
    Input: coins = [1,2,5], amount = 11
    Output: 3
    Explanation: 11 = 5 + 5 + 1
 */

import java.util.Arrays;

public class Exc13_CoinChange {
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;

        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1); // Fill with a large value
        dp[0] = 0; // Base case

        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        Exc13_CoinChange solution = new Exc13_CoinChange();

        int[] coins1 = {1, 2, 5};
        int amount1 = 11;
        System.out.println("Minimum coins needed: " + solution.coinChange(coins1, amount1)); // Output: 3

        int[] coins2 = {2};
        int amount2 = 3;
        System.out.println("Minimum coins needed: " + solution.coinChange(coins2, amount2)); // Output: -1

        int[] coins3 = {186, 419, 83, 408};
        int amount3 = 6249;
        System.out.println("Minimum coins needed: " + solution.coinChange(coins3, amount3)); // Large input test case

        int[] coins4 = {1};
        int amount4 = 0;
        System.out.println("Minimum coins needed: " + solution.coinChange(coins4, amount4)); // Edge case, output: 0

        int[] coins5 = {1, 2, 5};
        int amount5 = 100;
        System.out.println("Minimum coins needed: " + solution.coinChange(coins5, amount5)); // High amount test case
    }
}
