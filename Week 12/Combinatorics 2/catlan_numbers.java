
/*
Time complexity: O(N^2)
Space complexity: O(N)
where N is given in input
*/
import java.io.*;
import java.util.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static long MOD = (long) Math.pow(10, 9) + 7;

    static void pre(long catalan[], int n) {
        catalan[0] = catalan[1] = 1;
        for (int i = 2; i <= n; i++) {
            catalan[i] = 0;
            for (int j = 0; j < i; j++) {
                catalan[i] += (catalan[j] * catalan[i - j - 1]) % MOD;
                if (catalan[i] >= MOD) {
                    catalan[i] -= MOD;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        long[] catalan = new long[1002];
        pre(catalan, 1000);
        int t = Integer.parseInt(br.readLine().trim());
        while (t != 0) {
            int n = Integer.parseInt(br.readLine().trim());
            System.out.println(catalan[n]);
            t--;
        }
    }
}