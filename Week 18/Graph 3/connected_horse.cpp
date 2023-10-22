#include <bits/stdc++.h>
using namespace std;

vector<pair<int, int>> moves(8);
int n, m;
long long MOD = 1e9 + 7;

vector<long long> fact(1000001);

int a[1005][1005] = {{0}};

int bfs(int x, int y)
{
    int c = 0;
    a[x][y] = 2;
    queue<pair<int, int>> q;
    q.push(make_pair(x, y));
    while (q.size())
    {
        c++;
        pair<int, int> node = q.front();
        q.pop();
        x = node.first;
        y = node.second;
        for (int i = 0; i < 8; i++)
        {
            if (x + moves[i].first >= 1 && x + moves[i].first <= n && y + moves[i].second >= 1 && y + moves[i].second <= m && a[x + moves[i].first][y + moves[i].second] == 1)
            {
                a[x + moves[i].first][y + moves[i].second] = 2;
                q.push(make_pair(x + moves[i].first, y + moves[i].second));
            }
        }
    }
    return fact[c];
}

int main()
{

    moves[0] = make_pair(-1, -2);
    moves[1] = make_pair(-1, 2);
    moves[2] = make_pair(1, -2);
    moves[3] = make_pair(1, 2);

    moves[4] = make_pair(-2, -1);
    moves[5] = make_pair(-2, 1);
    moves[6] = make_pair(2, -1);
    moves[7] = make_pair(2, 1);

    fact[0] = 1;
    for (int i = 1; i <= 1000000; i++)
        fact[i] = (i * fact[i - 1]) % MOD;

    // freopen("in09.txt","r",stdin);
    // freopen("out09.txt","w",stdout);

    int t, q, i, j, x, y;
    cin >> t;
    while (t--)
    {
        cin >> n >> m >> q;
        while (q--)
        {
            cin >> x >> y;
            a[x][y] = 1;
        }
        long long ans = 1;
        for (int i = 1; i <= n; i++)
        {
            for (int j = 1; j <= m; j++)
            {
                if (a[i][j] == 1)
                    ans = (ans * bfs(i, j)) % MOD;
            }
        }
        cout << ans << endl;
        for (int i = 1; i <= n; i++)
        {
            for (int j = 1; j <= m; j++)
            {
                a[i][j] = 0;
            }
        }
    }
}