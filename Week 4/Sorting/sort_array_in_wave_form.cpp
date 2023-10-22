vector<int> waveFormArray(vector<int> &arr, int n)
{

    sort(arr.begin(), arr.end());

    for (int i = 1; i < n; i += 2)
    {

        swap(arr[i], arr[i - 1]);
    }

    return arr;
}