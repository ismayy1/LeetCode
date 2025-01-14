package madium;
/*
    Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an
    integer k, return the k closest points to the origin (0, 0).
    The distance between two points on the X-Y plane is the Euclidean distance
    (i.e., √(x1 - x2)2 + (y1 - y2)2).
    You may return the answer in any order. The answer is guaranteed to be unique (except for the
    order that it is in).
 */

import java.util.Arrays;
import java.util.PriorityQueue;

public class Exc29_KClosestPointsToOrigin {
    public int[][] kClosest(int[][] points, int k) {
        // Create a max-heap with a comparator based on the Euclidean distance
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
                (a, b) -> Integer.compare(b[0] * b[0] + b[1] * b[1], a[0] * a[0] + a[1] * a[1])
        );

        // Add points to the heap
        for (int[] point : points) {
            maxHeap.offer(point);
            // Keep the heap size at most k
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        // Extract the k closest points from the heap
        int[][] result = new int[k][2];
        int index = 0;
        while (!maxHeap.isEmpty()) {
            result[index++] = maxHeap.poll();
        }

        return result;
    }

    // Main method for testing
    public static void main(String[] args) {
        Exc29_KClosestPointsToOrigin solution = new Exc29_KClosestPointsToOrigin();

        int[][] points = {{1, 3}, {-2, 2}, {5, 8}, {0, 1}};
        int k = 2;

        int[][] result = solution.kClosest(points, k);

        // Print the result
        System.out.println(Arrays.deepToString(result));
        // Example Output: [[-2, 2], [0, 1]] (or similar valid arrangement)
    }
}
