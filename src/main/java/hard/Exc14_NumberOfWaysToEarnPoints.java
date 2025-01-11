package hard;
/*
    There is a test that has n types of questions. You are given an integer target and a 0-indexed
    2D integer array types where types[i] = [counti, marksi] indicates that there are counti questions
    of the ith type, and each one of them is worth marksi points.
    Return the number of ways you can earn exactly target points in the exam. Since the answer may be
    too large, return it modulo 109 + 7.
    Note that questions of the same type are indistinguishable.
    For example, if there are 3 questions of the same type, then solving the 1st and 2nd questions is
    the same as solving the 1st and 3rd questions, or the 2nd and 3rd questions.

    Example 1:
    Input: target = 6, types = [[6,1],[3,2],[2,3]]
    Output: 7
    Explanation: You can earn 6 points in one of the seven ways:
    - Solve 6 questions of the 0th type: 1 + 1 + 1 + 1 + 1 + 1 = 6
    - Solve 4 questions of the 0th type and 1 question of the 1st type: 1 + 1 + 1 + 1 + 2 = 6
    - Solve 2 questions of the 0th type and 2 questions of the 1st type: 1 + 1 + 2 + 2 = 6
    - Solve 3 questions of the 0th type and 1 question of the 2nd type: 1 + 1 + 1 + 3 = 6
    - Solve 1 question of the 0th type, 1 question of the 1st type and 1 question of the 2nd type: 1 + 2 + 3 = 6
    - Solve 3 questions of the 1st type: 2 + 2 + 2 = 6
    - Solve 2 questions of the 2nd type: 3 + 3 = 6
 */

public class Exc14_NumberOfWaysToEarnPoints {
    private static final int MOD = 1_000_000_007;

    public int waysToReachTarget(int target, int[][] types) {
        int n = types.length;
        int[][] dp = new int[n + 1][target + 1];
        dp[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            int count = types[i - 1][0];
            int marks = types[i - 1][1];

            for (int t = 0; t <= target; t++) {
                dp[i][t] = dp[i - 1][t];
                for (int k = 1; k <= count; k++) {
                    if (t >= k * marks) {
                        dp[i][t] = (dp[i][t] + dp[i - 1][t - k * marks]) % MOD;
                    } else {
                        break;
                    }
                }
            }
        }
        return dp[n][target];
    }

    public static void main(String[] args) {
        Exc14_NumberOfWaysToEarnPoints solution = new Exc14_NumberOfWaysToEarnPoints();

        // Test cases
        System.out.println(solution.waysToReachTarget(5, new int[][]{{2, 1}, {2, 2}, {1, 3}})); // Output: 3
        System.out.println(solution.waysToReachTarget(3, new int[][]{{1, 3}, {1, 2}, {1, 1}})); // Output: 1
        System.out.println(solution.waysToReachTarget(7, new int[][]{{3, 2}, {2, 3}, {1, 4}})); // Output: 6
    }
}
