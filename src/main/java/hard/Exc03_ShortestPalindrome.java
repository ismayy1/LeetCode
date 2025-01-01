package hard;

/*
    You are given a string s. You can convert s to a palindrome by adding characters in front of it.
    Return the shortest palindrome you can find by performing this transformation.

    Example 1:
    Input: s = "aacecaaa"
    Output: "aaacecaaa"

    Example 2:
    Input: s = "abcd"
    Output: "dcbabcd"

    Constraints:
    0 <= s.length <= 5 * 104
    s consists of lowercase English letters only.
 */

public class Exc03_ShortestPalindrome {
    public String shortestPalindrome(String s) {

        if (s == null || s.length() < 2) return s;

        String reversed = new StringBuilder(s).reverse().toString();
        String combined = s + "#" + reversed;

//         Compute the KMP table
        int[] kmp = computeKMP(combined);

//         Length of the longest palindromic prefix
        int palindromeLength = kmp[kmp.length - 1];

//         Add the necessary characters to the front
        String toAdd = reversed.substring(0, s.length() - palindromeLength);
        return toAdd + s;
    }

    private int[] computeKMP(String pattern) {
        int[] kmp = new int[pattern.length()];
        int j = 0;

        for (int i = 1; i < pattern.length(); i++) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = kmp[j - 1];
            }

            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            kmp[i] = j;
        }
        return kmp;
    }

    public static void main(String[] args) {
        Exc03_ShortestPalindrome solution = new Exc03_ShortestPalindrome();

        String s1 = "aacecaaa";
        System.out.println("Shortest palindrome for 'aacecaaa': " + solution.shortestPalindrome(s1));
        // Expected: "aaacecaaa"

        String s2 = "abcd";
        System.out.println("Shortest palindrome for 'abcd': " + solution.shortestPalindrome(s2));
        // Expected: "dcbabcd"
    }
}
