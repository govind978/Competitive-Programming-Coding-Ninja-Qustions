
#include <unordered_map>
bool CheckSumPairs(int arr[], int n, int k, int m)
{
    // Write your code here.
    if (n % 2 != 0)
    {
        return false;
    }
    unordered_map<int, int> count;
    for (int i = 0; i < n; i++)
    {
        count[arr[i] % k]++;
    }
    int first, second;
    for (int i = 0; i < n; i++)
    {
        first = count[arr[i] % k];
        second = count[(m - arr[i] % k + k) % k];
        if (first != second)
        {
            return false;
        }
    }
    return true;
}