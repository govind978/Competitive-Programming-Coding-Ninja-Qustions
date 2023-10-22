#include <bits/stdc++.h>
using namespace std;

int arr[100004];
pair<int, int> tree[400004];

void build(int st, int l, int r)
{
    if (l == r)
    {
        tree[st].first = arr[l];
        tree[st].second = l;
        return;
    }
    int mid = (l + r) / 2;
    int lef = 2 * st;
    int rit = 2 * st + 1;

    build(lef, l, mid);
    build(rit, mid + 1, r);

    tree[st] = max(tree[lef], tree[rit]);
}

void update(int st, int l, int r, int idx, int val)
{
    if (l == r)
    {
        tree[st].first = val;
        tree[st].second = idx;
        return;
    }
    int lef = st + st;
    int rit = lef + 1;
    int mid = (l + r) / 2;

    if (idx >= l && idx <= mid)
        update(lef, l, mid, idx, val);
    else
        update(rit, mid + 1, r, idx, val);

    tree[st] = max(tree[lef], tree[rit]);
}

pair<int, int> query(int st, int l, int r, int x, int y)
{
    if (l > y || r < x)
        return make_pair(-1000000007, -1);

    if (l >= x && r <= y)
        return tree[st];

    int mid = (l + r) / 2;

    return max(query(2 * st, l, mid, x, y), query(2 * st + 1, mid + 1, r, x, y));
}

int main()
{
    int n, q, a, b, c;
    char ch;
    cin >> n;

    for (int i = 0; i < n; ++i)
    {
        cin >> arr[i];
    }

    build(1, 0, n - 1);
    cin >> q;

    while (q--)
    {
        cin >> ch >> a >> b;
        if (ch == 'Q')
        {
            pair<int, int> c = query(1, 0, n - 1, a - 1, b - 1);
            pair<int, int> d = query(1, 0, n - 1, a - 1, c.second - 1);
            pair<int, int> e = query(1, 0, n - 1, c.second + 1, b - 1);
            cout << (c.first + max(d.first, e.first)) << endl;
        }
        else
        {
            update(1, 0, n - 1, a - 1, b);
        }
    }
    return 0;
}