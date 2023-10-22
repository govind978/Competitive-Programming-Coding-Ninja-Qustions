
import java.util.Arrays;

public class Solution {

    private static int minCostPathHelper(int[][] cost, int n, int m, int x, int y, int[][] dp) {
        if (dp[x - 1][y - 1] != -1) {
            return dp[x - 1][y - 1];
        }

        if (x == 1 && y == 1) {
            dp[x - 1][y - 1] = cost[0][0];
            return dp[x - 1][y - 1];
        }

        int minCost = Integer.MAX_VALUE;

        if ((x - 1) > 0) {
            minCost = Math.min(minCost, cost[x - 1][y - 1] + minCostPathHelper(cost, n, m, x - 1, y, dp));
        }

        if ((y - 1) > 0) {
            minCost = Math.min(minCost, cost[x - 1][y - 1] + minCostPathHelper(cost, n, m, x, y - 1, dp));
        }

        if ((x - 1) > 0 && (y - 1) > 0) {
            minCost = Math.min(minCost, cost[x - 1][y - 1] + minCostPathHelper(cost, n, m, x - 1, y - 1, dp));
        }

        dp[x - 1][y - 1] = minCost;

        return dp[x - 1][y - 1];
    }

    public static int minCostPath(int[][] cost) {

        int n = cost.length;
        if (n == 0) {
            return Integer.MAX_VALUE;
        }

        int m = cost[0].length;
        int x = m;
        int y = n;
        int[][] dp = new int[x][y];

        for (int i = 0; i < x; i++) {
            // Initializing all elements of newly formed row with -1
            Arrays.fill(dp[i], -1);

        }

        return minCostPathHelper(cost, n, m, x, y, dp);
    }
}