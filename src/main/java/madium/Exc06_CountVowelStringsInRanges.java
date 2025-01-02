package madium;

/*
    You are given a 0-indexed array of strings words and a 2D array of integers queries.
    Each query queries[i] = [li, ri] asks us to find the number of strings present in the
    range li to ri (both inclusive) of words that start and end with a vowel. Return an array
    ans of size queries.length, where ans[i] is the answer to the ith query.
    Note that the vowel letters are 'a', 'e', 'i', 'o', and 'u'.

    Example 1:
    Input: words = ["aba","bcb","ece","aa","e"], queries = [[0,2],[1,4],[1,1]]
    Output: [2,3,0]
    Explanation: The strings starting and ending with a vowel are "aba", "ece", "aa" and "e".
    The answer to the query [0,2] is 2 (strings "aba" and "ece").
    to query [1,4] is 3 (strings "ece", "aa", "e").
    to query [1,1] is 0.
    We return [2,3,0].

    Example 2:
    Input: words = ["a","e","i"], queries = [[0,2],[0,1],[2,2]]
    Output: [3,2,1]
    Explanation: Every string satisfies the conditions, so we return [3,2,1].

    Constraints:
    1 <= words.length <= 105
    1 <= words[i].length <= 40
    words[i] consists only of lowercase English letters.
    sum(words[i].length) <= 3 * 105
    1 <= queries.length <= 105
    0 <= li <= ri < words.length
 */

import java.util.Arrays;

public class Exc06_CountVowelStringsInRanges {

    public int[] vowelStrings(String[] words, int[][] queries) {
        int n = words.length;
        int[] prefixSum = new int[n + 1];

        for (int i = 0; i < n; i++) {
            if (isVowelString(words[i])) {
                prefixSum[i + 1] = prefixSum[i] + 1;
            } else {
                prefixSum[i + 1] = prefixSum[i];
            }
        }

        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int li = queries[i][0];
            int ri = queries[i][1];
            result[i] = prefixSum[ri + 1] - prefixSum[li];
        }

        return result;
    }

    private boolean isVowelString(String word) {
        if (word == null || word.isEmpty()) return false;
        char first = word.charAt(0);
        char last = word.charAt(word.length() - 1);
        return isVowel(first) && isVowel(last);
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    public static void main(String[] args) {
        Exc06_CountVowelStringsInRanges solution = new Exc06_CountVowelStringsInRanges();

        String[] words1 = {"apple", "banana", "orange", "umbrella", "ice"};
        int[][] queries1 = {{0, 2}, {1, 4}, {0, 4}};
        System.out.println("Answers for queries: " + Arrays.toString(solution.vowelStrings(words1, queries1))); // Expected: [1, 2, 3]

        String[] words2 = {"a", "e", "i", "o", "u"};
        int[][] queries2 = {{0, 4}, {0, 1}, {3, 4}};
        System.out.println("Answers for queries: " + Arrays.toString(solution.vowelStrings(words2, queries2))); // Expected: [5, 2, 2]
    }
}
