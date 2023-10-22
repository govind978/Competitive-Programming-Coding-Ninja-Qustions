/*
	Time Complexity : O(N)
	Space Complexity : O(N)

	where 'N' is the number of coins.
*/

// Returns whether X is the winner or not.
int coinGameWinner(int n, int a, int b)
{

	vector<int> dp(n + 1);
	dp[0] = 0;
	dp[1] = 1;

	for (int i = 2; i <= n; i++)
	{
		// Checking all three cases.
		if (!dp[i - 1])
		{
			dp[i] = 1;
		}

		if (i >= a && !dp[i - a])
		{
			dp[i] = 1;
		}

		if (i >= b && !dp[i - b])
		{
			dp[i] = 1;
		}
	}

	// Return the ans.
	return dp[n];
}
