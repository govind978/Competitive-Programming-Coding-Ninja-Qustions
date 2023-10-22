/*
    Time Complexity: O((N ^ 2) * log(N))
    Space Complexity: O(1)

    Where 'N' is the given number.
*/

// Function to calculate gcd of two number.
int gcd(int a, int b)
{
    while (b != 0)
    {
        int temp = b;
        b = a % b;
        a = temp;
    }

    return a;
}

long long gcdSum(int n)
{
    // Declare a variable to store gcd sum.
    long long answer = 0;

    // Iterate over each possible pairs.
    for (int i = 1; i <= n; i++)
    {
        for (int j = i + 1; j <= n; j++)
        {
            answer += gcd(i, j);
        }
    }

    // Return answer.
    return answer;
}