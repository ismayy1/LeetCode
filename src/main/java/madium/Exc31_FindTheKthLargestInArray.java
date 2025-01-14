package madium;
/*
    You are given an array of strings nums and an integer k. Each string in nums represents an
    integer without leading zeros.
    Return the string that represents the kth largest integer in nums.
    Note: Duplicate numbers should be counted distinctly. For example, if nums is ["1","2","2"], "2" is
    the first largest integer, "2" is the second-largest integer, and "1" is the third-largest integer.

    Example 1:
    Input: nums = ["3","6","7","10"], k = 4
    Output: "3"
    Explanation:
    The numbers in nums sorted in non-decreasing order are ["3","6","7","10"].
    The 4th largest integer in nums is "3".
 */

import java.util.PriorityQueue;

public class Exc31_FindTheKthLargestInArray {
    public String kthLargestNumber(String[] nums, int k) {
        // Min-heap to store the k largest numbers
        PriorityQueue<String> minHeap = new PriorityQueue<>((a, b) -> {
            if (a.length() == b.length()) {
                return a.compareTo(b); // Compare lexicographically for same length
            }
            return Integer.compare(a.length(), b.length()); // Compare by length
        });

        // Add numbers to the heap
        for (String num : nums) {
            minHeap.offer(num);

            // If heap size exceeds k, remove the smallest element
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // The kth largest element is at the root of the heap
        return minHeap.peek();
    }

    // Main method for testing
    public static void main(String[] args) {
        Exc31_FindTheKthLargestInArray solution = new Exc31_FindTheKthLargestInArray();

        String[] nums = {"3", "6", "7", "10"};
        int k = 4;

        String result = solution.kthLargestNumber(nums, k);
        System.out.println(result); // Output: "3"
    }
}
