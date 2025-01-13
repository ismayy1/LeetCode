package madium;
/*
    Given a string s, sort it in decreasing order based on the frequency of the characters. The
    frequency of a character is the number of times it appears in the string.
    Return the sorted string. If there are multiple answers, return any of them.

    Example 1:
    Input: s = "tree"
    Output: "eert"
    Explanation: 'e' appears twice while 'r' and 't' both appear once.
    So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
 */

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Exc22_SortCharactersByFrequency {
    public String frequencySort(String s) {
        // Step 1: Count the frequency of each character using a HashMap
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        // Step 2: Create a max heap (priority queue) based on frequencies
        PriorityQueue<Map.Entry<Character, Integer>> maxHeap =
                new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());

        // Add all entries from the frequency map to the max heap
        maxHeap.addAll(freqMap.entrySet());

        // Step 3: Build the result string
        StringBuilder result = new StringBuilder();
        while (!maxHeap.isEmpty()) {
            Map.Entry<Character, Integer> entry = maxHeap.poll();
            char c = entry.getKey();
            int freq = entry.getValue();
            for (int i = 0; i < freq; i++) {
                result.append(c);
            }
        }

        return result.toString();
    }

    // Main method for testing
    public static void main(String[] args) {
        Exc22_SortCharactersByFrequency solution = new Exc22_SortCharactersByFrequency();
        System.out.println(solution.frequencySort("tree")); // Output: "eetr" or "eert"
        System.out.println(solution.frequencySort("cccaaa")); // Output: "cccaaa" or "aaaccc"
        System.out.println(solution.frequencySort("Aabb")); // Output: "bbAa" or "bbaA"
    }
}
