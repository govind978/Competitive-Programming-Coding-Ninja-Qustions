/*
    Time Complexity: O(N)
    Space Complexity: O(N)

   Where ‘N’ is the number of nodes in the tree.
*/

long long int solve(int node, int parentNode, bool b, vector<int> v[], vector<int> &c, vector<vector<long long int>> &dp)
{

    if (dp[node][b] != -1)
    {
        return dp[node][b];
    }

    long long int sum = 0;

    for (int i = 0; i < (int)v[node].size(); i++)
    {
        if (v[node][i] == parentNode)
        {
            continue;
        }
        if (b == 1)
        {
            // Since we are taking current node, we need to skip all the child nodes.
            sum += solve(v[node][i], node, 0, v, c, dp);
        }
        else
        {
            // We have an option to either take or skip child nodes.
            sum += max(solve(v[node][i], node, 0, v, c, dp), solve(v[node][i], node, 1, v, c, dp));
        }
    }
    if (b == 1)
    {
        sum += c[node];
    }

    dp[node][b] = sum;

    return sum;
}

long long int maximizeProfit(int n, vector<int> c, vector<vector<int>> edges)
{

    // Creating an adjacency vector.
    vector<int> v[n];

    // Creating 2d vector of size 'N' * 2 initialized to -1.
    vector<vector<long long int>> dp(n, {-1, -1});

    for (int i = 0; i + 1 < n; i++)
    {
        int x = edges[i][0];
        int y = edges[i][1];

        x--;
        y--;

        // Adding edges.
        v[x].push_back(y);
        v[y].push_back(x);
    }

    // Final answer is going to be the maximum of either taking the root node or skipping it.
    long long int ans = max(solve(0, 0, 1, v, c, dp), solve(0, 0, 0, v, c, dp));

    return ans;
}