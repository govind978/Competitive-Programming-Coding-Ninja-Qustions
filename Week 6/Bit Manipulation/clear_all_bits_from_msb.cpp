

#include <bits/stdc++.h>
using namespace std;
int main()
{
    int t;
    cin >> t;
    while (t--)
    {
        int n, i;
        cin >> n >> i;
        int mask = (1 << i) - 1;
        cout << (n & mask) << endl;
    }
    return 0;
}