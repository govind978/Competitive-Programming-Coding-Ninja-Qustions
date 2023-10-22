/*
    Time Complexity : O(N^2)
    Space Compleixty : O(N^2)
    where 'N' is the length of the chessboard.
*/

public class Solution {

    // Check if coordinates lie inside the board.
    private static boolean isValid(int x, int y) {
        return x >= 0 && x < 15 && y >= 0 && y < 15;
    }

    // Recursive function to cheeck if current cell is a winning state.
    private static boolean isWinning(int x, int y, int[][] dp, int[] dx, int[] dy) {
        // If value already computed, return it.
        if (dp[x][y] != -1)
            return dp[x][y] > 0;

        dp[x][y] = 0;
        for (int i = 0; i < 4; ++i) {
            // If a losing state is reachable, current state is winning.
            if (isValid(x + dx[i], y + dy[i]) && !isWinning(x + dx[i], y + dy[i], dp, dx, dy)) {
                dp[x][y] = 1;
                break;
            }
        }
        return dp[x][y] > 0;
    }

    public static String winnerOfGame(int x, int y) {
        // Decrement to make it 0-indexed.
        x--;
        y--;

        // Declare DP array and fill with -1.
        int[][] dp = new int[15][15];
        for (int i = 0; i < 15; ++i) {
            for (int j = 0; j < 15; ++j)
                dp[i][j] = -1;
        }

        // Dx and Dy to simplify iterating reachable cells.
        int[] dx = { -2, -2, 1, -1 };
        int[] dy = { 1, -1, -2, -2 };

        // If function returns true, First person wins, else second.
        if (isWinning(x, y, dp, dx, dy))
            return "First";
        else
            return "Second";
    }
}