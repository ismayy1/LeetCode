package hard;
/*
    A string is called a happy prefix if is a non-empty prefix which is also a suffix (excluding itself).
    Given a string s, return the longest happy prefix of s. Return an empty string "" if no such prefix
    exists.

    Example 1:
    Input: s = "level"
    Output: "l"
    Explanation: s contains 4 prefix excluding itself ("l", "le", "lev", "leve"), and suffix
    ("l", "el", "vel", "evel"). The largest prefix which is also suffix is given by "l".
 */

public class Exc17_LongestHappyPrefix {
    public String longestPrefix(String s) {
        int n = s.length();
        int[] lps = new int[n];

        // Compute the LPS array
        int len = 0; // Length of the previous longest prefix-suffix
        int i = 1;
        while (i < n) {
            if (s.charAt(i) == s.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    // Fall back to the previous longest prefix-suffix
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }

        // The value at the last index of lps gives the length of the longest prefix-suffix
        int longestPrefixLength = lps[n - 1];
        return s.substring(0, longestPrefixLength);
    }

    // Main method for testing
    public static void main(String[] args) {
        Exc17_LongestHappyPrefix solution = new Exc17_LongestHappyPrefix();

        // Test cases
        System.out.println(solution.longestPrefix("level")); // Output: "l"
        System.out.println(solution.longestPrefix("ababab")); // Output: "abab"
        System.out.println(solution.longestPrefix("leetcode")); // Output: ""
    }
}
