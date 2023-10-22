/*
    Time Complexity : O(M * (4 ^ N)).
    Space Complextiy : O(M * (2 ^ N)).

    Where 'M' is the number of rows,
    and 'N' is the number of columns.
*/

#include <algorithm>

int maxStudents(vector<vector<char>> &seats)
{
    int m = seats.size();
    int n = seats[0].size();
    vector<int> validity;

    // Parsing the seats into masks.
    for (int i = 0; i < m; ++i)
    {
        int cur = 0;
        for (int j = 0; j < n; ++j)
        {
            if (seats[i][j] == '.')
            {
                cur = cur ^ (1 << j);
            }
        }
        validity.push_back(cur);
    }

    vector<vector<int>> dp(m + 1, vector<int>(1 << n, -1));

    dp[0][0] = 0;
    for (int i = 1; i <= m; ++i)
    {
        int valid = validity[i - 1];
        /*
            Checking all possible combinations
            of seating students at valid positions.
        */
        for (int j = 0; j < (1 << n); ++j)
        {
            /*
                1. (j & valid) == j: check if j is a subset of valid.
                2. !(j & (j >> 1)): check if there is no adjancent students in the row.
            */
            if ((j & valid) == j && !(j & (j >> 1)))
            {
                for (int k = 0; k < (1 << n); ++k)
                {
                    /*
                        !(j & (k >> 1)): no students in the upper left positions
                        !((j >> 1) & k): no students in the upper right positions
                        dp[i - 1][k] != -1: the previous state is valid
                    */
                    if (!(j & (k >> 1)) && !((j >> 1) & k) && dp[i - 1][k] != -1)
                    {
                        dp[i][j] = max(dp[i][j], dp[i - 1][k] + __builtin_popcount(j));
                    }
                }
            }
        }
    }

    // The answer is the maximum among all dp[m][mask].
    return *max_element(dp[m].begin(), dp[m].end());
}