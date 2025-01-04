package madium;

/*
    Given a string s, return the number of unique palindromes of length three that are a subsequence
    of s. Note that even if there are multiple ways to obtain the same subsequence, it is still only
    counted once. A palindrome is a string that reads the same forwards and backwards. A subsequence
    of a string is a new string generated from the original string with some characters (can be none)
    deleted without changing the relative order of the remaining characters.
    For example, "ace" is a subsequence of "abcde".

    Example 1:
    Input: s = "aabca"
    Output: 3
    Explanation: The 3 palindromic subsequences of length 3 are:
    - "aba" (subsequence of "aabca")
    - "aaa" (subsequence of "aabca")
    - "aca" (subsequence of "aabca")

    Example 2:
    Input: s = "adc"
    Output: 0
    Explanation: There are no palindromic subsequences of length 3 in "adc".

    Example 3:
    Input: s = "bbcbaba"
    Output: 4
    Explanation: The 4 palindromic subsequences of length 3 are:
    - "bbb" (subsequence of "bbcbaba")
    - "bcb" (subsequence of "bbcbaba")
    - "bab" (subsequence of "bbcbaba")
    - "aba" (subsequence of "bbcbaba")

    Constraints:
    3 <= s.length <= 105
    s consists of only lowercase English letters.
 */

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Exc09_UniqueLength3PalindromicSubsequences {
    public int countPalindromicSubsequence(String s) {
            int n = s.length();
            Set<String> uniquePalindromes = new HashSet<>();

            int[] leftSeen = new int[26];
            Arrays.fill(leftSeen, -1);

            int[] rightSeen = new int[26];
            Arrays.fill(rightSeen, -1);

            for (int i = 0; i < n; i++) {
                rightSeen[s.charAt(i) - 'a'] = i;
            }

            for (int i = 0; i < n; i++) {
                int currentChar = s.charAt(i) - 'a';
                rightSeen[currentChar] = -1; // Remove the current character from right seen

                for (int ch = 0; ch < 26; ch++) {
                    if (leftSeen[ch] != -1 && rightSeen[ch] != -1) {
                        uniquePalindromes.add("" + (char)(ch + 'a') + s.charAt(i) + (char)(ch + 'a'));
                    }
                }

                leftSeen[currentChar] = i; // Mark the current character as seen in left
            }
            return uniquePalindromes.size();
        }

    public static void main(String[] args) {
        Exc09_UniqueLength3PalindromicSubsequences solution = new Exc09_UniqueLength3PalindromicSubsequences();

        String s1 = "aabca";
        System.out.println("Number of unique palindromes of length 3: " + solution.countPalindromicSubsequence(s1)); // Expected: 3

        String s2 = "bbcbaba";
        System.out.println("Number of unique palindromes of length 3: " + solution.countPalindromicSubsequence(s2)); // Expected: 4

        String s3 = "abcd";
        System.out.println("Number of unique palindromes of length 3: " + solution.countPalindromicSubsequence(s3)); // Expected: 0
    }
}
