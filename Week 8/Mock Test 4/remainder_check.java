public class Solution {
    private static int countTill(int t, int len, int[] pref) {
        return (t / len) * pref[len - 1] + pref[t % len];
    }

    public static int[] countValidNumbers(int x, int y, int q, int[][] queries) {
        // Vector to be returned.
        int[] ret = new int[q];
        // Length of our array
        int len = x * y;
        // Prefix sum array computation.
        int[] pref = new int[len];
        pref[0] = 0;
        for (int i = 1; i < len; ++i) {
            pref[i] = pref[i - 1];
            if ((i % x) % y != (i % y) % x)
                pref[i]++;
        }
        for (int i = 0; i < q; ++i) {
            int l = queries[i][0];
            int r = queries[i][1];
            // Number of valid integers ON [L,R] will be [0,R] - [0,L-1].
            int ans = countTill(r, len, pref) - countTill(l - 1, len, pref);
            ret[i] = ans;
        }
        return ret;
    }
}