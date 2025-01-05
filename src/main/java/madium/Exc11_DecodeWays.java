package madium;
/*
    You have intercepted a secret message encoded as a string of numbers. The message is decoded via
    the following mapping:
    "1" -> 'A'
    "2" -> 'B'
    ...
    "25" -> 'Y'
    "26" -> 'Z'
    However, while decoding the message, you realize that there are many different ways you can
    decode the message because some codes are contained in other codes ("2" and "5" vs "25").
    For example, "11106" can be decoded into:
    "AAJF" with the grouping (1, 1, 10, 6)
    "KJF" with the grouping (11, 10, 6)
    The grouping (1, 11, 06) is invalid because "06" is not a valid code (only "6" is valid).
    Note: there may be strings that are impossible to decode. Given a string s containing only digits,
    return the number of ways to decode it. If the entire string cannot be decoded in any valid way,
    return 0. The test cases are generated so that the answer fits in a 32-bit integer
    Example 1:
    Input: s = "12"
    Output: 2
    Explanation:
    "12" could be decoded as "AB" (1 2) or "L" (12).

    Example 2:
    Input: s = "226"
    Output: 3
    Explanation:
    "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 */

public class Exc11_DecodeWays {
    public int numDecodings(String s) {
        if (s == null || s.isEmpty() || s.charAt(0) == '0') {
            return 0;
        }

        int n = s.length();
        int[] dp = new int[n + 1];

        dp[0] = 1; // Base case: empty string has one way to decode
        dp[1] = s.charAt(0) == '0' ? 0 : 1; // Single digit decoding

        for (int i = 2; i <= n; i++) {
            char current = s.charAt(i - 1);
            char prev = s.charAt(i - 2);

            // Single digit decoding
            if (current != '0') {
                dp[i] += dp[i - 1];
            }

            // Two digit decoding
            if (prev == '1' || (prev == '2' && current <= '6')) {
                dp[i] += dp[i - 2];
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        Exc11_DecodeWays solution = new Exc11_DecodeWays();

        String s1 = "12";
        System.out.println("Number of ways to decode '12': " + solution.numDecodings(s1)); // Expected: 2 ("AB", "L")

        String s2 = "11106";
        System.out.println("Number of ways to decode '11106': " + solution.numDecodings(s2)); // Expected: 2 ("AAJF", "KJF")

        String s3 = "0";
        System.out.println("Number of ways to decode '0': " + solution.numDecodings(s3)); // Expected: 0

        String s4 = "226";
        System.out.println("Number of ways to decode '226': " + solution.numDecodings(s4)); // Expected: 3 ("BZ", "VF", "BBF")
    }
}
