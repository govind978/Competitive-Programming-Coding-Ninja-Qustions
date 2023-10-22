public class Solution {

    public static long countRectangles(int m, int n) {
        long rectangles = (long) m * (m + 1) * n * (n + 1) / 4;
        return rectangles;
    }
}
