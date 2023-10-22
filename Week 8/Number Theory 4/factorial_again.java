/*
    Time Complexity : O((N - P)*log(N))
    Space Complexity : O(1)

    Where 'N' is the number given and 'P' is the given prime number.
*/

import java.util.ArrayList;

public class Solution {

    private static long power(long x, long y, long p) {
        long res = 1; // Initialize result
        x = x % p; // Update x if it is more than or
        // equal to p
        while (y > 0) {
            // If y is odd, multiply x with result
            if ((y & 1) == 1)
                res = (res * x) % p;

            // y must be even now
            y = y >> 1; // y = y/2
            x = (x * x) % p;
        }
        return res;
    }

    private static long modInverse(long a, long p) {
        return power(a, p - 2, p);
    }

    private static long modMultiplication(long a, long b, long m) {
        a = a % m;
        b = b % m;

        return (((a * b) % m) + m) % m;
    }

    private static long modDivision(long a, long b, long m) {
        a = a % m;
        b = b % m;

        return (modMultiplication(a, modInverse(b, m), m) + m) % m;
    }

    public static long factorialAgain(long n, long p) {
        n = modMultiplication(n, 3, p);

        long res = (p - 1);

        for (long i = n + 1; i < p; i++)
            res = (res * modInverse(i, p)) % p;

        long den = 1;
        den = power(6, n / 3, p);

        long ans = modDivision(res, den, p);

        return ans;

    }
}