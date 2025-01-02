package easy;

/*
    Given a string s which consists of lowercase or uppercase letters, return the length of the longest
    palindrome that can be built with those letters. Letters are case sensitive,
    for example, "Aa" is not considered a palindrome.

    Example 1:
    Input: s = "abccccdd"
    Output: 7
    Explanation: One longest palindrome that can be built is "dccaccd", whose length is 7.

    Example 2:
    Input: s = "a"
    Output: 1
    Explanation: The longest palindrome that can be built is "a", whose length is 1.

    Constraints:
    1 <= s.length <= 2000
    s consists of lowercase and/or uppercase English letters only.
 */

import java.util.HashMap;
import java.util.Map;

public class Exc04_LongestPalindrome {
    public int longestPalindrome(String s) {

        if (s == null || s.isEmpty()) return 0;

        Map<Character, Integer> charCount = new HashMap<>();
        for (char c : s.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }

        int length = 0;
        boolean hasOdd = false;

        for (int count : charCount.values()) {
            if (count % 2 == 0) {
                length += count;
            } else {
                length += count - 1;
                hasOdd = true;
            }
        }

        if (hasOdd) {
            length += 1; // Add one odd character to the center
        }
        return length;
    }


    public static void main(String[] args) {
        Exc04_LongestPalindrome solution = new Exc04_LongestPalindrome();

        String s1 = "abccccdd";
        System.out.println("Longest palindrome length for 'abccccdd': " + solution.longestPalindrome(s1)); // Expected: 7

        String s2 = "a";
        System.out.println("Longest palindrome length for 'a': " + solution.longestPalindrome(s2)); // Expected: 1

        String s3 = "Aa";
        System.out.println("Longest palindrome length for 'Aa': " + solution.longestPalindrome(s3)); // Expected: 1
    }
}
