/*
	Time Complexity: O(N ^ 2)
	Space Complexity: O(N ^ 2)

	Where N is the number of coordinates.
*/

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Solution {
    public static int maxPointsOnLine(int[][] points, int n) {
        // Base cases.
        if (n == 0) {
            return 0;
        }

        if (n <= 2) {
            return n;
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            HashMap<Double, Integer> mp = new HashMap<Double, Integer>();
            int samePoint = 0, sameX = 1;
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }

                // Checking if the points are overlapping.
                if (points[j][0] == points[i][0] && points[j][1] == points[i][1]) {
                    samePoint++;
                }

                // Checking if the points have same X coordinates.
                if (points[j][0] == points[i][0]) {
                    sameX++;
                    continue;
                }

                // Finding the slope of the line.
                double k = (double) (points[j][1] - points[i][1]) / (double) (points[j][0] - points[i][0]);
                if (!mp.containsKey(k)) {
                    mp.put(k, 2);
                } else {
                    mp.replace(k, mp.get(k) + 1);
                }
            }

            // Iterating over the map and finding maximum.
            for (Map.Entry x : mp.entrySet()) {
                ans = Math.max(ans, (int) x.getValue() + samePoint);
            }
            ans = Math.max(ans, sameX);
        }

        return ans;
    }
}