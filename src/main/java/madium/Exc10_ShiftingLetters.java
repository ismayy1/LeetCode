package madium;

/*
    You are given a string s of lowercase English letters and a 2D integer array shifts where
    shifts[i] = [starti, endi, directioni]. For every i, shift the characters in s from the index
    starti to the index endi (inclusive) forward if directioni = 1, or shift the characters backward
    if directioni = 0.
    Shifting a character forward means replacing it with the next letter in the alphabet
    (wrapping around so that 'z' becomes 'a'). Similarly, shifting a character backward means
    replacing it with the previous letter in the alphabet (wrapping around so that 'a' becomes 'z').
    Return the final string after all such shifts to s are applied.

    Example 1:
    Input: s = "abc", shifts = [[0,1,0],[1,2,1],[0,2,1]]
    Output: "ace"
    Explanation: Firstly, shift the characters from index 0 to index 1 backward. Now s = "zac".
    Secondly, shift the characters from index 1 to index 2 forward. Now s = "zbd".
    Finally, shift the characters from index 0 to index 2 forward. Now s = "ace".

    Example 2:
    Input: s = "dztz", shifts = [[0,0,0],[1,1,1]]
    Output: "catz"
    Explanation: Firstly, shift the characters from index 0 to index 0 backward. Now s = "cztz".
    Finally, shift the characters from index 1 to index 1 forward. Now s = "catz".
 */
public class Exc10_ShiftingLetters {
    public String shiftingLetters(String s, int[][] shifts) {
        int n = s.length();
        int[] shiftAmounts = new int[n + 1];

        // Build shift amounts array
        for (int[] shift : shifts) {
            int start = shift[0];
            int end = shift[1];
            int direction = shift[2];

            if (direction == 1) {
                shiftAmounts[start] += 1;
                shiftAmounts[end + 1] -= 1;
            } else {
                shiftAmounts[start] -= 1;
                shiftAmounts[end + 1] += 1;
            }
        }

        // Compute the cumulative shifts
        int cumulativeShift = 0;
        char[] result = s.toCharArray();
        for (int i = 0; i < n; i++) {
            cumulativeShift += shiftAmounts[i];
            int newChar = ((result[i] - 'a') + cumulativeShift) % 26;
            if (newChar < 0) {
                newChar += 26;
            }
            result[i] = (char) ('a' + newChar);
        }

        return new String(result);
    }

    public static void main(String[] args) {
        Exc10_ShiftingLetters solution = new Exc10_ShiftingLetters();

        String s1 = "abc";
        int[][] shifts1 = {{0, 1, 0}, {1, 2, 1}};
        System.out.println("Final string: " + solution.shiftingLetters(s1, shifts1)); // Expected: "zac"

        String s2 = "zxy";
        int[][] shifts2 = {{0, 2, 1}, {1, 1, 0}};
        System.out.println("Final string: " + solution.shiftingLetters(s2, shifts2)); // Expected: "aya"
    }
}
