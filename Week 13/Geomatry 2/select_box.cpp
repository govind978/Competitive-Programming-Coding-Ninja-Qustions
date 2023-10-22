double slope(int i, int j, vector<vector<int>> &vec, vector<int> &dp)
{
    int nu = 0;
    if (i != 0)
    {
        nu = vec[i - 1][0];
    }

    return (double)(dp[i] - dp[j]) / (nu - vec[j - 1][0]);
}

int selectBox(int n, vector<vector<int>> &vec)
{
    sort(vec.begin(), vec.end());

    vector<int> dp(n + 1);
    deque<int> q;
    q.push_back(0);

    for (int i = 1; i <= n; i++)
    {
        while (q.size() > 1 && slope(q[0], q[1], vec, dp) >= vec[i - 1][1])
        {
            q.pop_front();
        }
        int j = q.front();

        if (j == 0)
        {
            dp[i] = max(dp[i - 1], vec[i - 1][0] * vec[i - 1][1] - vec[i - 1][2]);
        }
        else
        {
            dp[i] = max(dp[i - 1], vec[i - 1][0] * vec[i - 1][1] - vec[i - 1][2] - vec[j - 1][0] * vec[i - 1][1] + dp[j]);
        }

        while (q.size() > 1 && slope(q[q.size() - 2], q.back(), vec, dp) <= slope(q.back(), i, vec, dp))
        {
            q.pop_back();
        }

        q.push_back(i);
    }

    return dp[n];
}
