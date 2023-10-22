/*
   Time Complexity: O( 1 )
   Space Complexity: O( 1 )
*/

public class Solution {

    static boolean intersectCircle(int x1, int y1, int x2, int y2, int r1, int r2) {

        // Find the square of the distance.
        long dist = (long) (x1 - x2) * (x1 - x2) + (long) (y1 - y2) * (y1 - y2);

        // Find the square of the sum of radius.
        long rad = (long) (r1 + r2) * (r1 + r2);

        // If distance square <= radius square than they either touching / intersecing
        // each other.
        if (dist <= rad) {
            return true;
        } else {
            return false;
        }
    }
}