/*
    Time Complexity : O(N!)
    Space Complexity : O(N)

    Where N is the number of Queens.
*/

import java.util.ArrayList;

public class Solution {

    static private void addSolution(int n, ArrayList<ArrayList<Integer>> ans, int[] col) {
        ArrayList<Integer> currentAnswer = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (col[j] == i) {
                    currentAnswer.add(1);
                } else {
                    currentAnswer.add(0);
                }
            }
        }

        ans.add(currentAnswer);
    }

    static private void solveNQueensHelper(int row, int n, ArrayList<ArrayList<Integer>> ans, int[] col, int[] d1,
            int[] d2) {
        if (row == n) {
            addSolution(n, ans, col);
            return;
        }

        for (int i = 0; i < n; i++) {
            if ((col[i] == -1) && (d1[row - i + n - 1] == -1) && (d2[row + i] == -1)) {

                col[i] = d1[row - i + n - 1] = d2[row + i] = row;
                solveNQueensHelper(row + 1, n, ans, col, d1, d2);
                col[i] = d1[row - i + n - 1] = d2[row + i] = -1;

            }
        }
    }

    public static ArrayList<ArrayList<Integer>> solveNQueens(int n) {

        int[] col = new int[31];
        int[] d1 = new int[31];
        int[] d2 = new int[31];

        for (int i = 0; i <= 30; i++) {
            col[i] = -1;
            d1[i] = -1;
            d2[i] = -1;
        }

        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        solveNQueensHelper(0, n, ans, col, d1, d2);
        return ans;
    }
}