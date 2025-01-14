package madium;
/*
    Given an integer array nums and an integer k, return the kth largest element in the array.
    Note that it is the kth largest element in the sorted order, not the kth distinct element.
    Can you solve it without sorting?

    Example 1:
    Input: nums = [3,2,1,5,6,4], k = 2
    Output: 5
 */

import java.util.PriorityQueue;

public class Exc26_KthLargestElementInArray {
    public int findKthLargest(int[] nums, int k) {
        // Min-Heap with capacity k
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // Iterate through all numbers in the array
        for (int num : nums) {
            minHeap.add(num); // Add the current number to the heap
            if (minHeap.size() > k) {
                minHeap.poll(); // Remove the smallest element if size exceeds k
            }
        }

        // The root of the heap is the kth largest element
        return minHeap.peek();
    }

    // Main method for testing
    public static void main(String[] args) {
        Exc26_KthLargestElementInArray solution = new Exc26_KthLargestElementInArray();

        int[] nums = {3, 2, 1, 5, 6, 4};
        int k = 2;

        int result = solution.findKthLargest(nums, k);
        System.out.println(result); // Output: 5
    }
}
