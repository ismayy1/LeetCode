package madium;
/*
    You are given an integer array coins representing coins of different denominations and an integer
    amount representing a total amount of money.
    Return the number of combinations that make up that amount. If that amount of money cannot be
    made up by any combination of the coins, return 0.
    You may assume that you have an infinite number of each kind of coin.
    The answer is guaranteed to fit into a signed 32-bit integer.

    Example 1:
    Input: amount = 5, coins = [1,2,5]
    Output: 4
    Explanation: there are four ways to make up the amount:
    5=5
    5=2+2+1
    5=2+1+1+1
    5=1+1+1+1+1
 */

public class Exc15_CoinChange_2 {

    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1; // Base case: one way to make amount 0 (using no coins)

        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }

        return dp[amount];
    }

    public static void main(String[] args) {
        Exc15_CoinChange_2 solution = new Exc15_CoinChange_2();

        int[] coins1 = {1, 2, 5};
        int amount1 = 5;
        System.out.println("Number of combinations: " + solution.change(amount1, coins1)); // Expected output: 4

        int[] coins2 = {2};
        int amount2 = 3;
        System.out.println("Number of combinations: " + solution.change(amount2, coins2)); // Expected output: 0

        int[] coins3 = {10};
        int amount3 = 10;
        System.out.println("Number of combinations: " + solution.change(amount3, coins3)); // Expected output: 1
    }
}
