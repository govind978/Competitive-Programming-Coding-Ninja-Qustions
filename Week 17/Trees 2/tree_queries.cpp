// Given Function.
#include <bits/stdc++.h>
// Function to get Euler Tour (Flat Tree).
void eulerTour(vector<int> adj[], int node, int parent,
               vector<int> &level, vector<int> &treeStart,
               vector<int> &treeEnd, vector<int> &rev, int &timer)
{
    if (parent == -1)
    {
        level[node] = 1;
    }
    else
    {
        level[node] = level[parent] + 1;
    }
    timer++;
    treeStart[node] = timer;
    rev[timer] = node;

    for (int child : adj[node])
    {
        eulerTour(adj, child, node, level, treeStart, treeEnd, rev, timer);
    }

    treeEnd[node] = timer;
}

vector<long long> treeQueries(vector<vector<int>> &edges,
                              vector<vector<int>> &queries)
{
    int n = edges.size() + 1;
    vector<int> adj[n], start(n + 1), finish(n + 1), treeStart(n + 1),
        level(n + 1), treeEnd(n + 1), rev(n + 1), lookup(n + 1);
    vector<long long> val(n + 1), sum(n + 1);

    // Creating adjacency from edges.
    for (vector<int> &edge : edges)
    {
        adj[edge[0]].push_back(edge[1]);
    }

    // Intitializing freq array of size sqrt(n) * N.
    int sqn = sqrt(n), curr = 0;
    vector<vector<long long>> freq((n + 1) / sqn + 1, vector<long long>(n + 1));

    // Initialize timer and compute euler tour of the given tree.
    int timer = 0;
    eulerTour(adj, 0, -1, level, treeStart, treeEnd, rev, timer);
    for (int i = 1; i <= n; i++)
    {
        int j = i;
        start[curr] = i;
        while (j <= n && j < i + sqn)
        {
            freq[curr][level[rev[j]]]++;
            lookup[j] = curr;
            j++;
        }

        i = j - 1;
        finish[curr] = j - 1;
        curr++;
    }

    vector<long long> ans;
    for (vector<int> &query : queries)
    {
        // For the query of the first type.
        if (query[0] == 1)
        {
            for (int i = 0; i < curr; i++)
            {
                val[i] += (long long)freq[i][1 + query[1]] * query[2];
            }
            sum[query[1] + 1] += query[2];
        }

        // For the query of the second type;
        else if (query[0] == 2)
        {
            int node = query[1];
            int x = lookup[treeStart[node]], y = lookup[treeEnd[node]];
            long long res = 0;
            for (int i = x; i <= y; i++)
            {
                // If the full segment is contained within the subtree.
                if (start[i] >= treeStart[node] && finish[i] <= treeEnd[node])
                {
                    res += val[i];
                }

                // If the end of the segment is contained within the subtree.
                else if (start[i] < treeStart[node])
                {
                    for (int j = treeStart[node]; j <= min(treeEnd[node], finish[i]); j++)
                    {
                        res += sum[level[rev[j]]];
                    }
                }

                // If the start of the segment is contained within the subtree.
                else if (finish[i] > treeEnd[node])
                {
                    for (int j = max(start[i], treeStart[node]); j <= treeEnd[node]; j++)
                    {
                        res += sum[level[rev[j]]];
                    }
                }
            }

            // Append the calculated answer to the ans array.
            ans.push_back(res);
        }
    }

    // Return the ans array containing answers to all the queries of the second type.
    return ans;
}
