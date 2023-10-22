import java.io.*;
import java.util.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static long mod = (long) Math.pow(10, 9) + 7;

    static void fac_(long[] fac, int size) {
        fac[0] = 1;
        for (int i = 1; i < size; i++) {
            fac[i] = (fac[i - 1] * i) % mod;
        }
    }

    static long power(long a, long b, long mod) {
        if (b == 0) {
            return 1;
        }
        long ans = power(a, b / 2, mod);
        ans = (ans * ans) % mod;
        if ((b % 2) != 0) {
            ans = (ans * a) % mod;
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine().trim());

        long[] fac = new long[200001];
        fac_(fac, 200001);
        while (t != 0) {
            int n, k;
            String[] strNums = br.readLine().split(" ");
            n = Integer.parseInt(strNums[0]);
            k = Integer.parseInt(strNums[1]);

            long ans = fac[n + k - 1];
            long a = fac[n], b = fac[k - 1];
            a = (a * b) % mod;

            a = power(a, mod - 2, mod);
            ans = (ans * a) % mod;
            System.out.println(ans);
            t--;
        }
    }
}