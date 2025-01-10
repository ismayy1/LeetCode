package madium;
/*
    You are given two string arrays words1 and words2. A string b is a subset of string a if every
    letter in b occurs in a including multiplicity.
    For example, "wrr" is a subset of "warrior" but is not a subset of "world". A string a from words1
    is universal if for every string b in words2, b is a subset of a. Return an array of all the
    universal strings in words1. You may return the answer in any order.

    Example 1:
    Input: words1 = ["amazon","apple","facebook","google","leetcode"], words2 = ["e","o"]
    Output: ["facebook","google","leetcode"]
 */

import java.util.ArrayList;
import java.util.List;

public class Exc14_WordSubsets {

    public List<String> wordSubsets(String[] words1, String[] words2) {
        int[] maxFreq = new int[26];

        for (String b : words2) {
            int[] freq = getCharFrequency(b);
            for (int i = 0; i < 26; i++) {
                maxFreq[i] = Math.max(maxFreq[i], freq[i]);
            }
        }

        List<String> result = new ArrayList<>();
        for (String a : words1) {
            int[] freq = getCharFrequency(a);
            if (isUniversal(freq, maxFreq)) {
                result.add(a);
            }
        }

        return result;
    }

    private int[] getCharFrequency(String word) {
        int[] freq = new int[26];
        for (char c : word.toCharArray()) {
            freq[c - 'a']++;
        }
        return freq;
    }

    private boolean isUniversal(int[] wordFreq, int[] maxFreq) {
        for (int i = 0; i < 26; i++) {
            if (wordFreq[i] < maxFreq[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Exc14_WordSubsets solution = new Exc14_WordSubsets();

        String[] words1 = {"amazon", "apple", "facebook", "google", "leetcode"};
        String[] words2 = {"e", "o"};
        System.out.println("Universal Strings: " + solution.wordSubsets(words1, words2));

        String[] words3 = {"amazon", "apple", "facebook", "google", "leetcode"};
        String[] words4 = {"lo", "eo"};
        System.out.println("Universal Strings: " + solution.wordSubsets(words3, words4));

        String[] words5 = {"abc", "bca", "cab"};
        String[] words6 = {"a", "b"};
        System.out.println("Universal Strings: " + solution.wordSubsets(words5, words6));

        String[] words7 = {"abcd", "efgh"};
        String[] words8 = {"z"};
        System.out.println("Universal Strings: " + solution.wordSubsets(words7, words8)); // Edge case
    }
}
