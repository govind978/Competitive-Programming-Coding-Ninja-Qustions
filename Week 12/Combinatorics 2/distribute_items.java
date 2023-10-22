/*
    Time Complexity : O(1)
    Space Complexity : O(1)
*/

public class Solution {

    public static long countWays(int N) {

        long ans = ((N - 1) * 1l * (N - 2)) / 2;

        // Subtract total number of ways when all 3 people get same number of items
        if (N % 3 == 0)
            ans--;

        // Now, subtract total number of ways when two persons get same number of items
        // and that too greater than third person.
        // This distribution is possible when two person get x number of items and third
        // one will get y number of items i.e. 2x + y = N
        // y > 0 so, min value of y = 1, max value of x can be (N - 1) / 2
        // also for invalid way y < x, as y = N - 2x so, n - 2x < x => x > N / 3
        // thus total number of possible such x are (N - 1) / 2 - (N / 3)
        int waysTwoEquals = ((N - 1) / 2) - (N / 3);
        ans -= waysTwoEquals * 3;
        return ans;

    }
}