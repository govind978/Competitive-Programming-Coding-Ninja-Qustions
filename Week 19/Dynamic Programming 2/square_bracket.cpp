#include <bits/stdc++.h>
using namespace std;

int mod = 1e9 + 7;

int solve(int dp[][201], int op, int cl, int cur, int k, int n, int arr[])
{
    if (dp[op][cur] != -1)
    {
        return dp[op][cur];
    }
    if (op > n || (op == n && k != 0))
    {
        if (arr[cur] == 1)
            k--;
        return 0;
    }
    if (op == n)
    {
        if (arr[cur] == 1)
            k--;
        return 1;
    }
    if (arr[cur] == 1)
        k--;

    if (op - cl == 0 || arr[cur] == 1)
    {
        return dp[op][cur] = solve(dp, op + 1, cl, cur + 1, k, n, arr);
    }
    else if (op - cl > 0)
    {
        return dp[op][cur] = (solve(dp, op + 1, cl, cur + 1, k, n, arr) +
                              solve(dp, op, cl + 1, cur + 1, k, n, arr)) %
                             mod;
    }
    else
        return dp[op][cur] = 0;
}

int main()
{
    int t;
    cin >> t;
    while (t--)
    {
        int n, k;
        cin >> n >> k;
        int arr[2 * n + 1];
        memset(arr, 0, sizeof(arr));
        int dp[n + 1][201];
        memset(dp, -1, sizeof(dp));
        for (int i = 0; i < k; i++)
        {
            int x;
            cin >> x;
            arr[x] = 1;
        }
        if (arr[2 * n] == 1)
            cout << 0 << endl;
        else
            cout << solve(dp, 0, 0, 1, k, n, arr) << endl;
    }
}
