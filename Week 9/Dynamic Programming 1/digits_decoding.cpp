/*
    Time Complexity: O(N)
    Space Complexity: O(1)

    Where N is size of the sequence.
*/

int decodingDigits(string seq)
{
    int n = seq.size();

    // The idea is to store answers for the last 2 iterations,
    // in 2 variables named prev1 and prev2.
    int prev1 = 1, prev2 = 1, cur;

    for (int i = 1; i < n; i++)
    {
        cur = 0;
        if (seq[i] != '0')
        {
            cur += prev1;
        }

        int num = (seq[i - 1] - '0') * 10 + seq[i] - '0';
        if (num >= 10 and num <= 26)
        {
            cur += prev2;
        }

        prev2 = prev1;
        prev1 = cur % 1000000007;
    }
    return prev1;
}
