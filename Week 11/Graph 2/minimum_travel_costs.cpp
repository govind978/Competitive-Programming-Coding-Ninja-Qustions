/*
    Time Complexity: O(M*logN)
    Space Complexity: O(N + M)

    Where N is the number of states in the country.
    And M is number of roads.
*/

vector<vector<int>> findMST(vector<vector<int>> &road, int n, int m)
{
    // Creating adjacency list representation of country graph from edge list.
    vector<pair<int, int>> adjList[n + 1];

    for (int i = 0; i < m; i++)
    {
        int first = road[i][0];
        int second = road[i][1];
        int cost = road[i][2];

        adjList[first].push_back(make_pair(second, cost));
        adjList[second].push_back(make_pair(first, cost));
    }

    vector<bool> visited(n + 1);
    vector<int> parent(n + 1);
    vector<int> weight(n + 1);

    for (int i = 1; i <= n; i++)
    {
        visited[i] = false;
        parent[i] = i;
        weight[i] = INT_MAX;
    }

    // We will take 1 as our source vertex.
    weight[1] = 0;

    set<pair<int, int>> minStates;

    for (int i = 1; i <= n; i++)
    {
        minStates.insert(make_pair(weight[i], i));
    }

    while (!minStates.empty())
    {
        pair<int, int> temp = *(minStates.begin());
        minStates.erase(minStates.begin());

        visited[temp.second] = true;

        for (auto neighbours : adjList[temp.second])
        {
            int node = neighbours.first;
            int cost = neighbours.second;

            if (visited[node] == false && cost < weight[node])
            {
                minStates.erase(make_pair(weight[node], node));
                weight[node] = cost;
                minStates.insert(make_pair(weight[node], node));
                parent[node] = temp.second;
            }
        }
    }

    vector<vector<int>> mstEdges;

    for (int i = 2; i <= n; i++)
    {
        int first = i;
        int second = parent[i];

        mstEdges.push_back({first, second, weight[i]});
    }

    return mstEdges;
}
