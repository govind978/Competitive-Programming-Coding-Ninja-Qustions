#include <bits/stdc++.h>
vector<int> twoSum(vector<int> a, int target)
{
    // Write your code here.
    int i = 0;
    int j = a.size() - 1;
    vector<int> ans;
    while (i < j)
    {
        if (a[i] + a[j] == target)
        {
            ans.push_back(i);
            ans.push_back(j);
            break;
        }
        else if (a[i] + a[j] < target)
        {
            i++;
            continue;
        }
        else
        {
            j--;
            continue;
        }
    }
    return ans;
}