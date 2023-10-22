/*
    Time Complexity: O(N^2)
    Space Complexity: O(1)

    where 'N' is the size of array.
*/

vector<int> countNumber(int n, vector<int> &arr)
{
    vector<int> ans(n, 0);

    for (int i = 0; i < n; i++)
    {
        for (int j = i + 1; j < n; j++)
        {
            // If arr[i] greater than arr[j] then increase the count of ans[i].
            if (arr[i] > arr[j])
            {
                ans[i]++;
            }
        }
    }

    return ans;
}