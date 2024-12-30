package madium;

/*
    Given the integers zero, one, low, and high, we can construct a string by starting with
    an empty string, and then at each step perform either of the following:

    Append the character '0' zero times.
    Append the character '1' one times.
    This can be performed any number of times.

    A good string is a string constructed by the above process having a length between low and high (inclusive).

    Return the number of different good strings that can be constructed satisfying
    these properties. Since the answer can be large, return it modulo 109 + 7.
 */
public class Exc01_medium {
    public int countGoodStrings(int low, int high, int zero, int one) {
        int mod = 1_000_000_007;

        // dp[i] represents the number of good strings of length i.
        int[] dp = new int[high + 1];
        dp[0] = 1; // Base case: there's one way to construct an empty string.

        for (int length = 1; length <= high; length++) {
            // If we can append '0' zero times to form a string of this length.
            if (length >= zero) {
                dp[length] = (dp[length] + dp[length - zero]) % mod;
            }
            // If we can append '1' one times to form a string of this length.
            if (length >= one) {
                dp[length] = (dp[length] + dp[length - one]) % mod;
            }
        }

        // Sum up all the good strings of lengths between low and high.
        int result = 0;
        for (int length = low; length <= high; length++) {
            result = (result + dp[length]) % mod;
        }

        return result;
    }

    public static void main(String[] args) {
        Exc01_medium gs = new Exc01_medium();
        int low = 3;
        int high = 10;
        int zero = 2;
        int one = 3;
        System.out.println("Number of good strings: " + gs.countGoodStrings(low, high, zero, one));
    }
}
