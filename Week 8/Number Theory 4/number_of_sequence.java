/*
    Time Complexity : O(N * Log(N))
    Space Complexity : O(N)

    Where 'N' is the size of the array.
*/

import java.util.ArrayList;

public class Solution {

    private static int check(int x, ArrayList<Integer> v) {
        int cur = -1;
        int n = v.size();
        for (int i = x; i < n; i += x) {
            if (v.get(i).intValue() != -1) {
                if (cur == -1) {
                    cur = v.get(i) % x;
                } else {
                    if (v.get(i) % x != cur) {
                        return 0;
                    }
                }
            }
        }

        if (cur == -1) {
            return x;
        } else {
            return 1;
        }
    }

    public static int solve(ArrayList<Integer> arr) {
        int n = arr.size();

        ArrayList<Integer> v = new ArrayList<Integer>(n + 1);
        v.add(0);

        for (int i = 1; i <= n; i++) {
            v.add(arr.get(i - 1));
        }

        if (v.get(1) != 0 && v.get(1) != -1) {
            return 0;
        }

        ArrayList<Long> isprime = new ArrayList<Long>(n + 1);
        for (int i = 0; i <= n; i++) {

            isprime.add(1L);
        }

        int sqrtn = Math.min((int) (Math.sqrt(n)) + 1, n);

        ArrayList<Long> primes = new ArrayList<Long>();

        for (int i = 2; i <= sqrtn; i++) {
            Long cur = 2L;
            cur *= (i);
            if (isprime.get(i) == 0) {
                continue;
            }

            while (cur <= n) {
                int val = cur.intValue();
                isprime.set(val, 0L);
                cur += i;
            }
        }
        for (int i = 2; i <= n; i++) {
            if (isprime.get(i) != 0) {
                long temp = i;
                primes.add(temp);
            }
        }

        long res = 1L;

        for (int i = 0; i < primes.size(); i++) {
            long cur = primes.get(i);
            while (cur <= n) {
                int temp = check((int) cur, v);
                if (temp == cur) {
                    res *= primes.get(i);
                } else {
                    res *= temp;
                }
                res %= 1000000007;
                cur *= primes.get(i);
            }
        }

        int finalans = (int) res;
        return finalans;
    }
}