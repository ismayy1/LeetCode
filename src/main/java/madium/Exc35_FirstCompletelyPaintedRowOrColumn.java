package madium;
/*

 */

import java.util.HashMap;

public class Exc35_FirstCompletelyPaintedRowOrColumn {
    public int firstCompleteIndex(int[] arr, int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        // Map each number to its position (row, column) in the matrix
        HashMap<Integer, int[]> positionMap = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                positionMap.put(mat[i][j], new int[]{i, j});
            }
        }

        // Arrays to track painted cells in rows and columns
        int[] rowCount = new int[m];
        int[] colCount = new int[n];

        // Iterate through the painting order in arr
        for (int i = 0; i < arr.length; i++) {
            int[] position = positionMap.get(arr[i]);
            int row = position[0];
            int col = position[1];

            // Paint the cell by incrementing the respective row and column counts
            rowCount[row]++;
            colCount[col]++;

            // Check if the row or column is completely painted
            if (rowCount[row] == n || colCount[col] == m) {
                return i;
            }
        }

        return -1; // Should not reach here as per problem constraints
    }
}
