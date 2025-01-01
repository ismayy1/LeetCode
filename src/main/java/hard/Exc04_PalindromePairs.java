package hard;

/*
    You are given a 0-indexed array of unique strings words.
    A palindrome pair is a pair of integers (i, j) such that:

    - 0 <= i, j < words.length,
    - i != j, and
    - words[i] + words[j] (the concatenation of the two strings) is a palindrome

    Return an array of all the palindrome pairs of words.
    You must write an algorithm with O(sum of words[i].length) runtime complexity.

    Example 1:
    Input: words = ["abcd","dcba","lls","s","sssll"]
    Output: [[0,1],[1,0],[3,2],[2,4]]
    Explanation: The palindromes are ["abcddcba","dcbaabcd","slls","llssssll"]

    Example 2:
    Input: words = ["bat","tab","cat"]
    Output: [[0,1],[1,0]]
    Explanation: The palindromes are ["battab","tabbat"]

    Example 3:
    Input: words = ["a",""]
    Output: [[0,1],[1,0]]
    Explanation: The palindromes are ["a","a"]

    Constraints:
    1 <= words.length <= 5000
    0 <= words[i].length <= 300
    words[i] consists of lowercase English letters.
 */

import java.util.*;

public class Exc04_PalindromePairs {
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> result = new ArrayList<>();
        if (words == null || words.length < 2) return result;

        Map<String, Integer> wordMap = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            wordMap.put(words[i], i);
        }

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            for (int j = 0; j <= word.length(); j++) {
                String left = word.substring(0, j);
                String right = word.substring(j);

                // Case 1: If left part is palindrome, check reversed right part in map
                if (isPalindrome(left)) {
                    String reversedRight = new StringBuilder(right).reverse().toString();
                    if (wordMap.containsKey(reversedRight) && wordMap.get(reversedRight) != i) {
                        result.add(Arrays.asList(wordMap.get(reversedRight), i));
                    }
                }

                // Case 2: If right part is palindrome, check reversed left part in map
                // Avoid duplicate pairs when j == 0 (entire word is right)
                if (j != word.length() && isPalindrome(right)) {
                    String reversedLeft = new StringBuilder(left).reverse().toString();
                    if (wordMap.containsKey(reversedLeft) && wordMap.get(reversedLeft) != i) {
                        result.add(Arrays.asList(i, wordMap.get(reversedLeft)));
                    }
                }
            }
        }
        return result;
    }

    private boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Exc04_PalindromePairs solution = new Exc04_PalindromePairs();

        String[] words1 = {"abcd", "dcba", "lls", "s", "sssll"};
        System.out.println("Palindrome pairs: " + solution.palindromePairs(words1)); // Expected: [[0,1],[1,0],[3,2],[2,4]]

        String[] words2 = {"bat", "tab", "cat"};
        System.out.println("Palindrome pairs: " + solution.palindromePairs(words2)); // Expected: [[0,1],[1,0]]

        String[] words3 = {"a", ""};
        System.out.println("Palindrome pairs: " + solution.palindromePairs(words3)); // Expected: [[0,1],[1,0]]
    }
}
