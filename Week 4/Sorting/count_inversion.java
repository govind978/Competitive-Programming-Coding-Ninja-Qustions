public class Solution {
    public static long getInversions(long[] arr, int n) {
        // Variable to store the total inversions in the array.
        long totalInversions = 0;

        // Check for each element whether any smaller element is present on right side.
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i] > arr[j]) {
                    totalInversions += 1;
                }

            }
        }
        return totalInversions;
    }
}