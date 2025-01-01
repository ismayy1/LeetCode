package madium;

/*
    You are given an array of strings words. Each element of words consists of two lowercase
    English letters. Create the longest possible palindrome by selecting some elements
    from words and concatenating them in any order. Each element can be selected at most once.
    Return the length of the longest palindrome that you can create. If it is impossible to create
    any palindrome, return 0. A palindrome is a string that reads the same forward and backward.

    Example 1:
    Input: words = ["lc","cl","gg"]
    Output: 6
    Explanation: One longest palindrome is "lc" + "gg" + "cl" = "lcggcl", of length 6.
    Note that "clgglc" is another longest palindrome that can be created.

    Example 2:
    Input: words = ["ab","ty","yt","lc","cl","ab"]
    Output: 8
    Explanation: One longest palindrome is "ty" + "lc" + "cl" + "yt" = "tylcclyt", of length 8.
    Note that "lcyttycl" is another longest palindrome that can be created.

    Example 3:
    Input: words = ["cc","ll","xx"]
    Output: 2
    Explanation: One longest palindrome is "cc", of length 2.
    Note that "ll" is another longest palindrome that can be created, and so is "xx".

    Constraints:
    1 <= words.length <= 105
    words[i].length == 2
    words[i] consists of lowercase English letters.
 */

import java.util.HashMap;
import java.util.Map;

public class Exc05_LongestPalindromeByConcatenatingTwoLetterWord {
    public int longestPalindrome(String[] words) {
        Map<String, Integer> countMap = new HashMap<>();
        int palindromeLength = 0;
        boolean hasOddCenter = false;

        for (String word : words) {
            countMap.put(word, countMap.getOrDefault(word, 0) + 1);
        }

        for (String word : countMap.keySet()) {
            String reversed = new StringBuilder(word).reverse().toString();
            int count = countMap.get(word);

            if (word.equals(reversed)) { // Case: Word is its own reverse (e.g., "aa")
                palindromeLength += (count / 2) * 4;
                if (count % 2 == 1) {
                    hasOddCenter = true;
                }
            } else if (countMap.containsKey(reversed)) { // Case: Word has a reverse pair (e.g., "ab" and "ba")
                int pairs = Math.min(count, countMap.get(reversed));
                palindromeLength += pairs * 4;
                countMap.put(reversed, 0); // Mark as used
            }
        }

        if (hasOddCenter) {
            palindromeLength += 2; // Add one odd palindrome center
        }
        return palindromeLength;
    }

    public static void main(String[] args) {
        Exc05_LongestPalindromeByConcatenatingTwoLetterWord solution =
                new Exc05_LongestPalindromeByConcatenatingTwoLetterWord();

        String[] words1 = {"lc", "cl", "gg"};
        System.out.println("Longest palindrome length: " + solution.longestPalindrome(words1));
        // Expected: 6

        String[] words2 = {"ab", "ty", "yt", "lc", "cl", "ab"};
        System.out.println("Longest palindrome length: " + solution.longestPalindrome(words2));
        // Expected: 8

        String[] words3 = {"cc", "ll", "xx"};
        System.out.println("Longest palindrome length: " + solution.longestPalindrome(words3));
        // Expected: 2
    }
}
