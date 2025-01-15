package hard;
/*
    You are given a 0-indexed string word and an integer k.
    At every second, you must perform the following operations:
    Remove the first k characters of word.
    Add any k characters to the end of word.
    Note that you do not necessarily need to add the same characters that you removed. However, you
    must perform both operations at every second.
    Return the minimum time greater than zero required for word to revert to its initial state.

    Example 1:
    Input: word = "abacaba", k = 3
    Output: 2
    Explanation: At the 1st second, we remove characters "aba" from the prefix of word, and add
    characters "bac" to the end of word. Thus, word becomes equal to "cababac".
    At the 2nd second, we remove characters "cab" from the prefix of word, and add "aba" to the end
    of word. Thus, word becomes equal to "abacaba" and reverts to its initial state.
    It can be shown that 2 seconds is the minimum time greater than zero required for word to revert
    to its initial state.
 */

public class Exc21_MinTimeToRevertWordToInitialState_2 {
    public int minimumTimeToInitialState(String s, int k) {
        int n = s.length(), v = 0, dp[] = new int[n];
        for (int i = 1; i < n; i++) { // kmp
            while (v > 0 && s.charAt(i) != s.charAt(v)) {
                v = dp[v - 1];
            }
            v = dp[i] = v + (s.charAt(i) == s.charAt(v) ? 1 : 0);
        }
        while (v > 0 && (n - v) % k > 0) {
            v = dp[v - 1];
        }
        return (n - v + k - 1) / k;
    }

    public static void main(String[] args) {
        Exc21_MinTimeToRevertWordToInitialState_2 solution =
                new Exc21_MinTimeToRevertWordToInitialState_2();

        // Test cases
        System.out.println(solution.minimumTimeToInitialState("abacaba", 3)); // Output: 2
        System.out.println(solution.minimumTimeToInitialState("aaaa", 2)); // Output: 2
    }
}
