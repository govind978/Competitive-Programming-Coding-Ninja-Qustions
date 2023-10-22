void dfs(int cur, int parent, int sum, int &pathSum, int target, vector<int> &arr, vector<int> adj[])
{
    if (cur == target)
    {
        pathSum = sum;
        return;
    }
    for (int u : adj[cur])
    {
        if (u != parent)
        {
            dfs(u, cur, sum + arr[u], pathSum, target, arr, adj);
        }
    }
}

vector<int> pathQueries(int n, vector<int> arr, vector<vector<int>> edges, int q, vector<vector<int>> query)
{
    vector<int> ans;
    vector<int> adj[n];
    for (int i = 0; i < n - 1; i++)
    {
        adj[edges[i][0]].push_back(edges[i][1]);
        adj[edges[i][1]].push_back(edges[i][0]);
    }
    for (int i = 0; i < q; i++)
    {
        if (query[i][0] == 1)
        {
            arr[query[i][1]] = query[i][2];
        }
        else
        {
            int pathSum;
            dfs(0, -1, arr[0], pathSum, query[i][1], arr, adj);
            ans.push_back(pathSum);
        }
    }
    return ans;
}
