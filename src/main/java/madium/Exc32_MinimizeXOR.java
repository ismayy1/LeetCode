package madium;
/*
    Given two positive integers num1 and num2, find the positive integer x such that:
    x has the same number of set bits as num2, and
    The value x XOR num1 is minimal.
    Note that XOR is the bitwise XOR operation.
    Return the integer x. The test cases are generated such that x is uniquely determined.
    The number of set bits of an integer is the number of 1's in its binary representation.

    Example 1:
    Input: num1 = 3, num2 = 5
    Output: 3
    Explanation:
    The binary representations of num1 and num2 are 0011 and 0101, respectively.
    The integer 3 has the same number of set bits as num2, and the value 3 XOR 3 = 0 is minimal.
 */

public class Exc32_MinimizeXOR {
    public int minimizeXor(int num1, int num2) {
        int setBitsTarget = Integer.bitCount(num2); // Number of set bits in num2
        int x = 0;

        // Set bits in x corresponding to set bits in num1
        for (int i = 31; i >= 0 && setBitsTarget > 0; i--) {
            if ((num1 & (1 << i)) != 0) {
                x |= (1 << i);
                setBitsTarget--;
            }
        }

        // If more bits are needed, set the least significant bits in x
        for (int i = 0; i <= 31 && setBitsTarget > 0; i++) {
            if ((x & (1 << i)) == 0) {
                x |= (1 << i);
                setBitsTarget--;
            }
        }
        return x;
    }

    // Main method for testing
    public static void main(String[] args) {
        Exc32_MinimizeXOR solution = new Exc32_MinimizeXOR();

        // Test cases
        System.out.println(solution.minimizeXor(3, 5)); // Output: 3
        System.out.println(solution.minimizeXor(1, 12)); // Output: 3
    }
}
