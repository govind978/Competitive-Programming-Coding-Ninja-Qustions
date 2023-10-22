/*
    Time Complexity: O(N * M)
    Space Complexity: O(N * M)

    Where N and M are the number of rows,
    columns in the matrix respectivley.
*/

import java.util.ArrayList;

public class Solution {
    static class trieNode {
        trieNode left;
        trieNode right;
    }

    public static int[][] uniqueRow(int[][] matrix, int n, int m) {
        trieNode head = new trieNode();
        head.left = null;
        head.right = null;
        ArrayList<int[]> ans = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            // if the current row is unique or not.
            boolean unique = false;
            trieNode node = head;

            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 0) {
                    if (node.left == null) {
                        // New node is created and current row is unique.
                        node.left = new trieNode();
                        node.left.left = null;
                        node.left.right = null;
                        unique = true;
                    }
                    node = node.left;
                } else {
                    if (node.right == null) {
                        // New node is created and current row is unique.
                        node.right = new trieNode();
                        node.right.left = null;
                        node.right.right = null;
                        unique = true;
                    }

                    node = node.right;
                }
            }

            if (unique) {
                ans.add(matrix[i]);
            }
        }

        int res[][] = new int[ans.size()][m];
        for (int i = 0; i < ans.size(); i++) {
            res[i] = ans.get(i);
        }

        return res;
    }
}