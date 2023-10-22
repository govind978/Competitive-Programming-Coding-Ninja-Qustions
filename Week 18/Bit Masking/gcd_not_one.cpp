/*
    Time Complexity:  O( N*(2^5) ).
    Space Complexity: O( maxn*5 ).

    Where 'N' is the size of the array and maxn is 2501.
*/

int gcdNotOne(int n, vector<int> &num)
{
    const int maxn = 2501;
    const int mod = 1000000007;

    // prime[i] checks whether 'i' is a prime number or not.
    vector<int> prime(maxn);

    vector<long long int> store_cnt(maxn), store_sum(maxn);

    // pf[i] stores the prime factorization of number 'i'.
    vector<int> pf[maxn];

    int ans = 0;

    prime[0] = prime[1] = 1;
    for (int i = 2; i < maxn; i++)
    {
        if (prime[i])
        {
            continue;
        }
        pf[i].push_back(i);
        for (int j = 2 * i; j < maxn; j += i)
        {
            prime[j] = 1;
            pf[j].push_back(i);
        }
    }

    for (int i = n - 1; i >= 0; i--)
    {
        int sz = pf[num[i]].size();
        long long int cnt = 0;
        long long int sum = 0;

        for (int mask = 1; mask < (1 << sz); mask++)
        {
            int x = 1;
            for (int j = 0; j < sz; j++)
            {
                if (!(mask & (1 << j)))
                {
                    continue;
                }
                x = x * pf[num[i]][j];
            }
            if ((__builtin_popcount(mask)) & 1)
            {
                cnt += 1LL * store_cnt[x];
                sum += 1LL * store_sum[x];
            }
            else
            {
                cnt -= 1LL * store_cnt[x];
                sum -= 1LL * store_sum[x];
            }
        }
        long long int temp = (1LL * (i + 1) * (1LL * n * cnt + cnt - sum)) % mod;
        ans = (ans + temp) % mod;

        for (int mask = 1; mask < (1 << sz); mask++)
        {
            int x = 1;
            for (int j = 0; j < sz; j++)
            {
                if (!(mask & (1 << j)))
                {
                    continue;
                }
                x = x * pf[num[i]][j];
            }
            store_cnt[x]++;
            store_sum[x] += 1LL * (i + 1);
        }
    }

    for (int i = 1; i <= n; i++)
    {
        if (num[i - 1] != 1)
        {

            long long int temp = (1LL * (1LL * i * (n - i + 1))) % mod;
            ans = (ans + temp) % mod;
        }
    }

    // Return the answer.
    return ans;
}
