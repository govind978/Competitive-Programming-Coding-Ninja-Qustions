// int maxProfit(vector<int> &values, vector<int> &weights, int n, int w)
// {
// 	// Write your code here
// }

#include <vector>

int maxProfit(vector<int> value, vector<int> weight, int n, int maxWeight)
{
    vector<vector<int>> dp(2, vector<int>(maxWeight + 1, 0));

    for (int i = 0; i < n; i++)
    {
        for (int j = 1; j <= maxWeight; ++j)
        {
            if (weight[i] <= j)
            {
                dp[i % 2][j] = max(value[i] + dp[(1 + i) % 2][j - weight[i]], dp[(i + 1) % 2][j]);
            }
            else
            {
                dp[i % 2][j] = dp[(i + 1) % 2][j];
            }
        }
    }

    return dp[(n + 1) % 2][maxWeight];
}