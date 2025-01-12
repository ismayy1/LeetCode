package madium;
/*
    A parentheses string is a non-empty string consisting only of '(' and ')'. It is valid if any of
    the following conditions is true:
    It is (). It can be written as AB (A concatenated with B), where A and B are valid parentheses
    strings. It can be written as (A), where A is a valid parentheses string.
    You are given a parentheses string s and a string locked, both of length n. locked is a binary
    string consisting only of '0's and '1's. For each index i of locked,
    If locked[i] is '1', you cannot change s[i].
    But if locked[i] is '0', you can change s[i] to either '(' or ')'.
    Return true if you can make s a valid parentheses string. Otherwise, return false.
 */

public class Exc17_CheckIfParenthesesStringCanBeValid {
    public boolean canBeValid(String s, String locked) {
        if (s.length() % 2 != 0) {
            return false;
        }

        int balance = 0;

        // Left to right pass
        for (int i = 0; i < s.length(); i++) {
            if (locked.charAt(i) == '0' || s.charAt(i) == '(') {
                balance++;
            } else {
                balance--;
            }

            if (balance < 0) {
                return false;
            }
        }

        balance = 0;

        // Right to left pass
        for (int i = s.length() - 1; i >= 0; i--) {
            if (locked.charAt(i) == '0' || s.charAt(i) == ')') {
                balance++;
            } else {
                balance--;
            }

            if (balance < 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Exc17_CheckIfParenthesesStringCanBeValid solution = new Exc17_CheckIfParenthesesStringCanBeValid();

        // Test cases
        System.out.println(solution.canBeValid("()()", "0000")); // Output: true
        System.out.println(solution.canBeValid("())(", "0000")); // Output: true
        System.out.println(solution.canBeValid("))((", "0000")); // Output: false
        System.out.println(solution.canBeValid("(()))", "11000")); // Output: false
    }
}
