package madium;
/*
    You are given a string s. You can perform the following process on s any number of times:
    Choose an index i in the string such that there is at least one character to the left of index i
    that is equal to s[i], and at least one character to the right that is also equal to s[i].
    Delete the closest character to the left of index i that is equal to s[i].
    Delete the closest character to the right of index i that is equal to s[i].
    Return the minimum length of the final string s that you can achieve.

    Example 1:
    Input: s = "abaacbcbb"
    Output: 5

    Explanation:
    We do the following operations:
    Choose index 2, then remove the characters at indices 0 and 3. The resulting string is s = "bacbcbb".
    Choose index 3, then remove the characters at indices 0 and 5. The resulting string is s = "acbcb".
 */

import java.util.Arrays;

public class Exc20_MinLengthOfStringAfterOperations {
    int minimumLength(String s) {
        int[] freq = new int[26];
        Arrays.fill(freq, 0);
        int ans = s.length();
        for(int i = 0; i < s.length(); ++i){
            freq[s.charAt(i) - 'a']++;
            if(freq[s.charAt(i) - 'a'] != 1 && freq[s.charAt(i) - 'a'] % 2 == 1) ans -= 2;
        }
        return ans;
    }
}
