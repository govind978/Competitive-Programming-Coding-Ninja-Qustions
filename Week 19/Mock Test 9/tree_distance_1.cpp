#include <queue>
int bfs(int src, vector<int> &ans, vector<vector<int>> &tree)
{
    queue<int> Q;
    int n = ans.size();
    // distance stores the distance of ith node from src node.
    vector<int> distance(n, -1);
    distance[src] = 0;
    ans[src] = max(ans[src], distance[src]);
    Q.push(src);
    int top = Q.front();
    // While queue is not empty.
    while (!Q.empty())
    {
        top = Q.front();
        Q.pop();
        for (int node : tree[top])
        {
            if (distance[node] == -1)
            {
                // Node is not processed yet.
                Q.push(node);
                distance[node] = max(distance[node], distance[top] + 1);
                ans[node] = max(ans[node], distance[node]);
            }
        }
    }
    // Return the last visited node.
    return top;
}
vector<int> treeDistance(int n, vector<vector<int>> &edges)
{
    vector<vector<int>> tree(n);
    // Buliding tree adjacency list.
    for (int i = 0; i < n - 1; i++)
    {
        tree[edges[i][0]].push_back(edges[i][1]);
        tree[edges[i][1]].push_back(edges[i][0]);
    }
    // ans to store the distance of the farthest node.
    vector<int> ans(n, 0);
    // diameterFirst and diameterSecond are the endpoints of diameter.
    int diameterFirst = bfs(0, ans, tree);
    int diameterSecond = bfs(diameterFirst, ans, tree);
    int a = bfs(diameterSecond, ans, tree);
    return ans;
}