#include <bits/stdc++.h>

using namespace std;
typedef long long int ll;

ll phiFunction(ll n)
{
    ll res = n;
    for (ll i = 2; i * i <= n; i++)
    {
        if (n % i == 0)
        {
            while (n % i == 0)
            {
                n /= i;
            }
            res -= res / i;
        }
    }
    if (n > 1)
        res -= res / n;
    return res;
}

int main()
{
    ll t;
    cin >> t;
    while (t--)
    {
        ll n;
        cin >> n;
        cout << phiFunction(n) << endl;
    }
}