/*
    Time Complexity : O('N * M')
    Space Complexity : O('N')

    Where 'N' is the number of nodes, and 'M' is the number of edges.
*/

long long int minEffortsRequired(vector<pair<pair<int, int>, long long int>> &edges, int n, int src, int dest)
{

    if (src == dest)
    {
        return 0;
    }

    long long int inf = 1e18;

    vector<long long int> efforts(n + 1, inf);
    int m = edges.size();

    efforts[src] = 1;

    // Relaxing the edges, 'N-1' times.
    for (int i = 1; i <= n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            int u = edges[j].first.first;
            int v = edges[j].first.second;
            long long int wt = edges[j].second;
            efforts[v] = min(efforts[v], efforts[u] * wt);
        }
    }

    /*
        This means there is no possible way,
        to reach from source to destination.
    */
    if (efforts[dest] == inf)
    {
        return -1;
    }

    return efforts[dest];
}