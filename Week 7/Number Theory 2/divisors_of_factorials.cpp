
#include <iostream>
#include <bits/stdc++.h>
#include <vector>
using namespace std;

#define MOD 1000000007
#define MAX 50001
typedef long long ll;
using namespace std;
#define pb push_back

vector<int> *sieve()
{
    bool isPrimes[MAX];
    for (int i = 2; i <= MAX; i++)
    {
        isPrimes[i] = true;
    }
    vector<int> *primes = new vector<int>();
    for (int i = 2; i * i <= MAX; i++)
    {
        if (isPrimes[i])
        {
            for (int j = i * i; j <= MAX; j += i)
            {
                isPrimes[j] = false;
            }
        }
    }

    primes->pb(2);
    for (int i = 3; i <= MAX; i += 2)
    {
        if (isPrimes[i])
        {
            primes->pb(i);
        }
    }

    return primes;
}

ll devisors(int n, vector<int> *primes)
{
    ll result = 1;
    for (int i = 0; primes->at(i) <= n; i++)
    {
        ll count = 0;
        int k = primes->at(i);
        while ((n / k) != 0)
        { // legendres formula to get larget power of prime that devides n!
            count = count + (n / k);
            k = k * primes->at(i);
        }
        result = (result * ((count + 1) % MOD)) % MOD;
    }
    return result;
}

int main()
{
    // Write your code here
    int t;
    cin >> t;
    while (t--)
    {
        int n;
        cin >> n;
        vector<int> *primes = sieve();
        int ans = devisors(n, primes);
        cout << ans << endl;
    }
    return 0;
}