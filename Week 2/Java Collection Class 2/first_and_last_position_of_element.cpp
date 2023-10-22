
#include <bits/stdc++.h>

vector<int> searchRange(vector<int> &arr, int k)
{

    int ind1 = lower_bound(arr.begin(), arr.end(), k) - arr.begin();

    int ind2 = upper_bound(arr.begin(), arr.end(), k) - arr.begin();

    if (ind1 != ind2)
        return {ind1, ind2 - 1};

    return {-1, -1};
}