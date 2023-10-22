#include <iostream>
#include <vector>
#include <cstring> // for memset
#include <map>

const int K = 11;
const int LN = 17;

struct Data
{
    long long int ans[K];
    int top[K];
    int bot[K];
};

const int N = 1e4 + 5;

Data dp[LN][N];

inline long long int sqr(int x)
{
    return 1LL * x * x;
}

inline Data combine(Data down, Data up, int cur, std::vector<int> &costOfNode)
{
    Data res;

    for (int i = 1; i <= cur; ++i)
    {
        res.ans[i] = down.ans[i] + up.ans[i];
        res.top[i] = up.top[i];
        res.bot[i] = down.bot[i];

        if (!up.top[i])
        {
            res.top[i] = down.top[i];
        }

        if (!down.bot[i])
        {
            res.bot[i] = up.bot[i];
        }

        if (down.top[i] && up.bot[i])
        {
            res.ans[i] += sqr(costOfNode[down.top[i]] - costOfNode[up.bot[i]]);
        }
    }

    return res;
}

void dfs(int node, int parent, std::vector<int> &depth, std::vector<int> &colorOfNode, std::vector<std::vector<int>> &anc, std::vector<std::vector<int>> &v)
{
    depth[node] = depth[parent] + 1;
    anc[0][node] = parent;

    memset(dp[0][node].ans, 0, sizeof(dp[0][node].ans));
    memset(dp[0][node].top, 0, sizeof(dp[0][node].top));
    memset(dp[0][node].bot, 0, sizeof(dp[0][node].bot));

    dp[0][node].top[colorOfNode[node]] = node;
    dp[0][node].bot[colorOfNode[node]] = node;

    for (int next : v[node])
    {
        if (next != parent)
        {
            dfs(next, node, depth, colorOfNode, anc, v);
        }
    }
}

int answer_query(int a, int b, int cur, std::vector<int> &depth, std::vector<int> &colorOfNode, std::vector<int> &costOfNode, std::vector<std::vector<int>> &anc, std::vector<std::vector<int>> &v)
{
    if (a == b)
    {
        return 0;
    }

    if (depth[a] < depth[b])
    {
        std::swap(a, b);
    }

    int dif = depth[a] - depth[b];

    Data res1;
    Data res2;

    memset(res1.ans, 0, sizeof(res1.ans));
    memset(res2.ans, 0, sizeof(res2.ans));
    memset(res1.top, 0, sizeof(res1.top));
    memset(res2.top, 0, sizeof(res2.top));
    memset(res1.bot, 0, sizeof(res1.bot));
    memset(res2.bot, 0, sizeof(res2.bot));

    bool ok1 = false;
    bool ok2 = false;

    for (int i = 0; i < LN; ++i)
    {
        if (dif & (1 << i))
        {
            if (ok1)
            {
                res1 = combine(res1, dp[i][a], cur, costOfNode);
            }
            else
            {
                res1 = dp[i][a];
                ok1 = true;
            }
            a = anc[i][a];
        }
    }

    if (a != b)
    {
        for (int i = LN - 1; i >= 0; --i)
        {
            if (anc[i][a] != anc[i][b])
            {
                if (ok1)
                {
                    res1 = combine(res1, dp[i][a], cur, costOfNode);
                }
                else
                {
                    res1 = dp[i][a];
                    ok1 = true;
                }

                if (ok2)
                {
                    res2 = combine(res2, dp[i][b], cur, costOfNode);
                }
                else
                {
                    res2 = dp[i][b];
                    ok2 = true;
                }

                a = anc[i][a];
                b = anc[i][b];
            }
        }

        if (ok1)
        {
            res1 = combine(res1, dp[0][a], cur, costOfNode);
        }
        else
        {
            res1 = dp[0][a];
        }

        if (ok2)
        {
            res2 = combine(res2, dp[0][b], cur, costOfNode);
        }
        else
        {
            res2 = dp[0][b];
        }

        a = anc[0][a];

        for (int i = 1; i <= cur; ++i)
        {
            std::swap(res2.top[i], res2.bot[i]);
        }
    }

    res1 = combine(res1, res2, cur, costOfNode);

    long long int answer = 0;

    for (int i = 1; i <= cur; ++i)
    {
        if (i != colorOfNode[a])
        {
            answer += res1.ans[i];
        }
    }

    return answer;
}

std::vector<int> connectColor(int n, int q, std::vector<int> &color, std::vector<int> &cost, std::vector<std::vector<int>> &edges, std::vector<std::vector<int>> &query)
{
    std::vector<int> colorOfNode(n + 1);
    std::vector<int> costOfNode(n + 1);

    for (int i = 1; i <= n; i++)
        colorOfNode[i] = color[i - 1];

    for (int i = 1; i <= n; i++)
        costOfNode[i] = cost[i - 1];

    std::map<int, int> m;
    int cur = 0;

    std::vector<std::vector<int>> v(n + 1);
    std::vector<int> depth(n + 1);
    std::vector<std::vector<int>> anc(17, std::vector<int>(n + 1));

    for (int i = 1; i <= n; ++i)
    {
        m[colorOfNode[i]];
    }

    for (auto &it : m)
    {
        it.second = ++cur;
    }

    for (int i = 1; i <= n; ++i)
    {
        colorOfNode[i] = m[colorOfNode[i]];
    }

    for (int i = 0; i < edges.size(); i++)
    {
        v[edges[i][0]].pcush_back(edges[i][1]);
        v[edges[i][1]].push_back(edges[i][0]);
    }

    dfs(1, 0, depth, colorOfNode, anc, v);

    for (int i = 1; i < LN; ++i)
    {
        for (int j = 1; j <= n; ++j)
        {
            anc[i][j] = anc[i - 1][anc[i - 1][j]];
            dp[i][j] = combine(dp[i - 1][j], dp[i - 1][anc[i - 1][j]], cur, costOfNode);
        }
    }

    std::vector<int> ans;

    for (int i = 0; i < query.size(); i++)
    {
        ans.push_back(answer_query(query[i][0], query[i][1], cur, depth, colorOfNode, costOfNode, anc, v));
    }

    return ans;
}
