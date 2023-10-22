/*
    Time Complexity: O(1).
    Space Complexity: O(1).
*/

public class Solution {
    public static String morningWalk(int a, int b, int c, int d) {
        int alexStart = Math.min(a, b);
        int alexEnd = Math.max(a, b);
        int bobStart = Math.min(c, d);
        int bobEnd = Math.max(c, d);

        int end = Math.min(alexEnd, bobEnd);
        int start = Math.max(alexStart, bobStart);

        /*
         * If start is equal to end then end point of
         * Bob's path is equal to starting point of Alex's
         * path and vice versa.
         */
        if (start == end) {
            return "Point";
        }

        // If start is less than end then their path intersect.
        if (end > start) {
            return "Intersect";
        }

        // Else they never meet.
        return "Never";

    }

}