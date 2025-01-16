package hard;
/*
    There is a tree (i.e. a connected, undirected graph with no cycles) consisting of n nodes numbered
    from 0 to n - 1 and exactly n - 1 edges.
    You are given a 0-indexed integer array vals of length n where vals[i] denotes the value of the
    ith node. You are also given a 2D integer array edges where edges[i] = [ai, bi] denotes that there
    exists an undirected edge connecting nodes ai and bi.
    A good path is a simple path that satisfies the following conditions:
    The starting node and the ending node have the same value.
    All nodes between the starting node and the ending node have values less than or equal to the
    starting node (i.e. the starting node's value should be the maximum value along the path).
    Return the number of distinct good paths.
    Note that a path and its reverse are counted as the same path. For example, 0 -> 1 is considered
    to be the same as 1 -> 0. A single node is also considered as a valid path.
 */

import java.util.*;

public class Exc23_NumberOfGoodPaths {
    int[] parent, count; // parent array to keep track of parent of each node and count array to keep track of number of nodes in each set
    int res; // variable to store the number of good paths
    public int numberOfGoodPaths(int[] vals, int[][] edges) {
        // sort edges based on the maximum value of the nodes in the edge
        Arrays.sort(edges, (o1, o2) -> Integer.compare(Math.max(vals[o1[0]], vals[o1[1]]), Math.max(vals[o2[0]], vals[o2[1]])));
        int n = vals.length;
        res = n; // initialize number of good paths to be equal to number of nodes
        parent = new int[n];
        count = new int[n];

        Arrays.fill(count, 1); // initialize count of nodes in each set to be 1

        for(int i = 0; i < n; i++) parent[i] = i; // initialize parent of each node to be itself
        for(int[] edge: edges) {
            union(edge[0], edge[1], vals);
        }
        return res;
    }
    // function to merge two sets
    private void union(int x, int y, int[] vals) {
        int parx = find(x);
        int pary = find(y);
        if(parx == pary) return; // if x and y are already in the same set, return
        if(vals[parx] == vals[pary]) {
            // if the values of the parents of x and y are equal, update number of good paths
            res += count[parx]*count[pary];
            count[parx] += count[pary];
            parent[pary] = parx;
        }
        else if(vals[parx] > vals[pary]) {
            parent[pary] = parx;
        } else {
            parent[parx] = pary;
        }
    }
    // function to find the parent of a node
    private int find(int x) {
        if(parent[x] == x) return x;

        return parent[x] = find(parent[x]);
    }
}
