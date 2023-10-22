#include <bits/stdc++.h>
using namespace std;

map<long long, int> memo;

int solvePattern(long long x)
{
    if (x <= 2)
        return x;
    if (x == 3)
        return 2;
    if (x <= 5)
        return 3;

    long long l = 6, cnt = 6;
    while (1)
    {
        if (x < l + cnt)
            return 0;
        l += cnt;
        if (x < l + 2 * cnt)
            return 1;
        l += 2 * cnt;
        if (x < l + 4 * cnt)
            return 2;
        l += 4 * cnt;
        if (x < l + 4 * cnt)
            return 3;
        l += 4 * cnt;
        cnt *= 12;
    }
    assert(false);
}

string game(int N, vector<long long> A)
{
    // Write your code here.
    int xorSum = 0;
    for (long long x : A)
    {
        xorSum ^= solvePattern(x);
    }

    return (xorSum ? "Jack" : "Michael");
}