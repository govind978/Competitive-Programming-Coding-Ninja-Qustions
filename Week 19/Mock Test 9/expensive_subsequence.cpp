/*
Time Complexity: O(N * M)
Space Complexity: O(N * M)
where 'N' and 'M' are length of the first and the second string, respectively.
*/
int findCost(vector<int> &cost, string str1, string str2)
{
    // Store size of first string in n.
    int n = str1.length();
    // Store size of second string in m.
    int m = str2.length();
    // Create a 2D array 'dp'.
    vector<vector<int>> dp(n + 1, vector<int>(m + 1, 0));
    // Iterate i in 0 to n:
    for (int i = 0; i < n; i++)
    {
        // Iterate j in 0 to m:
        for (int j = 0; j < m; j++)
        {
            // If current characaters of both string are equal:
            if (str1[i] == str2[j])
            {
                dp[i + 1][j + 1] = dp[i][j] + cost[str1[i] - 97];
            }
            // Otherwise, if current characaters of both string are not equal:
            else
            {
                dp[i + 1][j + 1] = max(dp[i][j + 1], dp[i + 1][j]);
            }
        }
    }
    // Return dp[n][m].
    return dp[n][m];
}