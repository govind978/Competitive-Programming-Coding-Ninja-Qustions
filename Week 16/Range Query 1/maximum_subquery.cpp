

#include <bits/stdc++.h>
using namespace std;

int query(int *tree, int start, int end, int treeNode, int left, int right)
{

    // Completely out
    if (left > end || right < start)
    {
        int temp = INT_MIN;
        return temp;
    }

    // Completely inside
    if (start >= left && end <= right)
    {
        return tree[treeNode];
    }

    // Partially inside
    int mid = (start + end) / 2;

    int l = query(tree, start, mid, 2 * treeNode + 1, left, right);
    int r = query(tree, mid + 1, end, 2 * treeNode + 2, left, right);

    int result;
    result = max(l, r);

    return result;
}

void create(int *arr, int *tree, int start, int end, int treeNode)
{

    if (end == start)
    {
        tree[treeNode] = arr[start];
        return;
    }

    int mid = (start + end) / 2;

    create(arr, tree, start, mid, 2 * treeNode + 1);
    create(arr, tree, mid + 1, end, 2 * treeNode + 2);

    int left = tree[2 * treeNode + 1];
    int right = tree[2 * treeNode + 2];

    tree[treeNode] = max(left, right);

    return;
}

int main()
{
    int n, q;
    cin >> n;

    int *arr = new int[n];
    for (int i = 0; i < n; ++i)
    {
        cin >> arr[i];
    }

    cin >> q;
    int *tree = new int[4 * n];
    create(arr, tree, 0, n - 1, 0);

    while (q--)
    {

        int b, c;
        cin >> b >> c;
        int result = query(tree, 0, n - 1, 0, b, c);

        cout << result << endl;
    }

    return 0;
}