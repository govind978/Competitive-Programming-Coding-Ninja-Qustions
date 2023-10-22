/* 
	Time complexity: O(N)
	Space complexity: O(1)

	Where 'N' is the number of elements.
*/

public class Solution {
    static long MOD = 1000000007;

    public static long countDerangements(int n) {
        // Base case.
        if (n == 1 || n == 2) {
            return n - 1;
        }

        // Variable for storing previous values.
        long prevOne = 0;
        long prevTwo = 1;

        for (int i = 3; i <= n; ++i) {
            long cur = ((i - 1) % MOD * (prevOne % MOD + prevTwo % MOD) % MOD) % MOD;
            prevOne = prevTwo;
            prevTwo = cur;
        }

        // Return result for 'N'.
        return prevTwo;
    }
}