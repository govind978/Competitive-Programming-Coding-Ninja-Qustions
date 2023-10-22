

#include <algorithm>

int findFirstIndex(vector<int> &arr, int n, int value)

{

    int start = 0;

    int end = n - 1;

    int ans = -1;

    // Iterate till start is less than or equal to end

    while (start <= end)

    {

        // The variable mid stores the mid value of start and end

        int mid = (start + end) / 2;

        if (arr[mid] == value)

        {

            ans = mid;

            end = mid - 1;
        }

        else if (arr[mid] < value)

        {

            start = mid + 1;
        }

        else

        {

            end = mid - 1;
        }
    }

    // Return the variable ans

    return ans;
}

vector<int> relativeSorting(vector<int> &arr, vector<int> &brr, int n, int m)

{

    vector<int> visited(n, false);

    // Sort the array ARR

    sort(arr.begin(), arr.end());

    // Create an array result

    vector<int> result;

    // Traverse through the array BRR

    for (int i = 0; i < m; i++)

    {

        int firstIndex = findFirstIndex(arr, n, brr[i]);

        if (firstIndex != -1)

        {

            for (int j = firstIndex; j < n; j++)

            {

                // Check if ARR[j] is equal to BRR[i]

                if (arr[j] == brr[i])

                {

                    visited[j] = true;

                    result.push_back(arr[j]);
                }

                else

                {

                    break;
                }
            }
        }
    }

    for (int i = 0; i < n; i++)

    {

        // Check if visited[i] is equal to false

        if (visited[i] == false)

        {

            result.push_back(arr[i]);
        }
    }

    // Return the array result

    return result;
}