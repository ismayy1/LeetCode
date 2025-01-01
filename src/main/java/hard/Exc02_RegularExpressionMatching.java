package hard;

/*
    Given an input string s and a pattern p, implement regular expression matching with
    support for '.' and '*' where:

    '.' Matches any single character.​​​​
    '*' Matches zero or more of the preceding element.
    The matching should cover the entire input string (not partial).

    Example 1:
    Input: s = "aa", p = "a"
    Output: false
    Explanation: "a" does not match the entire string "aa".

    Example 2:
    Input: s = "aa", p = "a*"
    Output: true
    Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".

    Example 3:
    Input: s = "ab", p = ".*"
    Output: true
    Explanation: ".*" means "zero or more (*) of any character (.)".
 */

public class Exc02_RegularExpressionMatching {
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];

        // Base case: empty string matches empty pattern
        dp[0][0] = true;

        // Handle patterns with '*' that can match zero preceding elements
        for (int j = 1; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }

        // Fill the DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j - 1) == '.' || p.charAt(j - 1) == s.charAt(i - 1)) {
                    // Characters match or pattern has '.'
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    // '*' can match zero or more of the preceding element
                    dp[i][j] = dp[i][j - 2] || (dp[i - 1][j] && (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.'));
                }
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        Exc02_RegularExpressionMatching solution = new Exc02_RegularExpressionMatching();

        String s1 = "aa", p1 = "a*";
        System.out.println("Does 'aa' match 'a*': " + solution.isMatch(s1, p1)); // Expected output: true

        String s2 = "mississippi", p2 = "mis*is*p*.";
        System.out.println("Does 'mississippi' match 'mis*is*p*.': " + solution.isMatch(s2, p2)); // Expected output: false

        String s3 = "ab", p3 = ".*";
        System.out.println("Does 'ab' match '.*': " + solution.isMatch(s3, p3)); // Expected output: true
    }
}
