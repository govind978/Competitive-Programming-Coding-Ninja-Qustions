/*
    Time complexity: O(1)
    Space complexity: O(1) 
*/

public class Solution {

    public static boolean meteorGarden(int xC, int yC, int rad, int x1, int y1, int x2, int y2) {

        // Using formula for finding nearest point.
        int xN = Math.max(x1, Math.min(xC, x2));
        int yN = Math.max(y1, Math.min(yC, y2));

        // Checking whether distance is smaller than or equal to radius or not.
        if (((xC - xN) * (xC - xN)) + ((yC - yN) * (yC - yN)) <= (rad * rad)) {
            return true;
        }

        // If distance is greater than radius.
        else {
            return false;
        }
    }
}