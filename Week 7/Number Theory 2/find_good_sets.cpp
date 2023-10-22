

#include <iostream>
#include <algorithm>
using namespace std;
#define MAX 750001
#define MOD 1000000007
typedef long long ll;
int main()
{
    // Write your code here
    int t;
    cin >> t;
    while (t--)
    {
        ll n;
        cin >> n;
        ll *arr = new ll[n];
        for (ll i = 0; i < n; i++)
        {
            cin >> arr[i];
        }

        ll *sieve = new ll[MAX];
        for (ll i = 0; i < MAX; i++)
        {
            sieve[i] = 0;
        }

        for (ll i = 0; i < n; i++)
        {
            sieve[arr[i]] = 1;
        }

        for (ll i = 1; i < MAX; i++)
        {
            if (sieve[i] > 0)
            {
                for (ll j = i * 2; j < MAX; j += i)
                {
                    if (sieve[j] > 0)
                    {
                        sieve[j] = (sieve[j] % MOD + sieve[i] % MOD) % MOD; // adding count to all its multiples
                    }
                }
            }
        }

        ll count = 0;
        for (ll i = 0; i < MAX; i++)
        {
            count = (count % MOD + sieve[i] % MOD) % MOD;
        }
        cout << count << endl;
    }
    return 0;
}