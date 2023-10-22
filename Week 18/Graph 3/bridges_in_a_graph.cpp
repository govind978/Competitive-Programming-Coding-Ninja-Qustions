/*
    Time Complexity : O(V + E)
    Space Complexoty : O(V)

    Where V is the number of vertices and E is the number of edges in the graph.
*/

void dfs(vector<vector<int>> &graph, vector<vector<int>> &allBridges, vector<bool> &visited, vector<int> &in, vector<int> &low, int parent, int currentVertex, int &time)
{
    // Mark currentVertex as visited.
    visited[currentVertex] = true;

    // Initialize discovery time and low value
    low[currentVertex] = in[currentVertex] = ++time;

    // Iterate all the adjacent vertices to currentVertex node.
    for (int i = 0; i < graph[currentVertex].size(); i++)
    {
        // child is the current adjacent vertex of currentVertex
        int child = graph[currentVertex][i];

        // If child is not visited yet then recur for it.
        if (!visited[child])
        {
            dfs(graph, allBridges, visited, in, low, currentVertex, child, time);

            // Check if the subtree rooted with child has a connection to one of the
            // ancestors of currentVertex
            low[currentVertex] = min(low[child], low[currentVertex]);

            if (low[child] > in[currentVertex])
            {
                // Add an edge(currentVertex-child) to answer.
                vector<int> edge(2);
                edge[0] = currentVertex;
                edge[1] = child;
                allBridges.push_back(edge);
            }
        }

        else if (child != parent)
        {
            // If adjacent vertex is alreedy visited before, then update the low of currentVertex.
            low[currentVertex] = min(low[currentVertex], in[child]);
        }
    }
}

// Function to add edge to graph.
void addedge(vector<vector<int>> &graph, int a, int b)
{
    graph[a].push_back(b);
    graph[b].push_back(a);
}

vector<vector<int>> findBridges(vector<vector<int>> &edges, int v, int e)
{
    vector<vector<int>> graph(v);

    // Adding edges in the graph.
    for (int ei = 0; ei < e; ei++)
    {
        int a = edges[ei][0];
        int b = edges[ei][1];

        addedge(graph, a, b);
    }

    // Initialise time to 0
    int time = 0;

    // It keep track of visited vertices.
    vector<bool> visited(v);

    // It stores discovery time of every vertex
    vector<int> in(v);

    // For every vertex it stores, the discovery time of the earliest discovered
    // vertex to which or any of the vertices in the subtree rooted at is
    // having a back edge.
    vector<int> low(v);

    vector<vector<int>> allBridges;

    // Call the recursive helper function to find bridges in DFS tree rooted with
    // vertex i.
    for (int i = 0; i < v; i++)
    {
        if (!visited[i])
        {
            dfs(graph, allBridges, visited, in, low, -1, i, time);
        }
    }

    return allBridges;
}