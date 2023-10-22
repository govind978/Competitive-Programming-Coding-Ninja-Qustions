/*
    Time Complexity : O(log(N))
    Space Complexity : O(1)

    Where 'N' is the number given to us.
*/

public class Solution {

    public static long nthTerm(long n) {

        if (n <= 1)
            return 1;

        n--;
        int mod = (int) 1e9 + 7;

        // This is an identity matrix.
        long res[][] = { { 1, 0 }, { 0, 1 } };

        // This is Transformation matrix.
        long tMat[][] = { { 2, 3 }, { 1, 0 } };

        while (n > 0) {

            if ((n & 1) == 1) {
                long tmp[][] = new long[2][2];
                tmp[0][0] = (res[0][0] * tMat[0][0] + res[0][1] * tMat[1][0]) % mod;
                tmp[0][1] = (res[0][0] * tMat[0][1] + res[0][1] * tMat[1][1]) % mod;
                tmp[1][0] = (res[1][0] * tMat[0][0] + res[1][1] * tMat[1][0]) % mod;
                tmp[1][1] = (res[1][0] * tMat[0][1] + res[1][1] * tMat[1][1]) % mod;
                res[0][0] = tmp[0][0];
                res[0][1] = tmp[0][1];
                res[1][0] = tmp[1][0];
                res[1][1] = tmp[1][1];
            }
            n = n / 2;
            long tmp[][] = new long[2][2];
            tmp[0][0] = (tMat[0][0] * tMat[0][0] + tMat[0][1] * tMat[1][0]) % mod;
            tmp[0][1] = (tMat[0][0] * tMat[0][1] + tMat[0][1] * tMat[1][1]) % mod;
            tmp[1][0] = (tMat[1][0] * tMat[0][0] + tMat[1][1] * tMat[1][0]) % mod;
            tmp[1][1] = (tMat[1][0] * tMat[0][1] + tMat[1][1] * tMat[1][1]) % mod;
            tMat[0][0] = tmp[0][0];
            tMat[0][1] = tmp[0][1];
            tMat[1][0] = tmp[1][0];
            tMat[1][1] = tmp[1][1];
        }

        return (res[0][0] * 1 + res[0][1] * 1) % mod;
    }
}