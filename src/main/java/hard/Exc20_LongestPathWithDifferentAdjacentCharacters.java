package hard;
/*
    You are given a tree (i.e. a connected, undirected graph that has no cycles) rooted at node 0
    consisting of n nodes numbered from 0 to n - 1. The tree is represented by a 0-indexed array parent
    of size n, where parent[i] is the parent of node i. Since node 0 is the root, parent[0] == -1.
    You are also given a string s of length n, where s[i] is the character assigned to node i.
    Return the length of the longest path in the tree such that no pair of adjacent nodes on the path
    have the same character assigned to them.
 */

import java.util.ArrayList;
import java.util.List;

public class Exc20_LongestPathWithDifferentAdjacentCharacters {
    public int longestPath(int[] parent, String s) {
        int n = parent.length;
        List<List<Integer>> tree = new ArrayList<>();

        // Build the adjacency list for the tree
        for (int i = 0; i < n; i++) {
            tree.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            tree.get(parent[i]).add(i);
        }

        int[] result = new int[1]; // To store the global maximum path length

        // Perform DFS
        dfs(0, tree, s, result);

        return result[0];
    }

    private int dfs(int node, List<List<Integer>> tree, String s, int[] result) {
        int maxPath1 = 0, maxPath2 = 0;

        for (int child : tree.get(node)) {
            int childPath = dfs(child, tree, s, result);

            // Include the child's path only if characters are different
            if (s.charAt(node) != s.charAt(child)) {
                if (childPath > maxPath1) {
                    maxPath2 = maxPath1;
                    maxPath1 = childPath;
                } else if (childPath > maxPath2) {
                    maxPath2 = childPath;
                }
            }
        }

        // Update the global result with the sum of the two longest paths
        result[0] = Math.max(result[0], maxPath1 + maxPath2 + 1);

        // Return the longest single path from the current node
        return maxPath1 + 1;
    }

    // Main method for testing
    public static void main(String[] args) {
        Exc20_LongestPathWithDifferentAdjacentCharacters solution =
                new Exc20_LongestPathWithDifferentAdjacentCharacters();

        // Test cases
        int[] parent1 = {-1, 0, 0, 1, 1, 2};
        String s1 = "abacbe";
        System.out.println(solution.longestPath(parent1, s1)); // Output: 3

        int[] parent2 = {-1, 0, 0, 0};
        String s2 = "aabc";
        System.out.println(solution.longestPath(parent2, s2)); // Output: 3
    }
}
