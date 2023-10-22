bool isPossible(vector<int> &boards, int k, int mid)
{
    int paintCount = 1;
    int counterCount = 0;
    for (int i = 0; i < boards.size(); i++)
    {
        if (counterCount + boards[i] <= mid)
        {
            counterCount += boards[i];
        }
        else
        {
            paintCount++;
            counterCount = boards[i];
            if (paintCount > k || boards[i] > mid)
            {
                return false;
            }
            // paintCount= boards[i];
        }
    }
    return true;
}

int findLargestMinDistance(vector<int> &boards, int k)
{
    //    Write your code here.

    int start = 0;
    int sum = 0;
    int ans = -1;
    for (int i = 0; i < boards.size(); i++)
    {
        sum += boards[i];
    }
    int end = sum;

    int mid = start + (end - start) / 2;

    while (start <= end)
    {
        if (isPossible(boards, k, mid))
        {
            ans = mid;
            end = mid - 1;
        }
        else
        {
            start = mid + 1;
        }
        //   mid = start + (end - start)/2;
        mid = start + (end - start) / 2;
    }

    return ans;
}