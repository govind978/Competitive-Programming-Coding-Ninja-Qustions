

#include <iostream>
using namespace std;
#define MAX 1000001

int main()
{
    // Write your code here
    int t;
    cin >> t;

    // pre computing array
    int *arr = new int[MAX]();
    for (int i = 0; i <= 1000000; i++)
    {
        arr[i] = 0;
    }
    for (int i = 2; i <= 100; i++)
    { // its enough if you traverse upto 100 since (100)^3 = 10^6 by time you reach 100 it covers remaining all
        int cubic = i * i * i;
        for (int j = 1; cubic * j <= 1000000; j++)
        { // making all cubic numbers of i to -1
            arr[cubic * j] = -1;
        }
    }

    int *result = new int[MAX](); // at each index it stores numbers of cubic free numbers ending at that index(ie position)
    int count = 1;
    result[0] = 0;
    for (int i = 1; i <= 1000000; i++)
    {
        if (arr[i] != -1)
        {
            result[i] = count;
            count++;
        }
    }

    int caseCount = 1;
    while (t--)
    {
        int n;
        cin >> n;
        if (result[n] == 0)
        {
            cout << "Not Cube Free" << endl;
            caseCount++;
        }
        else
        {
            cout << result[n] << endl;
            caseCount++;
        }
    }
    return 0;
}