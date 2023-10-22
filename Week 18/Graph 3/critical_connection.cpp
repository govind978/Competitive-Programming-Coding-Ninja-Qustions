
/*
    Time Complexity : O(M + N)
    Space Complexity: O(M + N)

    Where ‘N’ is the number of nodes and ‘M’ is the number of connections.

*/

// Utility function to find all the critical connections and store it in 'ans' array
void dfs(int s, vector<vector<int>> &ans, int p, int *disc, int *low, bool *visited, vector<vector<int>> &graph, int &timer)
{

    // Mark node 's' visited
    visited[s] = 1;

    // Increament the timer by one
    timer++;

    // Store the discovery time of node 's' in 'disc'
    disc[s] = timer;

    // Initialize the 'low' of 's' with 'timer'
    low[s] = timer;

    // Run aloop from 0 to number of nodes adjacent to node 's'
    for (int i = 0; i < graph[s].size(); i++)
    {
        // Store the adjacent of 's' in 'adj' variable
        int adj = graph[s][i];

        // If node 'adj' is not visited
        if (visited[adj] == 0)
        {

            // Recursively call the function on adjacent 'adj'
            dfs(adj, ans, s, disc, low, visited, graph, timer);

            // Update the 'low' of node 's' as the minimum of 'low[adj]' and 'low[s]'
            low[s] = min(low[adj], low[s]);

            // If low of adjacent 'adj' is greater than discovery time of parent node 's'
            if (low[adj] > disc[s])
            {
                vector<int> temp;

                temp.push_back(s);
                temp.push_back(adj);
                // Push the critical connection in 'ans'
                ans.push_back(temp);
            }
        }
        // Else check if current adjacent is not the parent of 's'
        else if (adj != p)
        {

            // Update the low of 's'
            low[s] = min(low[adj], low[s]);
        }
    }

    return;
}

// Function to find all the critical connection in a network
vector<vector<int>> criticalConnections(int n, vector<vector<int>> &connections)
{

    // Take a variable to track discovery time of each node
    int timer = 0;

    // Array to store the discovery time of each node
    int *disc = new int[n];

    // Array to store the 'low' time of each node
    int *low = new int[n];

    // Array to track whether a node is visited or not
    bool *visited = new bool[n];

    // List to create graph
    vector<vector<int>> graph(n);

    int m = connections.size();

    // Run a loop from 0 to 'm' and create graph by given connections
    for (int i = 0; i < m; i++)
    {
        int u = connections[i][0];
        int v = connections[i][1];

        graph[u].push_back(v);
        graph[v].push_back(u);
    }

    // Take an array 'ans' to store all the critical connection
    vector<vector<int>> ans;

    // Run a loop from 0 to 'n' and initialize 'disc', 'low' and 'visited' array
    for (int i = 0; i < n; i++)
    {
        // Initialy  discovery time of each node is 0.
        disc[i] = 0;

        // Initialy Low of each node is INT_MAX
        low[i] = INT_MAX;

        // Initialy all nodes are unvisited
        visited[i] = false;
    }

    // Run a loop from 0 to 'n - 1' , because graph may have more than one component
    for (int i = 0; i < n; i++)
    {
        // If node is not visited
        if (visited[i] == 0)
        {

            // Call the helper function with 0 as sthe tarting node and -1 as dummy parent of 's'
            dfs(i, ans, -1, disc, low, visited, graph, timer);
        }
    }

    // Return 'ans'
    return ans;
}