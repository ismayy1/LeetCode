package madium;
/*
    You have planned some train traveling one year in advance.
    The days of the year in which you will travel are given as an integer array days.
    Each day is an integer from 1 to 365.

    Train tickets are sold in three different ways:

    a 1-day pass is sold for costs[0] dollars,
    a 7-day pass is sold for costs[1] dollars, and
    a 30-day pass is sold for costs[2] dollars.
    The passes allow that many days of consecutive travel.

    For example, if we get a 7-day pass on day 2, then we can travel for 7 days: 2, 3, 4, 5, 6, 7, and 8.
    Return the minimum number of dollars you need to travel every day in the given list of days.
 */

import java.util.HashSet;
import java.util.Set;

public class Exc03_minimumCostForTickets {

    public int mincostTickets(int[] days, int[] costs) {
        // Create a set of travel days for quick lookup
        Set<Integer> travelDays = new HashSet<>();
        for (int day : days) {
            travelDays.add(day);
        }

        // Create a dp array to store the minimum cost for each day
        int[] dp = new int[366]; // For days 1 to 365

        for (int i = 1; i <= 365; i++) {
            if (!travelDays.contains(i)) {
                dp[i] = dp[i - 1]; // If no travel, cost remains the same
            } else {
                int cost1Day = dp[i - 1] + costs[0]; // Cost of a 1-day pass
                int cost7Day = dp[Math.max(0, i - 7)] + costs[1]; // Cost of a 7-day pass
                int cost30Day = dp[Math.max(0, i - 30)] + costs[2]; // Cost of a 30-day pass
                dp[i] = Math.min(cost1Day, Math.min(cost7Day, cost30Day));
            }
        }
        return dp[365]; // Minimum cost to cover all days in the year
    }

    public static void main(String[] args) {
        Exc03_minimumCostForTickets solution = new Exc03_minimumCostForTickets();

        int[] days = {1, 4, 6, 7, 8, 20};
        int[] costs = {2, 7, 15};

        System.out.println("Minimum cost: " + solution.mincostTickets(days, costs));

        int[] days2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 30, 31};
        int[] costs2 = {2, 7, 15};

        System.out.println("Minimum cost: " + solution.mincostTickets(days2, costs2));
    }
}
