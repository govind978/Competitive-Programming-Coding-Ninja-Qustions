/*
    Time Complexity : O((2 ^ N) * (N*M))
    Space Complexity : O((M) * (2 ^ N))

    Where 'N' is the number of people and 'M' is the number of hats.
*/

int mod = 1e9 + 7;

int numberWaysHelper(vector<vector<int>> &mp, int hatId, int curMask, int &filledMask, vector<vector<int>> &dp)
{

    // Base condition.
    if (curMask == filledMask)
    {
        return 1;
    }

    // Base condition.
    if (hatId >= 42)
    {
        return 0;
    }

    if (dp[hatId][curMask] != -1)
    {
        return dp[hatId][curMask];
    }

    long long int totalWays = numberWaysHelper(mp, hatId + 1, curMask, filledMask, dp);

    for (int i : mp[hatId])
    {
        if ((curMask & (1 << (i - 1))) == 0)
        {

            // Recurr for next hatId, assigning current hatId to i.
            totalWays += numberWaysHelper(mp, hatId + 1, ((curMask) | (1 << (i - 1))), filledMask, dp);
        }
    }

    return dp[hatId][curMask] = totalWays % mod;
}

int numberWays(vector<vector<int>> &hats)
{
    int n = hats.size();

    vector<vector<int>> mp(42);

    // Hashing student to hatId.
    for (int i = 0; i < n; i++)
    {
        int sz = hats[i].size();

        for (int j = 0; j < sz; j++)
        {
            mp[hats[i][j]].push_back(i + 1);
        }
    }

    vector<vector<int>> dp(42, vector<int>(pow(2, 11), -1));

    int filledMask = (1 << n) - 1;

    return numberWaysHelper(mp, 1, 0, filledMask, dp);
}
