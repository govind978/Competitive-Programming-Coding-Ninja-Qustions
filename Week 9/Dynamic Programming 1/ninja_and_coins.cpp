int ninjaAndCoins(int n, int k, vector<int> &coins)
{
    vector<vector<int>> dp(n, vector<int>(k + 1, -1));

    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < k + 1; j++)
        {
            // Sum is equal to 0.
            if (j == 0)
            {
                dp[i][j] = 1;
                continue;
            }

            // Only one coin.
            if (i == 0)
            {
                c if (j == coins[i])
                {
                    dp[i][j] = 1;
                }
                else
                {
                    dp[i][j] = 0;
                }
                continue;
            }

            dp[i][j] = dp[i - 1][j];

            // If we can pick the i'th coin.
            if (j >= coins[i])
            {
                dp[i][j] = (dp[i][j] + dp[i - 1][j - coins[i]]) % (1000000007);
            }
        }
    }

    // Number of ways to make 'K' using all 'N' coins.
    return dp[n - 1][k];
}