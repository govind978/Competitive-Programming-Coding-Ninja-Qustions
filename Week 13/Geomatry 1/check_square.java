/*
    Time Complexity: O(1)
    Space Complexity: O(1)
*/

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Solution {
    public static boolean isSquare(List<Long> x, List<Long> y) {
        long distSq[] = new long[6];
        int t = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = i + 1; j < 4; j++) {
                long dist = (1L * (x.get(i) - x.get(j)) * 1L * (x.get(i) - x.get(j))
                        + 1L * ((y.get(i) - y.get(j)) * 1L * (y.get(i) - y.get(j))));
                distSq[t] = dist;
                t += 1;
            }
        }

        Arrays.sort(distSq);

        // Check if the distance of all the sides are equal
        // and the length of the diagonals are equal to the length of each side *
        // root(2).
        if (distSq[0] == distSq[1] &&
                distSq[1] == distSq[2] &&
                distSq[2] == distSq[3] &&
                distSq[4] == distSq[5] &&
                distSq[0] * 2L == distSq[4] &&
                distSq[0] > 0) {
            return true;
        }
        return false;
    }
}