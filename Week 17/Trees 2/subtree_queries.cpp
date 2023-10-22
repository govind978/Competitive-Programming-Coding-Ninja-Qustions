#define int long long

vector<vector<int>> tree;
vector<int> val;
vector<int> in, out, A;
int timer;

vector<int> segtree;

void dfs(int curr, int parent)
{
    in[curr] = timer++;
    A[in[curr]] = val[curr];

    for (int neighbour : tree[curr])
    {
        if (neighbour != parent)
            dfs(neighbour, curr);
    }

    out[curr] = timer;
}

void build(int beg, int end, int pos)
{
    if (beg == end)
    {
        segtree[pos] = A[beg];
        return;
    }

    int mid = (beg + end) / 2;
    build(beg, mid, 2 * pos + 1);
    build(mid + 1, end, 2 * pos + 2);

    segtree[pos] = segtree[2 * pos + 1] + segtree[2 * pos + 2];
}

int query(int beg, int end, int ql, int qr, int pos)
{
    if (ql <= beg && qr >= end)
        return segtree[pos];
    else if (qr < beg || ql > end)
        return 0;

    int mid = (beg + end) / 2;
    return query(beg, mid, ql, qr, 2 * pos + 1) + query(mid + 1, end, ql, qr, 2 * pos + 2);
}

void update(int beg, int end, int idx, int val, int pos)
{
    if (beg == end)
    {
        segtree[pos] = val;
        return;
    }

    int mid = (beg + end) / 2;

    if (idx <= mid)
        update(beg, mid, idx, val, 2 * pos + 1);
    else
        update(mid + 1, end, idx, val, 2 * pos + 2);

    segtree[pos] = segtree[2 * pos + 1] + segtree[2 * pos + 2];
}

void resetAll(int N)
{
    val.resize(N + 1);
    in.resize(N + 1);
    out.resize(N + 1);
    A.resize(N + 1);

    segtree.resize(4 * N + 5);
    timer = 0;

    tree.resize(N + 1);
    for (int i = 0; i <= N; i++)
        tree[i].clear();
}

vector<long long> treeSum(int N, int Q, vector<int> &V, vector<vector<int>> &edges, vector<vector<int>> &queries)
{
    resetAll(N);

    for (int i = 1; i <= N; i++)
    {
        val[i] = V[i - 1];
    }

    for (vector<int> curr : edges)
    {
        tree[curr[0]].push_back(curr[1]);
        tree[curr[1]].push_back(curr[0]);
    }

    dfs(1, 0);
    build(0, N - 1, 0);
    vector<int> ans;

    for (vector<int> qu : queries)
    {
        if (qu[0] == 1)
        {
            int node = qu[1], weight = qu[2];
            update(0, N - 1, in[node], weight, 0);
        }
        else
        {
            int node = qu[1];
            int sum = query(0, N - 1, in[node], out[node] - 1, 0);
            ans.push_back(sum);
        }
    }

    return ans;
}
