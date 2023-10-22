#include <iostream>
using namespace std;

int main()
{
    int t, n, m;
    cin >> t;
    for (int i = 0; i < t; i++)
    {
        cin >> n >> m;
        if ((n == 1) && (m != 1))
        {
            if ((m - 1) % 2 == 0)
            {
                cout << "Harshit\n";
            }
            else
            {
                cout << "Aahad\n";
            }
        }
        else if ((m == 1) && (n != 1))
        {
            if ((n - 1) % 2 == 0)
            {
                cout << "Harshit\n";
            }
            else
            {
                cout << "Aahad\n";
            }
        }
        else if ((n == 1) && (m == 1))
        {
            cout << "No\n";
        }
        else
        {
            int count = 0;
            count = (m - 1) + ((n - 1) * m);
            if (count % 2 != 0)
            {
                cout << "Aahad\n";
            }
            else
            {
                cout << "Harshit\n";
            }
        }
    }
    return 0;
}