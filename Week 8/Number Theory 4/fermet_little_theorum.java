
public class Solution {

    public static int modPow(int base, int exponent, int mod) {
        int result = 1;
        while (exponent > 0) {
            if (exponent % 2 == 1) {
                result = (result * base) % mod;
            }
            base = (base * base) % mod;
            exponent /= 2;
        }
        return result;
    }

    public static int modInverse(int num, int mod) {
        return modPow(num, mod - 2, mod);
    }

    public static int fermatLittle(int n, int r, int p) {
        if (r == 0) {
            return 1;
        }

        int[] fact = new int[n + 1];
        fact[0] = 1;

        for (int i = 1; i <= n; i++) {
            fact[i] = (fact[i - 1] * i) % p;
        }

        int num = fact[n];
        if (r > n)
            return 0;
        int den = (fact[r] * fact[n - r]) % p;
        int denInverse = modInverse(den, p);

        return (num * denInverse) % p;
    }
    // public static int fermatLittle(int a, int b, int c) {
    // // Write your code here
    // }

}
