/*
    Time Complexity : O(N * V * maxFreq)
    Space Complexity : O(N * V * maxFreq)

    where, N is the size of the vector ‘coins’, V is the required
    change and ‘maxFreq’ is the maximum supply of any coin.
*/

int coinChange(int n, vector<int> &coins, vector<int> &freq, int v)
{

    int maxFreq = 1;
    for (int i = 0; i < n; i++)
    {
        maxFreq = max(maxFreq, freq[i]);
    }

    vector<vector<vector<int>>> dp(n, vector<vector<int>>(v + 1, vector<int>(maxFreq + 1, 0)));

    // Base case.
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j <= freq[i]; j++)
        {
            dp[i][0][j] = 1;
        }
    }

    // Iterate through all the coins.
    for (int i = 0; i < n; i++)
    {

        // Try to make change for all the values from 1 to v.
        for (int j = 1; j <= v; j++)
        {
            for (int k = 0; k <= freq[i]; k++)
            {

                // Check if we can take the current coin or not.
                if (j - coins[i] >= 0 and k > 0)
                {
                    dp[i][j][k] = dp[i][j - coins[i]][k - 1] % 1000000007;
                }

                // Go to the previous coin.
                if (i > 0)
                {
                    dp[i][j][k] = (dp[i][j][k] + dp[i - 1][j][freq[i - 1]]) % 1000000007;
                }
            }
        }
    }
    return dp[n - 1][v][freq[n - 1]];
}