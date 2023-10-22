
#include <iostream>
using namespace std;
int getCount(int *arr, int n, int value, int **temp)
{
    if (value == 0)
    {
        return 1;
    }
    if (n == 0)
    {
        return 0;
    }
    if (value < 0)
    {
        return 0;
    }
    if (temp[n][value] > -1)
    {
        return temp[n][value];
    }
    int first = getCount(arr, n, value - arr[0], temp);
    int second = getCount(arr + 1, n - 1, value, temp);
    temp[n][value] = first + second;
    return first + second;
}

int countWaysToMakeChange(int denominations[], int numDenominations, int value)
{

    /*  Don't write main().
     *  Don't read input, it is passed as function argument.
     *  Return output and don't print it.
     *  Taking input and printing output is handled automatically.
     */
    int **temp = new int *[11];
    for (int i = 0; i < 11; i++)
    {
        temp[i] = new int[1001];
    }
    for (int i = 0; i < 11; i++)
    {
        for (int j = 0; j < 1001; j++)
        {
            temp[i][j] = -1;
        }
    }
    return getCount(denominations, numDenominations, value, temp);
}
