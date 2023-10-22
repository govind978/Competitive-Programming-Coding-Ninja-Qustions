#include <iostream>
#include <vector>
#include <set>
using namespace std;

int count3Cycles(int N, vector<int> &u, vector<int> &v)
{
    vector<set<int>> adj(N + 1);

    for (int i = 0; i < u.size(); i++)
    {
        adj[u[i]].insert(v[i]);
        adj[v[i]].insert(u[i]);
    }

    int count = 0;

    for (int i = 1; i <= N; i++)
    {
        for (int u : adj[i])
        {
            for (int v : adj[i])
            {
                if (u != v && adj[u].count(v))
                {
                    count++;
                }
            }
        }
    }

    return count / 6; // Each 3-cycle is counted 3 times.
}

int main()
{
    int N, M;
    cin >> N >> M;
    vector<int> u(M);
    vector<int> v(M);

    for (int i = 0; i < M; i++)
    {
        cin >> u[i];
    }

    for (int i = 0; i < M; i++)
    {
        cin >> v[i];
    }

    int result = count3Cycles(N, u, v);
    cout << result << endl;

    return 0;
}
