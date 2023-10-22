long long countNumbersHelper(long long digit, long long mask, bool repeat, long long n, vector<vector<vector<long long>>> &dp)
{
    if (digit == n + 1)
    {
        if (repeat == true)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }

    if (repeat == true)
    {
        return pow(10, n - digit + 1);
    }

    long long &val = dp[digit][mask][repeat];
    if (val != -1)
    {
        return val;
    }

    val = 0;

    if (digit == 1)
    {
        for (long long i = 1; i <= 9; ++i)
        {
            if (mask & (1 << i))
            {
                val = (val + countNumbersHelper(digit + 1, mask | (1 << i), 1, n, dp)) % 1000000007;
            }
            else
            {
                val = (val + countNumbersHelper(digit + 1, mask | (1 << i), 0, n, dp)) % 1000000007;
            }
        }
    }
    else
    {
        for (long long i = 0; i <= 9; ++i)
        {
            if (mask & (1 << i))
            {
                val = (val + countNumbersHelper(digit + 1, mask | (1 << i), 1, n, dp)) % 1000000007;
            }
            else
            {
                val = (val + countNumbersHelper(digit + 1, mask | (1 << i), 0, n, dp)) % 1000000007;
            }
        }
    }

    return val % 1000000007;
}

int countNumbers(long long n)
{
    vector<vector<vector<long long>>> dp(n + 1, vector<vector<long long>>(1024, vector<long long>(2, -1)));

    return countNumbersHelper(1, 0, 0, n, dp);
}
