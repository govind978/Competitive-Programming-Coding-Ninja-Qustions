int bipartite(vector<vector<int>> &edges, int n)
{
    vector<int> counts(2);
    vector<int> tree[n + 1];

    for (int i = 0; i < n - 1; i++)
    {
        int u = edges[i][0];
        int v = edges[i][1];
        tree[u].push_back(v);
        tree[v].push_back(u);
    }

    queue<vector<int>> q;
    q.push({1, 0, 0});

    while (!q.empty())
    {
        vector<int> qTop = q.front();
        int cur = qTop[0], last = qTop[1], parity = qTop[2];
        q.pop();
        counts[parity % 2] += 1;

        // Changing the parity.
        parity = (parity + 1) % 2;

        for (int child : tree[cur])
        {
            // If child is not equal to parent.
            if (child != last)
            {
                q.push({child, cur, parity});
            }
        }
    }

    return (counts[0] * counts[1] - (n - 1));
}
