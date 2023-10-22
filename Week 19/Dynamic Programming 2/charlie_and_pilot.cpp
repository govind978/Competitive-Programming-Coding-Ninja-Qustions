#include <bits/stdc++.h>
using namespace std;

int f(int *a, int id, int n, int x, int *c, int **dp)
{
    if (id >= n) // PROBLEM IS TO MAKE PLANE CREWS WHICH CONTAINS A PILOT AND ASSISTANT
        return 0;
    if (dp[id][x] != -1)
        return dp[id][x];
    int ans;
    if (x == 0) // FIRST SHOULD BE ASSISTANT COZ IYS SALARY SHOULD BE MINIMUM
    {
        ans = a[id] + f(a, id + 1, n, 1, c, dp);
        dp[id][x] = ans;
        return ans;
    }
    else if (x == n - id) // CPTAIN COMES
    {
        ans = c[id] + f(a, id + 1, n, x - 1, c, dp); // X IS MAINTAINED BY  A-C
        dp[id][x] = ans;
        return ans;
    }
    else
    {
        int op1 = a[id] + f(a, id + 1, n, x + 1, c, dp);
        int op2 = c[id] + f(a, id + 1, n, x - 1, c, dp);
        ans = min(op1, op2);
        // dp[n][x]=ans;
        // return ans;
    }
    /*  for(int i=0;i<=n;i++)
       {
           for(int j=0;j<=n;j++)
           {
               cout<<dp[i][j]<<"   ";
           }
           cout<<endl;
       }*/
    dp[id][x] = ans;
    return ans;
}
int main()
{
    int t;
    cin >> t;
    while (t--)
    {
        int i, j, k, l, x, z, m, n;
        cin >> n;
        int c[n];
        int a[n];
        for (i = 0; i < n; i++)
            cin >> c[i] >> a[i];
        int **dp = new int *[n + 1];
        for (int i = 0; i <= n; i++)
        {
            dp[i] = new int[n / 2 + 1];
        }
        for (int i = 0; i <= n; i++)
        {
            for (int j = 0; j <= n / 2; j++)
            {
                dp[i][j] = -1;
            }
        }
        cout << f(a, 0, n, 0, c, dp) << endl;
        for (int i = 0; i <= n; i++)
        {
            delete[] dp[i];
        }
        delete[] dp;
    }
    return 0;
}