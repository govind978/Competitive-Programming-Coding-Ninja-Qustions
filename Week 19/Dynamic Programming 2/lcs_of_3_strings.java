/*
    Time Complexity: O(n*m*k)
    Space Complexity: O(n*m*k)

    where n, m, and k are the lengths of string A, B, C respectively.
*/

public class Solution {

    public static int LCS(String A, String B, String C) {
        int n = A.length();
        int m = B.length();
        int k = C.length();

        int[][][] dp = new int[n + 1][m + 1][k + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                for (int t = 0; t <= k; t++) {

                    if (i == 0 || j == 0 || t == 0) {
                        dp[i][j][t] = 0;
                    }

                    else if (A.charAt(i - 1) == B.charAt(j - 1) && A.charAt(i - 1) == C.charAt(t - 1)) {
                        dp[i][j][t] = 1 + dp[i - 1][j - 1][t - 1];
                    }

                    else {
                        dp[i][j][t] = Math.max(Math.max(dp[i - 1][j][t], dp[i][j - 1][t]), dp[i][j][t - 1]);
                    }
                }
            }
        }

        return dp[n][m][k];
    }
}
