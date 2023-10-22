
/*
Time complexity: O(2 * M + N + log(mod))
Space complexity: O(2 * M + N)
where N and M are give in the question
*/
import java.io.*;
import java.util.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static long MOD = (long) Math.pow(10, 9) + 7;
    static int N = 200043;
    static long[] fact = new long[N];

    static long binpow(long x, long y) {
        long z = 1;
        while (y != 0) {
            if ((y & 1) != 0)
                z = (z * x) % MOD;
            x = (x * x) % MOD;
            y >>= 1;
        }
        return z;
    }

    static void precalc() {
        fact[0] = 1;
        for (int i = 1; i < N; i++)
            fact[i] = (fact[i - 1] * i) % MOD;
    }

    public static void main(String[] args) throws IOException {
        precalc();
        int t = Integer.parseInt(br.readLine().trim());
        while (t != 0) {
            long n, m;
            String[] strNums = br.readLine().split(" ");
            n = Long.parseLong(strNums[0]);
            m = Long.parseLong(strNums[1]);
            long ans = fact[(int) (n + 2 * m - 1)];
            long a = (fact[2 * (int) m] * fact[(int) n - 1]) % MOD;
            a = binpow(a, MOD - 2);
            ans = (ans * a) % MOD;
            System.out.println(ans);
            t--;
        }
    }
}