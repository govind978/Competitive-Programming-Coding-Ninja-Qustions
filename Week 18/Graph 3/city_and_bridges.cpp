#include <unordered_set>

void articulation(vector<vector<int>> &arr, int u, int par, unordered_set<int> &art, vector<bool> &vis, int &tme, vector<int> &low, vector<int> &discovery)
{

    vis[u] = true;

    ++tme;

    int children = 0;

    // Initialize discovery time and low value.
    low[u] = discovery[u] = tme;

    // Go through all vertices aadjacent to u.
    for (int i = 0; i < arr[u].size(); i++)
    {

        // 'adjacent' is current adjacent of u
        int adjacent = arr[u][i];
        if (adjacent == par)
        {
            continue;
        }

        if (!vis[adjacent])
        {
            ++children;
            articulation(arr, adjacent, u, art, vis, tme, low, discovery);

            /*
                Check if the subtree rooted with 'adjacent' has a connection to
                one of the ancestors of u.
            */
            low[u] = min(low[u], low[adjacent]);

            // u is an articulation point in following cases.
            // (1) u is root of DFS tree and has two or more chilren.
            if (par == -1 and children > 1)
            {
                art.insert(u);
            }

            /*
                (2) If u is not root and low value of
                one of its child is more than discovery value of u.
            */
            if (par != -1 and low[adjacent] >= discovery[u])
            {
                art.insert(u);
            }
        }

        // Update low value of u for parent function calls.
        else
        {
            low[u] = min(low[u], discovery[adjacent]);
        }
    }
}

void cityAndBridges(vector<vector<int>> &arr, int n, int m, int q, vector<int> &query)
{
    unordered_set<int> art;
    int tme = 0;
    vector<bool> vis(n, false);
    vector<int> low(n, -1);
    vector<int> discovery(n, -1);

    for (int i = 0; i < n; i++)
    {
        if (!vis[i])
        {
            articulation(arr, i, -1, art, vis, tme, low, discovery);
        }
    }

    for (int i = 0; i < q; i++)
    {
        if (art.find(query[i]) != art.end())
        {
            cout << "Yes"
                 << " ";
        }

        else
        {
            cout << "No"
                 << " ";
        }
    }
}