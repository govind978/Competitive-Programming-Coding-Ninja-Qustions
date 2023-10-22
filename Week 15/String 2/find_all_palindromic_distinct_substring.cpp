#include <bits/stdc++.h>

vector<string> distinctPalindrome(string &s)
{
    int n = s.size();

    vector<vector<bool>> dp(n, vector<bool>(n, false));
    for (int i = 0; i < n; i++)
    {
        dp[i][i] = 1;

        if (i < n && s[i] == s[i + 1])
        {
            dp[i][i + 1] = 1;
        }
    }

    for (int len = 3; len <= n; len++)
    {
        for (int i = 0; i + len - 1 < n; i++)
        {
            if (s[i] == s[i + (len - 1)] && dp[i + 1][i + (len - 1) - 1])
            {
                dp[i][i + (len - 1)] = true;
            }
        }
    }

    vector<int> kmp(n, 0);

    for (int i = 0; i < n; i++)
    {
        int j = 0, k = 1;
        while (k + i < n)
        {
            if (s[j + i] == s[k + i])
            {
                dp[k + i - j][k + i] = false;
                kmp[k++] = ++j;
            }

            else if (j > 0)
            {
                j = kmp[j - 1];
            }

            else
            {
                kmp[k++] = 0;
            }
        }
    }

    vector<string> ans;

    for (int i = 0; i < n; i++)
    {
        string str;
        for (int j = i; j < n; j++)
        {
            str += s[j];
            if (dp[i][j])
            {
                ans.push_back(str);
            }
        }
    }

    sort(ans.begin(), ans.end());

    return ans;
}