package hard;
/*
    Given a wooden stick of length n units. The stick is labelled from 0 to n. For example, a stick
    of length 6 is labelled as follows: ->  0--1--2--3--4--5--6
    Given an integer array cuts where cuts[i] denotes a position you should perform a cut at.
    You should perform the cuts in order, you can change the order of the cuts as you wish.
    The cost of one cut is the length of the stick to be cut, the total cost is the sum of costs of
    all cuts. When you cut a stick, it will be split into two smaller sticks (i.e. the sum of their
    lengths is the length of the stick before the cut). Please refer to the first example for a better
    explanation. Return the minimum total cost of the cuts.
 */

import java.util.Arrays;

public class Exc09_MinCostToCutStick {
    public int minCost(int n, int[] cuts) {
        int m = cuts.length;
        int[] newCuts = new int[m + 2];
        System.arraycopy(cuts, 0, newCuts, 1, m);
        newCuts[0] = 0;
        newCuts[m + 1] = n;
        Arrays.sort(newCuts);

        int[][] dp = new int[m + 2][m + 2];

        for (int length = 2; length <= m + 1; length++) {
            for (int i = 0; i <= m + 1 - length; i++) {
                int j = i + length;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j] + newCuts[j] - newCuts[i]);
                }
            }
        }
        return dp[0][m + 1];
    }

    public static void main(String[] args) {
        Exc09_MinCostToCutStick solution = new Exc09_MinCostToCutStick();

        int n1 = 7;
        int[] cuts1 = {1, 3, 4, 5};
        System.out.println("Minimum cost for n=7 and cuts=[1, 3, 4, 5]: " + solution.minCost(n1, cuts1)); // Expected: 16

        int n2 = 9;
        int[] cuts2 = {5, 6, 1, 4, 2};
        System.out.println("Minimum cost for n=9 and cuts=[5, 6, 1, 4, 2]: " + solution.minCost(n2, cuts2)); // Expected: 22
    }
}
