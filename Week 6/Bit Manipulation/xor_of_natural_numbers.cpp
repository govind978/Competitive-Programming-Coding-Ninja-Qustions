

#include <bits/stdc++.h>
using namespace std;
int main()
{

    int t;
    cin >> t;
    while (t--)
    {
        int n;
        cin >> n;
        int rem = n % 4;
        if (rem == 0)
        {
            cout << n << endl;
        }
        else if (rem == 1)
        {
            cout << 1 << endl;
        }
        else if (rem == 2)
        {
            cout << (n + 1) << endl;
        }
        else
        {
            cout << 0 << endl;
        }
    }
    return 0;
}