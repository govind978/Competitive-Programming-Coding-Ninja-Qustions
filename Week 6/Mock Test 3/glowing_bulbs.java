/*

    Time Complexity : O(N * (2 ^ N) * log(K))
    Space Complexity : O(1)

    Where 'N' denotes the size of the array/list 'LABELS' and 'K' is the given integer.

*/

import java.util.ArrayList;

public class Solution {
    public static long findKthGlowingBulb(ArrayList<Integer> labels, long k) {
        // Number of switches.
        int n = labels.size();

        long start = 0, end = k * 30;

        // Binary search to find answer.
        while (end > start) {
            long mid = start + (end - start) / 2;
            long count = 0;

            // Using inclsion-exclusion principle to count number of glowing bulbs having
            // assigned integer not more than 'mid'.
            for (int i = 1; i < (1 << n); i++) {
                long product = 1;
                boolean sign = true;
                for (int j = 0; j < n; j++) {
                    if ((i & (1 << j)) > 0) {
                        product = product * labels.get(j);
                        sign = !sign;
                    }
                }
                if (sign == true) {
                    count -= mid / product;
                } else {
                    count += mid / product;
                }
            }

            // Answer will be in range [start, mid].
            if (count >= k) {
                end = mid;
            }

            // Answer will be in range [mid+1, end].
            else {
                start = mid + 1;
            }
        }

        return start;
    }
}