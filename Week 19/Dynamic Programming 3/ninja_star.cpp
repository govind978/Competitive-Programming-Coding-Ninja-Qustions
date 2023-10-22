vector<vector<char>> draw(vector<vector<int>> &ans, int n, int m)
{
    vector<vector<char>> res(n, vector<char>(m, '.'));

    vector<vector<int>> dp1(n, vector<int>(m)), dp2 = dp1;

    for (int i = 0; i < ans.size(); i++)
    {
        int x = ans[i][0];
        int y = ans[i][1];
        int len = ans[i][2];

        if (y - len >= 0)
        {
            ++dp1[x][y - len];
        }
        if (y + len + 1 < m)
        {
            --dp1[x][y + len + 1];
        }

        if (x - len >= 0)
        {
            ++dp2[x - len][y];
        }
        if (x + len + 1 < n)
        {
            --dp2[x + len + 1][y];
        }
    }

    for (int i = 0; i < n; ++i)
    {
        for (int j = 0; j < m; ++j)
        {
            if (i > 0)
                dp2[i][j] += dp2[i - 1][j];
            if (j > 0)
                dp1[i][j] += dp1[i][j - 1];
            if (dp1[i][j] > 0 || dp2[i][j] > 0)
                res[i][j] = '*';
        }
    }

    return res;
}

vector<vector<int>> ninjaStar(vector<vector<char>> &a, int n, int m)
{
    vector<vector<int>> ans;

    vector<vector<int>> u(n, vector<int>(m, 0)), d = u, l = u, r = u;

    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            if (i > 0)
            {
                if (a[i][j] != '.')
                    u[i][j] = u[i - 1][j] + 1;
            }
            else
            {
                if (a[i][j] != '.')
                {
                    u[i][j] = 1;
                }
                else
                    u[i][j] = 0;
            }
            if (j > 0)
            {
                if (a[i][j] != '.')
                    l[i][j] = l[i][j - 1] + 1;
            }
            else
            {
                if (a[i][j] != '.')
                {
                    l[i][j] = 1;
                }
                else
                    l[i][j] = 0;
            }
        }
    }

    for (int i = n - 1; i >= 0; --i)
    {
        for (int j = m - 1; j >= 0; --j)
        {
            if (i < n - 1)
            {
                if (a[i][j] != '.')
                    d[i][j] = d[i + 1][j] + 1;
            }
            else
            {
                if (a[i][j] != '.')
                {
                    d[i][j] = 1;
                }
                else
                    d[i][j] = 0;
            }
            if (j < m - 1)
            {
                if (a[i][j] != '.')
                    r[i][j] = r[i][j + 1] + 1;
            }
            else
            {
                if (a[i][j] != '.')
                {
                    r[i][j] = 1;
                }
                else
                    r[i][j] = 0;
            }
        }
    }

    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            if (a[i][j] == '*')
            {
                int rayLength = min(min(u[i][j], d[i][j]), min(l[i][j], r[i][j])) - 1;

                if (rayLength > 0)
                {
                    ans.push_back({i, j, rayLength});
                }
            }
        }
    }

    if (draw(ans, n, m) == a)
    {
        vector<vector<int>> finalAns(ans.size() + 1, vector<int>(3, -1));

        finalAns[0][0] = ans.size();

        for (int i = 0; i < ans.size(); i++)
        {
            finalAns[i + 1][0] = ans[i][0];
            finalAns[i + 1][1] = ans[i][1];
            finalAns[i + 1][2] = ans[i][2];
        }

        return finalAns;
    }

    vector<vector<int>> finalAns(1, vector<int>(3, -1));

    return finalAns;
}
