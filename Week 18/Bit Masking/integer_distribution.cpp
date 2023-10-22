pair<int, int> distribute(int n, vector<int> &arr)
{
    vector<int> dpMin((1 << n)), dpMax((1 << n));

    for (int mask = 0; mask < (1 << n); mask++)
    {
        if (__builtin_popcount(mask) == 2)
        {
            for (int j = 0; j < n; j++)
            {
                if (mask & (1 << j))
                {
                    dpMin[mask] = dpMin[mask] ^ arr[j];
                    dpMax[mask] = dpMin[mask];
                }
            }
        }
        else
        {
            dpMin[mask] = INT_MAX;
        }
    }

    for (int mask = 1; mask < (1 << n); mask++)
    {
        if ((__builtin_popcount(mask) & 1))
        {
            continue;
        }

        for (int i = 0; i < n; i++)
        {
            if (mask & (1 << i))
            {
                continue;
            }

            for (int j = i + 1; j < n; j++)
            {
                if (mask & (1 << j))
                {
                    continue;
                }

                int newMask = mask | (1 << i) | (1 << j);
                dpMin[newMask] = min(dpMin[newMask], dpMin[mask] + (arr[i] ^ arr[j]));
                dpMax[newMask] = max(dpMax[newMask], dpMax[mask] + (arr[i] ^ arr[j]));
            }
        }
    }

    return {dpMin[(1 << n) - 1], dpMax[(1 << n) - 1]};
}