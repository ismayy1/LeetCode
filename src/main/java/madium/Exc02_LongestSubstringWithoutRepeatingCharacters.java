package madium;

/*
    Given a string s, find the length of the longest substring without repeating characters.

    Example 1:

    Input: s = "abcabcbb"
    Output: 3
    Explanation: The answer is "abc", with the length of 3.
    Example 2:

    Input: s = "bbbbb"
    Output: 1
    Explanation: The answer is "b", with the length of 1.
    Example 3:

    Input: s = "pwwkew"
    Output: 3
    Explanation: The answer is "wke", with the length of 3.
    Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */

import java.util.HashSet;
import java.util.Set;

public class Exc02_LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {

//         Use a sliding window approach with a set to track characters
        Set<Character> set = new HashSet<>();
        int left = 0, maxLength = 0;

        for (int right = 0; right < s.length(); right++) {
//             If a duplicate character is found, shrink the window from the left
            while (set.contains(s.charAt(right))) {
                set.remove(s.charAt(left));
                left++;
            }

//             Add the current character to the set
            set.add(s.charAt(right));

//             Update the maximum length of the substring
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }


    public static void main(String[] args) {
        Exc02_LongestSubstringWithoutRepeatingCharacters solution =
                new Exc02_LongestSubstringWithoutRepeatingCharacters();

        String s1 = "abcabcbb";
        System.out.println("Length of longest substring without repeating " +
                "characters for 'abcabcbb': " + solution.lengthOfLongestSubstring(s1));
        // Expected output: 3

        String s2 = "bbbbb";
        System.out.println("Length of longest substring without repeating " +
                "characters for 'bbbbb': " + solution.lengthOfLongestSubstring(s2));
        // Expected output: 1

        String s3 = "pwwkew";
        System.out.println("Length of longest substring without repeating " +
                "characters for 'pwwkew': " + solution.lengthOfLongestSubstring(s3));
        // Expected output: 3

        String s4 = "";
        System.out.println("Length of longest substring without repeating " +
                "characters for '': " + solution.lengthOfLongestSubstring(s4));
        // Expected output: 0
    }
}
