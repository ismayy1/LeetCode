package madium;

/*
    Given a string s, return the longest palindromic substring in s.

    Example 1:
    Input: s = "babad"
    Output: "bab"
    Explanation: "aba" is also a valid answer.

    Example 2:
    Input: s = "cbbd"
    Output: "bb"

    Constraints:
    1 <= s.length <= 1000
    s consist of only digits and English letters.
 */

public class Exc04_LongestPalindromicSubstring {
//    public String longestPalindrome(String s) {
//
//        if (s == null || s.length() < 1) return "";
//
//        int start = 0, end = 0;
//
//        for (int i = 0; i < s.length(); i++) {
//            int len1 = expandAroundCenter(s, i, i); // Odd length palindrome
//            int len2 = expandAroundCenter(s, i, i + 1); // Even length palindrome
//            int len = Math.max(len1, len2);
//
//            if (len > end - start) {
//                start = i - (len - 1) / 2;
//                end = i + len / 2;
//            }
//        }
//        return s.substring(start, end + 1);
//    }
//
//    private int expandAroundCenter(String s, int left, int right) {
//        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
//            left--;
//            right++;
//        }
//
//        return right - left - 1;
//    }

//    ============================================================================
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";

        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int start = 0, maxLength = 0;

        // All substrings of length 1 are palindromes
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
            start = i;
            maxLength = 1;
        }

        // Check for substrings of length 2
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                start = i;
                maxLength = 2;
            }
        }

        // Check for substrings of length greater than 2
        for (int length = 3; length <= n; length++) {
            for (int i = 0; i <= n - length; i++) {
                int j = i + length - 1;

                // Check if the current substring is palindrome
                if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
                    dp[i][j] = true;
                    start = i;
                    maxLength = length;
                }
            }
        }
        return s.substring(start, start + maxLength);
    }
//    ============================================================================

    public static void main(String[] args) {
        Exc04_LongestPalindromicSubstring solution = new Exc04_LongestPalindromicSubstring();

        String s1 = "babad";
        System.out.println("Longest palindrome in 'babad': " + solution.longestPalindrome(s1)); // Expected: "bab" or "aba"

        String s2 = "cbbd";
        System.out.println("Longest palindrome in 'cbbd': " + solution.longestPalindrome(s2)); // Expected: "bb"

        String s3 = "a";
        System.out.println("Longest palindrome in 'a': " + solution.longestPalindrome(s3)); // Expected: "a"

        String s4 = "ac";
        System.out.println("Longest palindrome in 'ac': " + solution.longestPalindrome(s4)); // Expected: "a" or "c"
    }
}
