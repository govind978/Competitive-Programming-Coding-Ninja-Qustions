#include <iostream>

bool isPrimePower(int n)
{
    if (n == 0)
    {
        return false;
    }
    while (n % 2 == 0)
    {
        n /= 2;
    }
    return n == 1;
}

void whoWins(int n)
{
    if (n % 6 != 0)
    {
        cout << "Raman\n";
    }
    else
    {
        cout << "Moni\n";
    }
}