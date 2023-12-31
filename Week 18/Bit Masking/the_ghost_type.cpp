#include <bits/stdc++.h>

using namespace std;
#define ll long long
ll dp[1 << 20];
ll solve(int n)
{
    int mask = (1 << n) - 1;
    memset(dp, 0, sizeof(dp));
    dp[0] = 1;
    for (int i = 0; i < mask; i++)
    {
        for (int j = 0; j < n; j++)
        {
            if ((1 << j & i) == 0)
            {
                bool flag = false;
                for (int k = 0; k < n; k++)
                {
                    if (((j + 1) & (k + 1)) == j + 1 && ((1 << k) & i))
                    {
                        flag = true;
                        break;
                    }
                }
                if (flag == false)
                {
                    dp[1 << j | i] += dp[i];
                }
            }
        }
    }
    return dp[mask];
}
int main()
{

    // write your code here
    int n;
    cin >> n;
    cout << solve(n);
    return 0;
}
