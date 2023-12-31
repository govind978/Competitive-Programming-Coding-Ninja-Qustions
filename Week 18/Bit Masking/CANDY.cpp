
#include <bits/stdc++.h>
using namespace std;
#define int long long
int candies(int **like, int n, int person, int mask, int *dp)
{
    if (person >= n)
    {
        return 1;
    }

    if (dp[mask] != -1)
    {
        return dp[mask];
    }
    int ans = 0;
    for (int i = 0; i < n; i++)
    {
        if (!(mask & (1 << i)) && like[person][i])
        {
            ans += candies(like, n, person + 1, mask | (1 << i), dp);
        }
    }
    dp[mask] = ans;
    return ans;
}
int solve(int **like, int n)
{
    int *dp = new int[1 << n];
    for (int i = 0; i < (1 << n); i++)
    {
        dp[i] = -1;
    }
    int ans = candies(like, n, 0, 0, dp);
    delete[] dp;
    return ans;
}
int32_t main()
{
    int t;
    cin >> t;
    while (t--)
    {
        int n;
        cin >> n;
        int **like = new int *[n];

        for (int i = 0; i < n; i++)
        {

            like[i] = new int[n];
        }
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                cin >> like[i][j];
            }
        }

        cout << solve(like, n) << endl;
    }
}