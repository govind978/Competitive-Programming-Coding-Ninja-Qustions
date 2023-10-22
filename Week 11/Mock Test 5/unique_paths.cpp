/*
    Time Complexity  : O(M * N)
    Space Complexity : O(N)

    Where 'M' is the number of rows and 'N' is the number of columns of the matrix.
*/

int uniquePaths(int m, int n)
{
    // Reference array to store subproblems.
    int dp[n] = {1};

    // Bottom up approach.
    dp[0] = 1;

    for (int i = 0; i < m; i++)
    {
        for (int j = 1; j < n; j++)
        {
            dp[j] += dp[j - 1];
        }
    }

    // Returning answer.
    return dp[n - 1];
}