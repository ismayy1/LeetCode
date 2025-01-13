package madium;
/*
    You are given a 0-indexed array of strings nums, where each string is of equal length and
    consists of only digits.
    You are also given a 0-indexed 2D integer array queries where queries[i] = [ki, trimi]. For
    each queries[i], you need to:
    Trim each number in nums to its rightmost trimi digits.
    Determine the index of the kith smallest trimmed number in nums. If two trimmed numbers are
    equal, the number with the lower index is considered to be smaller.
    Reset each number in nums to its original length. Return an array answer of the same length as
    queries, where answer[i] is the answer to the ith query.

    Note:
    To trim to the rightmost x digits means to keep removing the leftmost digit, until only x digits
    remain. Strings in nums may contain leading zeros.

    Example 1:
    Input: nums = ["102","473","251","814"], queries = [[1,1],[2,3],[4,2],[1,2]]
    Output: [2,2,1,0]
    Explanation:
    1. After trimming to the last digit, nums = ["2","3","1","4"]. The smallest number is 1 at index 2.
    2. Trimmed to the last 3 digits, nums is unchanged. The 2nd smallest number is 251 at index 2.
    3. Trimmed to the last 2 digits, nums = ["02","73","51","14"]. The 4th smallest number is 73.
    4. Trimmed to the last 2 digits, the smallest number is 2 at index 0.
       Note that the trimmed number "02" is evaluated as 2.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Exc25_QueryKthSmallestTrimmedNumber {
    public int[] smallestTrimmedNumbers(String[] nums, int[][] queries) {
        int qLen = queries.length;
        int[] answer = new int[qLen];

        for (int i = 0; i < qLen; i++) {
            int k = queries[i][0];
            int trim = queries[i][1];

            // List to store trimmed numbers and their original indices
            List<Pair> trimmedNums = new ArrayList<>();

            for (int j = 0; j < nums.length; j++) {
                String trimmed = nums[j].substring(nums[j].length() - trim);
                trimmedNums.add(new Pair(trimmed, j));
            }

            // Sort based on trimmed number, then by index
            trimmedNums.sort((a, b) -> {
                int cmp = a.value.compareTo(b.value);
                return cmp != 0 ? cmp : Integer.compare(a.index, b.index);
            });

            // Get the k-th smallest element
            answer[i] = trimmedNums.get(k - 1).index;
        }
        return answer;
    }

    // Helper class to store a number and its index
    static class Pair {
        String value;
        int index;

        Pair(String value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        Exc25_QueryKthSmallestTrimmedNumber solution = new Exc25_QueryKthSmallestTrimmedNumber();

        String[] nums = {"102", "473", "251", "814"};
        int[][] queries = {
                {1, 1},
                {2, 3},
                {4, 2},
                {1, 2}
        };

        int[] result = solution.smallestTrimmedNumbers(nums, queries);
        System.out.println(Arrays.toString(result)); // Output: [2, 2, 1, 0]
    }
}
