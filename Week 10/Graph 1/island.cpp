#include <iostream>
#include <vector>

using namespace std;

vector<int> parent;

int findSet(int u)
{
    if (parent[u] == u)
    {
        return u;
    }
    return parent[u] = findSet(parent[u]);
}

void unionSets(int u, int v)
{
    u = findSet(u);
    v = findSet(v);
    if (u != v)
    {
        parent[u] = v;
    }
}

int countConnectedGroups(int N, vector<pair<int, int>> &connections)
{
    parent.assign(N, 0);
    for (int i = 0; i < N; i++)
    {
        parent[i] = i;
    }

    for (auto &connection : connections)
    {
        int u = connection.first;
        int v = connection.second;
        unionSets(u, v);
    }

    int connectedGroups = 0;
    for (int i = 0; i < N; i++)
    {
        if (parent[i] == i)
        {
            connectedGroups++;
        }
    }

    return connectedGroups;
}

int main()
{
    int T;
    cin >> T;
    while (T--)
    {
        int N, M;
        cin >> N >> M;
        vector<pair<int, int>> connections;
        for (int i = 0; i < M; i++)
        {
            int u, v;
            cin >> u >> v;
            connections.push_back({u, v});
        }
        int result = countConnectedGroups(N, connections);
        cout << result << endl;
    }
    return 0;
}
