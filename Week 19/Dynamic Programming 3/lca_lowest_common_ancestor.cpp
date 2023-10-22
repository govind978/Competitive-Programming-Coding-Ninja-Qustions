/*
    Time Complexity: O(Q * log(N) + N * log(N)).
    Space Complexity: O(N * log(N))

    where 'N' is the number of nodes, and 'Q' is the number of queries.
*/

// Initialization.
const int MAX_N = 1e5 + 10;
const int lvl = 20;
vector<int> graph[MAX_N];
int dp[MAX_N][lvl];
int depth[MAX_N];

// To find the depth and filling the sparse table of each node.
void dfs(int cur, int par)
{
    dp[cur][0] = par;
    depth[cur] = depth[par] + 1;
    // Filling the sparse table.
    for (int i = 1; i < lvl; ++i)
    {
        if (dp[cur][i - 1])
        {
            dp[cur][i] = dp[dp[cur][i - 1]][i - 1];
        }
    }

    // Iterating in the childs of the node.
    for (auto &i : graph[cur])
    {
        if (i != par)
        {
            dfs(i, cur);
        }
    }
}

int get_lca(int u, int v)
{
    // Making sure node 'u' does not have more depth than node 'v'.
    if (depth[u] > depth[v])
    {
        swap(u, v);
    }

    int d = depth[v] - depth[u];
    // Lifting node 'v' by a distance 'd' so that both the nodes have same depth.
    for (int i = 0; i < lvl; ++i)
    {
        if (d & (1 << i))
        {
            v = dp[v][i];
        }
    }
    // If both the nodes are same, we have found the LCA.
    if (u == v)
    {
        return u;
    }
    // Lifting both the nodes by a maximum distance, such that even after lifting they are not same.
    for (int i = lvl - 1; i >= 0; --i)
    {
        if (dp[u][i] != dp[v][i])
        {
            u = dp[u][i];
            v = dp[v][i];
        }
    }

    return dp[u][0];
}

vector<int> lca(int n, vector<vector<int>> edge, vector<vector<int>> query)
{
    // Initilizating all the arrays to its default value.
    for (int i = 0; i < n + 1; ++i)
    {
        graph[i].clear();
        depth[i] = 0;

        for (int j = 0; j < lvl; ++j)
        {
            dp[i][j] = 0;
        }
    }

    // Making the adjacency list.
    for (auto &i : edge)
    {
        graph[i[0]].push_back(i[1]);
        graph[i[1]].push_back(i[0]);
    }

    dfs(1, 0);
    vector<int> ans;
    for (auto &i : query)
    {
        ans.push_back(get_lca(i[0], i[1]));
    }
    return ans;
}