package hard;

/*
        Along a long library corridor, there is a line of seats and decorative plants. You are given a
    0-indexed string corridor of length n consisting of letters 'S' and 'P' where each 'S' represents
    a seat and each 'P' represents a plant.
        One room divider has already been installed to the left of index 0, and another to the right of
    index n - 1. Additional room dividers can be installed. For each position between indices i - 1
    and i (1 <= i <= n - 1), at most one divider can be installed.
        Divide the corridor into non-overlapping sections, where each section has exactly two seats with
    any number of plants. There may be multiple ways to perform the division. Two ways are different
    if there is a position with a room divider installed in the first way but not in the second way.
        Return the number of ways to divide the corridor. Since the answer may be very large, return
    it modulo 109 + 7. If there is no way, return 0.
 */

public class Exc06_NumberOfWaysToDivideLongCorridor {
    public int numberOfWays(String corridor) {
        final int MOD = 1_000_000_007;
        int n = corridor.length();
        int seatCount = 0;

        // Count total seats
        for (char ch : corridor.toCharArray()) {
            if (ch == 'S') {
                seatCount++;
            }
        }

        // If the total number of seats is not even or less than 2, no way to divide
        if (seatCount % 2 != 0 || seatCount < 2) {
            return 0;
        }

        int ways = 1;
        int seatSeen = 0;
        int plantsBetween = 0;

        for (char ch : corridor.toCharArray()) {
            if (ch == 'S') {
                seatSeen++;
                if (seatSeen > 2 && seatSeen % 2 == 1) {
                    // Multiply the ways by the number of plants between seats
                    ways = (int)((long)ways * (plantsBetween + 1) % MOD);
                    plantsBetween = 0;
                }
            } else if (seatSeen >= 2 && seatSeen % 2 == 0) {
                plantsBetween++;
            }
        }

        return ways;
    }

    public static void main(String[] args) {
        Exc06_NumberOfWaysToDivideLongCorridor solution = new Exc06_NumberOfWaysToDivideLongCorridor();

        String corridor1 = "SSPSSP";
        System.out.println("Number of ways to divide: " + solution.numberOfWays(corridor1)); // Expected: 1

        String corridor2 = "PPSPSP";
        System.out.println("Number of ways to divide: " + solution.numberOfWays(corridor2)); // Expected: 0

        String corridor3 = "SSPSSPSSP";
        System.out.println("Number of ways to divide: " + solution.numberOfWays(corridor3)); // Expected: 2
    }
}
