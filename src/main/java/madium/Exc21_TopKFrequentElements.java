package madium;
/*
    Given an integer array nums and an integer k, return the k most frequent elements.
    You may return the answer in any order.

    Example 1:
    Input: nums = [1,1,1,2,2,3], k = 2
    Output: [1,2]

    Example 2:
    Input: nums = [1], k = 1
    Output: [1]

    Constraints:
    1 <= nums.length <= 105
    -104 <= nums[i] <= 104
    k is in the range [1, the number of unique elements in the array].
    It is guaranteed that the answer is unique.

    Follow up: Your algorithm's time complexity must be better than O(n log n), where n is the
    array's size.
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Exc21_TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {
        // Step 1: Count the frequency of each element using a HashMap
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // Step 2: Create a max heap (priority queue) based on frequencies
        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap =
                new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());

        // Add all entries of the frequency map to the max heap
        maxHeap.addAll(freqMap.entrySet());

        // Step 3: Extract the top k frequent elements
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = maxHeap.poll().getKey();
        }

        return result;
    }

    // Main method for testing
    public static void main(String[] args) {
        Exc21_TopKFrequentElements solution = new Exc21_TopKFrequentElements();
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        System.out.println(Arrays.toString(solution.topKFrequent(nums, k))); // Output: [1, 2]

        nums = new int[]{1};
        k = 1;
        System.out.println(Arrays.toString(solution.topKFrequent(nums, k))); // Output: [1]
    }
}
