/*
     Time Complexity : O(N*log(MAX))
     Space Complexity : O(log(MAX))

     Where 'N' is the number of elements o the array and 'MAX' is the maximum value among array elements.
*/

long long int sumOfHammingDistance(int *arr, int n)
{
    // Initializing the two arrays.
    int countOne[32] = {0};
    int countZero[32] = {0};

    // Iterating through all the array elements.
    for (int i = 0; i < n; i++)
    {
        int j = 0;
        // Iterating through all the bits.
        while (j < 32)
        {
            // Checking if the jth bit is zero.
            if (arr[i] % 2 == 0)
            {
                countZero[j] += 1;
            }
            else
            {
                countOne[j] += 1;
            }
            // Moving to the next bit.
            arr[i] /= 2;
            j++;
        }
    }

    // Initializing the answer.
    long long int ans = 0;

    // Iterating through all the bits.
    for (int i = 0; i < 32; i++)
    {
        // Finding contribution of each bit.
        long long int contribution = 2 * countZero[i] * countOne[i];

        // Adding the contribution to answer.
        ans += contribution;
    }

    // Returning the overall sum.
    return ans;
}
