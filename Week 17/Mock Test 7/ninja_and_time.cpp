/*
    Time Complexity: O(N * log(1000000))
    SpaceComplexity: O(1000000)

    Where 'N' denotes the number of elements in the array.
*/

// Declaring Fenwick Tree.
const int maxn = 1000000;
int Bit[maxn];

/*
    Function to calculate the sum of previous
    numbers that are greater  than the current number in the array.
*/
void solve(vector<int> arr, int N, vector<int> &res)
{

    // Iterate the whole array.
    for (int i = 0; i < N; i++)
    {
        int index;
        int sum = 0;
        index = 100000;

        while (index)
        {

            // Calculating the sum of elements in the Fenwick Tree.
            sum = sum + Bit[index];
            index = index - (index & -index);
        }

        /*
            Storing the value of sum of elements having
            value smaller than or equal to the current element.
        */
        int sumNow = 0;

        index = arr[i];

        while (index)
        {
            sumNow = sumNow + Bit[index];
            index = index - (index & -index);
        }

        int ans = sum - sumNow;
        res.push_back(ans);

        // Update the Fenwick tree.
        index = arr[i];
        while (index <= 100000)
        {

            Bit[index] = Bit[index] + arr[i];
            index = index + (index & -index);
        }
    }
}

vector<int> fenwikTree(vector<int> arr, int N)
{

    // To store the sum for different elements.
    vector<int> res;

    // Initializing Fenwick Tree.
    for (int i = 0; i < maxn; i++)
    {
        Bit[i] = 0;
    }

    solve(arr, N, res);

    return res;
}