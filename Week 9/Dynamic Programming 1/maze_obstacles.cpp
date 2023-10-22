/*
    Time Complexity: O( N * M ).
    Space Complexity: O( N * M ).

    Where N * M is the size of input matrix.
*/

int mazeObstacles(int n, int m, vector<vector<int>> &mat)
{

    // dp table to store the already calculated values.
    vector<vector<int>> dp(n, vector<int>(m, 0));
    // Base case for first cell.
    dp[0][0] = 1;

    // Fill the first column.
    for (int i = 1; i < n; i++)
    {
        if (mat[i][0] == 0)
        {
            dp[i][0] = dp[i - 1][0];
        }
    }
    // Fill the first row.
    for (int i = 1; i < m; i++)
    {
        if (mat[0][i] == 0)
        {
            dp[0][i] = dp[0][i - 1];
        }
    }

    int mod = 1e9 + 7;
    // Calculate for each cell dp[i][j].
    for (int i = 1; i < n; i++)
    {
        for (int j = 1; j < m; j++)
        {
            if (mat[i][j] == 0)
            {
                dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % mod;
            }
        }
    }
    // Return the number of ways to reach the bottom-right cell in the maze.
    return dp[n - 1][m - 1];
}