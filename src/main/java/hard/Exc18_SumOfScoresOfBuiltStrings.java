package hard;
/*
    You are building a string s of length n one character at a time, prepending each new character
    to the front of the string. The strings are labeled from 1 to n, where the string with length i
    is labeled si.
    For example, for s = "abaca", s1 == "a", s2 == "ca", s3 == "aca", etc.
    The score of si is the length of the longest common prefix between si and sn (Note that s == sn).
    Given the final string s, return the sum of the score of every si.

    Example 1:
    Input: s = "babab"
    Output: 9
    Explanation:
    For s1 == "b", the longest common prefix is "b" which has a score of 1.
    For s2 == "ab", there is no common prefix so the score is 0.
    For s3 == "bab", the longest common prefix is "bab" which has a score of 3.
    For s4 == "abab", there is no common prefix so the score is 0.
    For s5 == "babab", the longest common prefix is "babab" which has a score of 5.
    The sum of the scores is 1 + 0 + 3 + 0 + 5 = 9, so we return 9.
 */

public class Exc18_SumOfScoresOfBuiltStrings {
    public long sumScores(String s) {
        char[] ca= s.toCharArray();
        int n= ca.length, x= 0, y= 0;
        int[] z= new int[n];
        long ans= n;
        for(int i= 1; i<n; i++) {
            z[i]= Math.max(0, Math.min(z[i-x], y-i+1));
            while(i+z[i] < n && ca[z[i]] == ca[i+z[i]]){
                x= i; y= i+z[i]; z[i]++;
            }
            ans+= z[i];
        }
        return ans;
    }

    // Main method for testing
    public static void main(String[] args) {
        Exc18_SumOfScoresOfBuiltStrings solution = new Exc18_SumOfScoresOfBuiltStrings();

        // Test cases
        System.out.println(solution.sumScores("babab")); // Output: 9
        System.out.println(solution.sumScores("abcd"));  // Output: 4
        System.out.println(solution.sumScores("aaaa"));  // Output: 10
    }
}
