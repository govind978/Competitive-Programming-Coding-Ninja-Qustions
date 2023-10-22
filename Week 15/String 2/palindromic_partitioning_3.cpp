/*
    Time Complexity  :   O(K * (N ^ 2))
    Space Complexity :   O(N ^ 2 + N * K)

    Where N is the length of the given string and K is an integer given in the problem.
*/

int paliPartitioning(string &s, int k)
{
    int n = s.length();

    // Declaring a 2-D array of integers to store the minimum changes for each substring.
    vector<vector<int>> changes(n, vector<int>(n));

    for (int index = 0; index < n; index++)
    {

        // All substrings of length 1 are a palindrome.
        changes[index][index] = 0;
    }

    for (int index = 1; index < n; index++)
    {

        if (s[index - 1] == s[index])
        {

            // Since substring ‘S[index - 1, index]’ is already a palindrome, we don’t have to do any changes.
            changes[index - 1][index] = 0;
        }

        else
        {

            // We can change either S[index - 1] or S[index] to make substring S[index - 1 , index] palindrome.
            changes[index - 1][index] = 1;
        }
    }

    for (int len = 2; len < n; len++)
    {
        for (int i = 0; i + len < n; i++)
        {
            int j = i + len;

            /*
                For a substring S[i, j], if S[i] is the same as S[j] then
                the minimum number of characters that we need to change to make
                this substring a  palindrome is equivalent to the minimum number
                of character changes to make substring S[i + 1, j - 1] palindrome.
            */
            if (s[i] == s[j])
            {
                changes[i][j] = changes[i + 1][j - 1];
            }

            /*
                For a substring S[i, j], if S[i] is not the same as S[j] then
                the minimum number of characters that we need to change to make this
                substring a  palindrome is equivalent to the minimum number of character
                changes to make substring S[i + 1, j - 1] palindrome plus 1 since we
                can change either S[i] or S[j] to  make S[i, j] palindrome.
            */
            else
            {
                changes[i][j] = 1 + changes[i + 1][j - 1];
            }
        }
    }

    // Declaring a 2-D array for DP table and initializing it with n + 1.
    vector<vector<int>> dp(n, vector<int>(k + 1, n + 1));

    for (int index = 0; index < n; index++)
    {

        /*
            The minimum number of characters that we need to change to divide the
            substring S[i, N] into exactly 1 part is the same as the minimum number
            of changes required to make substring S[i, N] palindrome.
        */
        dp[index][1] = changes[index][n - 1];
    }

    for (int currK = 2; currK <= k; currK++)
    {
        for (int i = 0; i < n; i++)
        {
            for (int j = n - 1; j >= 0; j--)
            {

                // Empty substrings.
                if (i > (j - 1))
                {
                    break;
                }

                /*
                    dp[i][currK] denotes the minimal number of characters required to change
                    upon the substring S[i, N], such that S[i, N] can be divided into
                    exactly currK non-empty disjoint substrings with each substring a palindrome.
                */
                dp[i][currK] = min(dp[i][currK], changes[i][j - 1] + dp[j][currK - 1]);
            }
        }
    }

    return dp[0][k];
}