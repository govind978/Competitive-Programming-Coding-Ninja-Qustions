#include <bits/stdc++.h>
using namespace std;

int calculateMex(unordered_set<int> Set)
{
    int Mex = 0;

    while (Set.find(Mex) != Set.end())
        Mex++;

    return Mex;
}

int calculateGrundy(int n, int grandy[])
{
    if (n == 0)
        return 0;

    if (grandy[n] != -1)
    {
        return grandy[n];
    }

    unordered_set<int> Set;

    Set.insert(calculateGrundy(n / 2, grandy));
    Set.insert(calculateGrundy(n / 3, grandy));
    Set.insert(calculateGrundy(n / 6, grandy));

    return grandy[n] = calculateMex(Set);
}

int main()
{
    int grandy[1000001];
    memset(grandy, -1, sizeof(grandy));
    int t;
    cin >> t;
    while (t--)
    {
        int n;
        cin >> n;
        printf("%d \n", calculateGrundy(n, grandy));
    }
}