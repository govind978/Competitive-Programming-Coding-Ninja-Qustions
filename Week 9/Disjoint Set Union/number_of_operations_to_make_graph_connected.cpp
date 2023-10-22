/*
    Time Complexity: O(N + M)
    Space Complexity: O(N)

    Where N is the total number of nodes and M is total number of edges
*/

int parent[10005];
int s[10005];

int root(int a)
{
    if (a == parent[a])
    {
        return a;
    }

    return parent[a] = root(parent[a]);
}

void dsunion(int a, int b)
{
    a = root(a);
    b = root(b);

    if (a != b)
    {
        if (s[a] < s[b])
            swap(a, b);

        parent[b] = a;
        s[a] += s[b];
    }
}

int makeGraphConnected(int n, vector<pair<int, int>> edges, int m)
{
    if (m < n - 1)
    {
        return -1;
    }

    int ans = n;
    for (int i = 1; i <= n; i++)
    {
        parent[i] = i;
        s[i] = 1;
    }

    for (int i = 0; i < m; i++)
    {
        if (root(edges[i].first) != root(edges[i].second))
        {
            dsunion(edges[i].first, edges[i].second);
            ans--;
        }
    }

    return (ans - 1);
}