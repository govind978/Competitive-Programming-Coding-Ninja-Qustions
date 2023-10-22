
public class Solution {
    public static long modInverse(long a, long m) {
        long m0 = m, t, q;
        long x0 = 0, x1 = 1;

        while (a > 1) {
            q = a / m;
            t = m;
            m = a % m;
            a = t;
            t = x0;
            x0 = x1 - q * x0;
            x1 = t;
        }

        if (x1 < 0) {
            x1 += m0;
        }
        return x1;
    }

    public static long numbersAndRemainders(int n, int[] arr, int[] rem) {

        long product = 1;

        for (int i = 0; i < n; i++) {
            product *= arr[i];
        }

        long result = 0;
        for (int i = 0; i < n; i++) {
            long pp = product / arr[i];
            result += (rem[i] * pp * modInverse(pp, arr[i]));
            result %= product;
        }

        return result;
    }
}
