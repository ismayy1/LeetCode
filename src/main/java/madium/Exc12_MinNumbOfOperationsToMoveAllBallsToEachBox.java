package madium;
/*
    You have n boxes. You are given a binary string boxes of length n, where boxes[i] is '0' if the
    i-th box is empty, and '1' if it contains one ball. In one operation, you can move one ball from
    a box to an adjacent box. Box i is adjacent to box j if abs(i - j) == 1. Note that after doing
    so, there may be more than one ball in some boxes.
    Return an array answer of size n, where answer[i] is the minimum number of operations needed to
    move all the balls to the ith box.
    Each answer[i] is calculated considering the initial state of the boxes.

    Example 1:
    Input: boxes = "110"
    Output: [1,1,3]
    Explanation: The answer for each box is as follows:
    1) First box: you will have to move one ball from the second box to the first box in one operation.
    2) Second box: you will have to move one ball from the first box to the second box in one operation.
    3) Third box: you will have to move one ball from the first box to the third box in two operations, and move one ball from the second box to the third box in one operation.
 */

public class Exc12_MinNumbOfOperationsToMoveAllBallsToEachBox {

    public int[] minOperations(String boxes) {
        int n = boxes.length();
        int[] answer = new int[n];

        // First pass: from left to right
        int count = 0; // Number of balls encountered so far
        int operations = 0; // Total operations for left to right
        for (int i = 0; i < n; i++) {
            answer[i] += operations;
            if (boxes.charAt(i) == '1') {
                count++;
            }
            operations += count;
        }

        // Second pass: from right to left
        count = 0; // Reset count for right to left
        operations = 0; // Total operations for right to left
        for (int i = n - 1; i >= 0; i--) {
            answer[i] += operations;
            if (boxes.charAt(i) == '1') {
                count++;
            }
            operations += count;
        }

        return answer;
    }

    public static void main(String[] args) {
        Exc12_MinNumbOfOperationsToMoveAllBallsToEachBox solution =
                new Exc12_MinNumbOfOperationsToMoveAllBallsToEachBox();

        String boxes1 = "110";
        int[] result1 = solution.minOperations(boxes1);
        System.out.println("Result for '110': " + java.util.Arrays.toString(result1)); // Expected: [1, 1, 3]

        String boxes2 = "001011";
        int[] result2 = solution.minOperations(boxes2);
        System.out.println("Result for '001011': " + java.util.Arrays.toString(result2)); // Expected: [11, 8, 5, 4, 3, 4]
    }
}
