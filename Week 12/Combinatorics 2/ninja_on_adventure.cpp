#include <bits/stdc++.h>
using namespace std;
typedef long long int ll;

const ll MOD = 1e9 + 7;

void pre(ll catalan[], int n)
{
    catalan[0] = catalan[1] = 1;

    for (int i = 2; i <= n; i++)
    {
        catalan[i] = 0;
        for (int j = 0; j < i; j++)
        {
            catalan[i] += (catalan[j] * catalan[i - j - 1]) % MOD;
            if (catalan[i] >= MOD)
            {
                catalan[i] -= MOD;
            }
        }
    }
}

int main()
{
    ll catalan[1002];
    pre(catalan, 1000);
    int t;
    cin >> t;

    while (t--)
    {
        int n;
        cin >> n;
        cout << catalan[n] << endl;
    }
}
