/*
   Time Complexity = O(N^2)
   Space Complexity = O(log(N))

   Where N is the number of elements in the given array/list.
*/

import java.util.Arrays;

public class Solution {
    // check if a distance of x is possible b/w each cow
    private static boolean check(int x, int k, int[] stalls) {
        // Greedy approach, put each cow in the first place you can.
        int cowsPlaced = 1, lastPos = stalls[0];

        int n = stalls.length;

        for (int i = 1; i < n; i++) {

            if ((stalls[i] - lastPos) >= x) {
                cowsPlaced = cowsPlaced + 1;
                if (cowsPlaced == k) {
                    return true;
                }

                // Assign current position of stall as the lastPos.
                lastPos = stalls[i];
            }
        }

        return false;
    }

    public static int aggressiveCows(int[] stalls, int k) {
        Arrays.sort(stalls);

        int pos = 0;

        // Iterate through all the stalls and try to find out the largest minimum
        // distance.
        for (int i = 0; i < 1000000000; i++) {
            if (check(i, k, stalls)) {
                pos = i;
            } else {
                // Break as soon as it is no more possible to extend the minimum distance.
                break;
            }
        }

        return pos;
    }
}