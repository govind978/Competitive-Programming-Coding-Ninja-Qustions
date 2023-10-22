/*
    Time complexity: O(N * Log(N))
    Space complexity: O(N)

    where 'N' is the size of the given array.

*/

// Returns the minimum number of swaps required to sort the array.
int minSwaps(vector<int> &arr)
{
    int count = 0;
    vector<int> temp = arr;

    sort(temp.begin(), temp.end());

    // Hashmap to store the indexes of the input array.
    unordered_map<int, int> map;

    for (int i = 0; i < arr.size(); i++)
    {
        map[arr[i]] = i;
    }

    for (int i = 0; i < arr.size(); i++)
    {
        /*
            Checking whether the current element
            is at the right place or not.
        */
        if (arr[i] != temp[i])
        {
            count++;
            int init = arr[i];
            /*
                If not, swap this element with the index
                of the element which should come here.
            */
            swap(arr[i], arr[map[temp[i]]]);

            // Update the indexes in the hashmap accordingly.
            map[init] = map[temp[i]];
            map[temp[i]] = i;
        }
    }

    return count;
}