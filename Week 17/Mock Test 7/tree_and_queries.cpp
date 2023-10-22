#include <bits/stdc++.h>
using namespace std;

void addEdge(unordered_map<int, vector<vector<int>>> &tree, vector<int> edge)
{
    int u = edge[0];
    int v = edge[1];
    int w = edge[2];

    vector<int> tempNode;
    tempNode.push_back(v);
    tempNode.push_back(w);

    tree[u].push_back(tempNode);

    tempNode[0] = u;
    tree[v].push_back(tempNode);
}

int get(vector<int> &bit, int x)
{
    int ans = 0;

    while (x > 0)
    {
        ans ^= bit[x];
        x -= x & (-x);
    }

    return ans;
}

void add(vector<int> &bit, int n, int x, int v)
{
    while (x <= n)
    {
        bit[x] ^= v;
        x += x & (-x);
    }
}

void dfs(unordered_map<int, vector<vector<int>>> &tree, int u, int parent, int &timer, vector<int> &st, vector<int> &en)
{
    st[u] = ++timer;

    for (int i = 0; i < tree[u].size(); ++i)
    {
        if (tree[u][i][0] != parent)
        {
            dfs(tree, tree[u][i][0], u, timer, st, en);
        }
    }

    en[u] = timer;
}

vector<int> XORquery(int n, vector<vector<int>> &edges, int q, vector<vector<int>> &query)
{
    unordered_map<int, vector<vector<int>>> tree;

    for (int i = 0; i < edges.size(); ++i)
    {
        addEdge(tree, edges[i]);
    }

    int timer = 0;

    vector<int> st(100010);
    vector<int> en(100010);
    dfs(tree, 1, -1, timer, st, en);

    vector<int> node1(100010);
    vector<int> node2(100010);

    vector<vector<int>> sortQ;

    for (int i = 0; i < n - 1; ++i)
    {
        if (st[edges[i][0]] < st[edges[i][1]])
        {
            swap(edges[i][0], edges[i][1]);
        }

        vector<int> temp;
        temp.push_back(edges[i][2]);
        temp.push_back(-edges[i][0]);
        sortQ.push_back(temp);
    }

    for (int i = 1; i <= q; ++i)
    {
        node1[i] = query[i - 1][0];
        node2[i] = query[i - 1][1];

        vector<int> temp;
        temp.push_back(query[i - 1][2]);
        temp.push_back(i);
        sortQ.push_back(temp);
    }

    sort(sortQ.begin(), sortQ.end());

    vector<int> bit(100010);
    vector<int> tempRes(100010);

    for (int i = 0; i < sortQ.size(); ++i)
    {
        if (sortQ[i][1] < 0)
        {
            int node = -sortQ[i][1];
            add(bit, n, st[node], sortQ[i][0]);
            add(bit, n, en[node] + 1, sortQ[i][0]);
        }
        else
        {
            int idx = sortQ[i][1];
            tempRes[idx] = get(bit, st[node1[idx]]) ^ get(bit, st[node2[idx]]);
        }
    }

    vector<int> res;

    for (int i = 1; i <= q; ++i)
    {
        res.push_back(tempRes[i]);
    }

    return res;
}
