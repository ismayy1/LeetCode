package madium;
/*
    Given a string s and an integer k, return true if you can use all the characters in s to construct
    k palindrome strings or false otherwise.

    Example 1:
    Input: s = "annabelle", k = 2
    Output: true
    Explanation: You can construct two palindromes using all characters in s.
    Some possible constructions "anna" + "elble", "anbna" + "elle", "anellena" + "b"
 */

public class Exc16_ConstructKPalindromeStrings {
    public boolean canConstruct(String s, int k) {
        if (s.length() < k) return false;

        int[] charCount = new int[26];
        for (char c : s.toCharArray()) {
            charCount[c - 'a']++;
        }

        int oddCount = 0;
        for (int count : charCount) {
            if (count % 2 != 0) {
                oddCount++;
            }
        }

        return oddCount <= k;
    }

    public static void main(String[] args) {
        Exc16_ConstructKPalindromeStrings solution = new Exc16_ConstructKPalindromeStrings();

        // Test cases
        System.out.println(solution.canConstruct("annabelle", 2)); // Output: true
        System.out.println(solution.canConstruct("leetcode", 3)); // Output: false
        System.out.println(solution.canConstruct("true", 4));     // Output: true
        System.out.println(solution.canConstruct("yzyzyzyzyzyzyzy", 2)); // Output: true
        System.out.println(solution.canConstruct("cr", 7));      // Output: false
    }
}
