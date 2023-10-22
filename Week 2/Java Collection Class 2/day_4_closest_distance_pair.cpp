#include <bits/stdc++.h>

// Greedy Approach! - First Sort elements based on x-coordinated then sort element based on y-coordinate and take the minimum.

// Time: O(nlogn)

// Space: O(1)

// Comparator

bool compare(const pair<int, int> &a, const pair<int, int> &b)
{

    return a.second < b.second;
}

long closestPair(pair<int, int> *arr, int n)
{

    // Sorting -> Based on X-Coordinate

    sort(arr, arr + n);

    long long ans = INT_MAX;

    long long ans2 = INT_MAX;

    for (int i = 0; i < n - 1; i++)
    {

        long long x1 = arr[i].first;

        long long y1 = arr[i].second;

        long long x2 = arr[i + 1].first;

        long long y2 = arr[i + 1].second;

        long long val = ((x1 - x2) * (x1 - x2)) + ((y1 - y2) * (y1 - y2));

        ans = min(ans, val);
    }

    // Sorting -> Based on Y-Coordinate

    sort(arr, arr + n, compare);

    for (int i = 0; i < n - 1; i++)
    {

        long long a1 = arr[i].first;

        long long b1 = arr[i].second;

        long long a2 = arr[i + 1].first;

        long long b2 = arr[i + 1].second;

        long long val2 = ((a1 - a2) * (a1 - a2)) + ((b1 - b2) * (b1 - b2));

        ans2 = min(ans2, val2);
    }

    return min(ans, ans2);
}