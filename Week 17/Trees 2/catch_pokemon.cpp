/*
    Time Complexity: O( NlogN + Q√N )
    Space Complexity: O( NlogN )
    where ‘N’ is the number of cities and ‘Q’ is the number of queries.
*/

#include <cstring>
const int MAXN = 10005;
const int MAXM = 100005;
const int LN = 19;

int N, M, cur, A[MAXN], LVL[MAXN], DP[LN][MAXN];
int BL[MAXN << 1], ID[MAXN << 1], VAL[MAXN], ANS[MAXM];
int d[MAXN], l[MAXN], r[MAXN];
bool VIS[MAXN];
vector<int> adjList[MAXN];

struct query
{
    int id, l, r, lc;
    bool operator<(const query &rhs)
    {
        return (BL[l] == BL[rhs.l]) ? (r < rhs.r) : (BL[l] < BL[rhs.l]);
    }
} Q[MAXM];

void dfs(int u, int par)
{
    // In-time
    l[u] = ++cur;
    ID[cur] = u;

    // Binary lifting
    for (int i = 1; i < LN; i++)
        DP[i][u] = DP[i - 1][DP[i - 1][u]];

    for (int i = 0; i < adjList[u].size(); i++)
    {
        int v = adjList[u][i];
        if (v == par)
            continue;
        LVL[v] = LVL[u] + 1;
        DP[0][v] = u;
        dfs(v, u);
    }

    // Out-time
    r[u] = ++cur;
    ID[cur] = u;
}

// Lowest Common Ancestor
int lca(int u, int v)
{
    if (LVL[u] > LVL[v])
        swap(u, v);
    for (int i = LN - 1; i >= 0; i--)
    {
        if (LVL[v] - (1 << i) >= LVL[u])
            v = DP[i][v];
    }

    if (u == v)
        return u;

    for (int i = LN - 1; i >= 0; i--)
    {
        // While not LCA, move upwards
        if (DP[i][u] != DP[i][v])
        {
            u = DP[i][u];
            v = DP[i][v];
        }
    }
    return DP[0][u];
}

inline void check(int x, int &res)
{
    // If (x) occurs twice, then don't consider it's value
    if ((VIS[x]) and (--VAL[A[x]] == 0))
        res--;
    else if ((!VIS[x]) and (VAL[A[x]]++ == 0))
        res++;
    VIS[x] ^= 1;
}

void compute()
{
    // Perform standard Mo's Algorithm
    int curL = Q[0].l, curR = Q[0].l - 1, res = 0;

    for (int i = 0; i < M; i++)
    {
        // Extend left end rightwards
        while (curL < Q[i].l)
            check(ID[curL++], res);

        // Extend left end leftwards
        while (curL > Q[i].l)
            check(ID[--curL], res);

        // Extend right end rightwards
        while (curR < Q[i].r)
            check(ID[++curR], res);

        // Extend right end leftwards
        while (curR > Q[i].r)
            check(ID[curR--], res);

        int u = ID[curL], v = ID[curR];

        // If LCA not part of the range
        if (Q[i].lc != u and Q[i].lc != v)
            check(Q[i].lc, res);

        ANS[Q[i].id] = res;

        if (Q[i].lc != u and Q[i].lc != v)
            check(Q[i].lc, res);
    }
}

vector<int> countPokemon(int n, vector<pair<int, int>> edges, vector<int> poke, int nq, vector<pair<int, int>> q)
{
    cur = 0;
    memset(VIS, 0, sizeof(VIS));
    memset(VAL, 0, sizeof(VAL));
    for (int i = 1; i <= N; i++)
        adjList[i].clear();

    // Build adjacency list
    for (auto i : edges)
    {
        adjList[i.first].emplace_back(i.second);
        adjList[i.second].emplace_back(i.first);
    }

    // Coordinate compression
    N = n;
    M = q.size();
    int c = 0;
    map<int, int> mp;

    for (int i = 0; i < n; ++i)
        mp[poke[i]]++;

    // Using map to compress coordinates
    for (auto &i : mp)
        i.second = c++;

    for (int i = 1; i <= n; ++i)
        A[i] = mp[poke[i - 1]];

    // Preprocess
    DP[0][1] = 1;
    dfs(1, -1);
    int size = sqrt(cur);
    for (int i = 1; i <= cur; i++)
        BL[i] = (i - 1) / size + 1;

    // Set up ranges for Mo's algo
    for (int i = 0; i < M; i++)
    {
        int u, v;
        u = q[i].first;
        v = q[i].second;
        Q[i].lc = lca(u, v);
        if (l[u] > l[v])
            swap(u, v);

        // If LCA == U, range is [In[u],In[v]]
        if (Q[i].lc == u)
            Q[i].l = l[u], Q[i].r = l[v];
        // If LCA != U, range is [Out[u],In[v]] + LCA
        else
            Q[i].l = r[u], Q[i].r = l[v];
        Q[i].id = i;
    }

    sort(Q, Q + M);
    compute();

    vector<int> ret(M);
    for (int i = 0; i < M; ++i)
        ret[i] = ANS[i];
    return ret;
}