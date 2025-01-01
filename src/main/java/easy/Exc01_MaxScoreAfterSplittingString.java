package easy;

/*
    Given a string s of zeros and ones, return the maximum score after
    splitting the string into two non-empty substrings (i.e. left substring and right substring).

    The score after splitting a string is the number of zeros in the left
    substring plus the number of ones in the right substring.
 */

public class Exc01_MaxScoreAfterSplittingString {

    public int maxScore(String s) {

        int maxScore = 0;

//        PreCompute the total number of ones in the string
        int totalOnes = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') {
                totalOnes ++;
            }
        }

        int leftZeros = 0;
        int rightOnes = totalOnes;

//        Iterate through the string, excluding the last character to ensure non-empty right substring
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == '0') {
                leftZeros++;
            } else {
                rightOnes--;
            }

//            Calculate the score for the current split
            maxScore = Math.max(maxScore, leftZeros + rightOnes);
        }
        return maxScore;
    }

    public static void main(String[] args) {
        Exc01_MaxScoreAfterSplittingString solution = new Exc01_MaxScoreAfterSplittingString();

        String s1 = "011101";
        System.out.println("Max score for '011101': " + solution.maxScore(s1)); // Expected output: 5

        String s2 = "00111";
        System.out.println("Max score for '00111': " + solution.maxScore(s2)); // Expected output: 5

        String s3 = "1111";
        System.out.println("Max score for '1111': " + solution.maxScore(s3)); // Expected output: 3
    }
}
