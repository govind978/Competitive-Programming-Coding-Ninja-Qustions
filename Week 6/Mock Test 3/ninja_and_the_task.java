public class Solution {
    public static long countValues(long x) {
        // Write your code here.
        long count = 0;
        for (long y = 1; y < x; y++) {
            if ((x ^ y) > x) {
                count++;
            }
        }
        return count;
    }
}