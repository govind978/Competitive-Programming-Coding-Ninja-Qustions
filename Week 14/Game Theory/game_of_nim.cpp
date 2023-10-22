void findWinner(int *arr, int n)
{
    // code - here
    int xorSum = 0;

    for (int i = 0; i < n; i++)
    {
        xorSum ^= arr[i];
    }

    if (xorSum == 0 || n % 2 == 0)
    {
        std::cout << "First" << std::endl;
    }
    else
    {
        std::cout << "Second" << std::endl;
    }
}
