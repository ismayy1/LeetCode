package madium;
/*
    Given an array of strings words and an integer k, return the k most frequent strings.
    Return the answer sorted by the frequency from highest to lowest. Sort the words with the same
    frequency by their lexicographical order.

    Example 1:
    Input: words = ["i","love","leetcode","i","love","coding"], k = 2
    Output: ["i","love"]
    Explanation: "i" and "love" are the two most frequent words.
    Note that "i" comes before "love" due to a lower alphabetical order.
 */

import java.util.*;

public class Exc23_TopKFrequentWords {
    public List<String> topKFrequent(String[] words, int k) {
        // Step 1: Count the frequency of each word
        Map<String, Integer> freqMap = new HashMap<>();
        for (String word : words) {
            freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
        }

        // Step 2: Use a priority queue to sort by frequency and lexicographical order
        PriorityQueue<String> pq = new PriorityQueue<>(
                (a, b) -> freqMap.get(a).equals(freqMap.get(b))
                        ? a.compareTo(b)
                        : freqMap.get(b) - freqMap.get(a)
        );

        // Add all words to the priority queue
        pq.addAll(freqMap.keySet());

        // Step 3: Extract the top k elements
        List<String> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            result.add(pq.poll());
        }

        return result;
    }

    // Main method for testing
    public static void main(String[] args) {
        Exc23_TopKFrequentWords solution = new Exc23_TopKFrequentWords();

        String[] words1 = {"i", "love", "leetcode", "i", "love", "coding"};
        int k1 = 2;
        System.out.println(solution.topKFrequent(words1, k1)); // Output: ["i", "love"]

        String[] words2 = {"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"};
        int k2 = 4;
        System.out.println(solution.topKFrequent(words2, k2)); // Output: ["the", "is", "sunny", "day"]
    }
}
