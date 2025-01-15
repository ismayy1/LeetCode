package hard;
/*
    You are given a string s consisting of only lowercase English letters. In one operation, you can:
    Delete the entire string s, or
    Delete the first i letters of s if the first i letters of s are equal to the following i letters
    in s, for any i in the range 1 <= i <= s.length / 2.
    For example, if s = "ababc", then in one operation, you could delete the first two letters of s
    to get "abc", since the first two letters of s and the following two letters of s are both equal
    to "ab". Return the maximum number of operations needed to delete all of s.

    Example 1:
    Input: s = "abcabcdabc"
    Output: 2
    Explanation:
    - Delete the first 3 letters ("abc") since the next 3 letters are equal. Now, s = "abcdabc".
    - Delete all the letters.
    We used 2 operations so return 2. It can be proven that 2 is the maximum number of operations needed.
    Note that in the second operation we cannot delete "abc" again because the next occurrence of "abc" does not happen in the next 3 letters.
 */

public class Exc19_MaxDeletionsOnString {
    public int deleteString(String s) {
        int n = s.length();
        int[] dp = new int[n + 1]; // dp[i] = max operations to delete s[0:i-1]
        int[][] lcs = new int[n + 1][n + 1]; // lcs[i][j] = longest common prefix of s[i:] and s[j:]

        // Compute the LCS array
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j > i; j--) {
                if (s.charAt(i) == s.charAt(j)) {
                    lcs[i][j] = lcs[i + 1][j + 1] + 1;
                }
            }
        }

        // DP calculation
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = 1; // At least one operation to delete the entire substring
            for (int len = 1; i + len * 2 <= n; len++) {
                if (lcs[i][i + len] >= len) {
                    dp[i] = Math.max(dp[i], dp[i + len] + 1);
                }
            }
        }

        return dp[0];
    }

    // Main method for testing
    public static void main(String[] args) {
        Exc19_MaxDeletionsOnString solution = new Exc19_MaxDeletionsOnString();

        // Test cases
        System.out.println(solution.deleteString("abcabcdabc")); // Output: 2
        System.out.println(solution.deleteString("aaaaa")); // Output: 5
        System.out.println(solution.deleteString("ababa")); // Output: 3
    }
}
