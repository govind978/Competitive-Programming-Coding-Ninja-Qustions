public class Solution {
    public static int jumbledPens(int n, int red, int green, int blue) {
        // Calculate the number of distinct orderings using combinatorics
        long MOD = 1000000007;
        long[] fact = new long[n + 1];
        fact[0] = 1;

        for (int i = 1; i <= n; i++) {
            fact[i] = (fact[i - 1] * i) % MOD;
        }

        long result = (fact[n] * modInverse(fact[red], MOD)) % MOD;
        result = (result * modInverse(fact[green], MOD)) % MOD;
        result = (result * modInverse(fact[blue], MOD)) % MOD;

        return (int) result;
    }

    // Calculate modular inverse using fast exponentiation
    private static long modInverse(long a, long MOD) {
        long result = 1;
        long base = a;
        long exponent = MOD - 2;

        while (exponent > 0) {
            if (exponent % 2 == 1) {
                result = (result * base) % MOD;
            }
            base = (base * base) % MOD;
            exponent /= 2;
        }

        return result;
    }
}
