#include <bits/stdc++.h>
const int block = 700;

void dfs(int n, int &timer, int p, vector<vector<int>> &adj, vector<int> &in, vector<int> &out, vector<int> &ft)
{
    in[n] = timer;
    ft[timer] = n;
    timer += 1;

    for (int i = 0; i < adj[n].size(); i++)
    {
        if (adj[n][i] != p)
        {
            dfs(adj[n][i], timer, n, adj, in, out, ft);
        }
    }

    out[n] = timer;
    ft[timer] = n;
    timer += 1;
}

struct query
{
    int l, r, k, ind;
};

bool cmp(query q1, query q2)
{
    if (q1.l / block < q2.l / block)
    {
        return 1;
    }
    if (q1.l / block == q2.l / block)
    {
        if ((q1.l / block) & 1)
        {
            return q2.r > q1.r;
        }
        return q1.r > q2.r;
    }
    return 0;
}

int get_ans(int l, int r, vector<int> &bucket, vector<int> &main_freq)
{
    int ans = 0;
    for (int i = l; i <= r;)
    {
        if (i % block == 0 && i + block - 1 <= r)
        {
            ans += bucket[i / block];
            i += block;
        }
        else
        {
            ans += main_freq[i];
            i += 1;
        }
    }
    return ans;
}

void add(int ind, vector<int> &ft, vector<int> &color, vector<int> &freq, vector<int> &main_freq, vector<int> &color_freq, vector<int> &bucket, int k)
{
    if (ind == -1)
    {
        return;
    }

    freq[ft[ind]] += 1;
    if (freq[ft[ind]] == 2)
    {
        color_freq[color[ft[ind]]] += 1;
        main_freq[color_freq[color[ft[ind]]]] += 1;
        main_freq[color_freq[color[ft[ind]]] - 1] -= 1;
        bucket[color_freq[color[ft[ind]]] / block] += 1;
        bucket[(color_freq[color[ft[ind]]] - 1) / block] -= 1;
    }
}

void remove(int ind, vector<int> &ft, vector<int> &color, vector<int> &freq, vector<int> &main_freq, vector<int> &color_freq, vector<int> &bucket, int k)
{
    if (ind == -1)
    {
        return;
    }

    freq[ft[ind]] -= 1;

    if (freq[ft[ind]] == 1)
    {
        color_freq[color[ft[ind]]] -= 1;
        main_freq[color_freq[color[ft[ind]]]] += 1;
        main_freq[color_freq[color[ft[ind]]] + 1] -= 1;
        bucket[color_freq[color[ft[ind]]] / block] += 1;
        bucket[(color_freq[color[ft[ind]]] + 1) / block] -= 1;
    }
}

vector<int> queries(int n, int q, vector<int> &color, vector<vector<int>> &edges, vector<vector<int>> &queries)
{
    vector<vector<int>> adj(n + 1);

    for (int i = 0; i < edges.size(); i++)
    {
        adj[edges[i][0]].push_back(edges[i][1]);
        adj[edges[i][1]].push_back(edges[i][0]);
    }

    vector<int> in(n + 1), out(n + 1), ft(2 * n + 1), freq(n + 1), bucket(n + 1), main_freq(n + 1);

    int max_color = 0;

    for (int i = 1; i <= n; i++)
    {
        max_color = max(max_color, color[i]);
    }

    vector<int> color_freq(max_color + 1);
    int timer = 0;

    dfs(1, timer, 0, adj, in, out, ft);

    int v, k;
    query qu[q];

    for (int i = 0; i < q; i++)
    {
        int v = queries[i][0];
        int k = queries[i][1];
        qu[i].ind = i;
        qu[i].l = in[v];
        qu[i].r = out[v];
        qu[i].k = k;
    }
    sort(qu, qu + q, cmp);

    vector<int> answer(q);
    int curr_l = -1, curr_r = -1;

    for (int i = 0; i < q; i++)
    {
        int l = qu[i].l;
        int r = qu[i].r;
        int k = qu[i].k;

        while (curr_l > l)
        {
            curr_l -= 1;
            add(curr_l, ft, color, freq, main_freq, color_freq, bucket, k);
        }

        while (curr_r < r)
        {
            curr_r += 1;
            add(curr_r, ft, color, freq, main_freq, color_freq, bucket, k);
        }

        while (curr_l < l)
        {
            remove(curr_l, ft, color, freq, main_freq, color_freq, bucket, k);
            curr_l += 1;
        }

        while (curr_r > r)
        {
            remove(curr_r, ft, color, freq, main_freq, color_freq, bucket, k);
            curr_r -= 1;
        }

        answer[qu[i].ind] = get_ans(k, n, bucket, main_freq);
    }

    return answer;
}
