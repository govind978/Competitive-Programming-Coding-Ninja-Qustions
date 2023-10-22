#include <unordered_map>
#include <set>

// DFS function.
void dfs(unordered_map<int, vector<int>> &tree, int currNode, int parentNode,
         vector<set<int>> &clrSet, vector<int> &res)
{

    // Traverse subtrees.
    for (int i = 0; i < tree[currNode].size(); ++i)
    {

        // Check if not parent.
        if (tree[currNode][i] != parentNode)
        { // Recursively call on child.
            dfs(tree, tree[currNode][i], currNode, clrSet, res);

            // Check condition.
            if (clrSet[currNode].size() < clrSet[tree[currNode][i]].size())
            {

                // Swap.
                swap(clrSet[currNode], clrSet[tree[currNode][i]]);
            }

            // Insert subtree colors.
            for (int j : clrSet[tree[currNode][i]])
            {
                clrSet[currNode].insert(j);
            }
        }
    }

    // Add result.
    res[currNode - 1] = clrSet[currNode].size();
}

// Function to add an edge.
void addEdge(unordered_map<int, vector<int>> &tree, vector<int> &edge)
{

    int u = edge[0];
    int v = edge[1];

    tree[u].push_back(v);
    tree[v].push_back(u);
}

vector<int> uniqueClr(int n, vector<vector<int>> &edges, vector<int> &color)
{

    // Create tree.
    unordered_map<int, vector<int>> tree;

    for (int i = 0; i < edges.size(); ++i)
    {
        addEdge(tree, edges[i]);
    }

    // To store unique colors for each vertex.
    vector<set<int>> clrSet(n + 1);

    // Fill clrSet array.
    for (int i = 1; i <= n; ++i)
    {
        clrSet[i].insert(color[i - 1]);
    }

    // To store the result.
    vector<int> res(n);

    // Call the helper function to fill the result array.
    dfs(tree, 1, -1, clrSet, res);

    return res;
}
