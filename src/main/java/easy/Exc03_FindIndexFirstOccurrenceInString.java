package easy;

/*
    Given two strings needle and haystack, return the index of the first occurrence of
    needle in haystack, or -1 if needle is not part of haystack.

    Example 1:
    Input: haystack = "sadbutsad", needle = "sad"
    Output: 0
    Explanation: "sad" occurs at index 0 and 6.
    The first occurrence is at index 0, so we return 0.

    Example 2:
    Input: haystack = "leetcode", needle = "leeto"
    Output: -1
    Explanation: "leeto" did not occur in "leetcode", so we return -1.

    Constraints:
    1 <= haystack.length, needle.length <= 104
    haystack and needle consist of only lowercase English characters.
 */

public class Exc03_FindIndexFirstOccurrenceInString {
    public int strStr(String haystack, String needle) {

        if (needle == null || needle.isEmpty() || needle.isBlank()) return 0;
        if (haystack == null || haystack.isBlank() ||
                haystack.isEmpty() || haystack.length() < needle.length()) return -1;

        int haystackLength = haystack.length();
        int needleLength = needle.length();

        for (int i = 0; i <= haystackLength - needleLength; i++) {
            int j;
            for (j = 0; j < needleLength; j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    break;
                }
            }

            if (j == needleLength) {
                return i; // Found the needle
            }
        }
        return -1; // Needle not found
    }

    public static void main(String[] args) {
        Exc03_FindIndexFirstOccurrenceInString solution = new Exc03_FindIndexFirstOccurrenceInString();

        String haystack1 = "hello", needle1 = "ll";
        System.out.println("Index of 'll' in 'hello': " + solution.strStr(haystack1, needle1));
        // Expected: 2

        String haystack2 = "aaaaa", needle2 = "bba";
        System.out.println("Index of 'bba' in 'aaaaa': " + solution.strStr(haystack2, needle2));
        // Expected: -1

        String haystack3 = "", needle3 = "";
        System.out.println("Index of '' in '': " + solution.strStr(haystack3, needle3));
        // Expected: 0

        String haystack4 = "abc", needle4 = "c";
        System.out.println("Index of 'c' in 'abc': " + solution.strStr(haystack4, needle4));
        // Expected: 2
    }
}
