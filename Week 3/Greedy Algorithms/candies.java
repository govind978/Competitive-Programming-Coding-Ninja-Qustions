import java.util.*;

public class Solution {
    public static int requiredCandies(int[] students) {
        int n = students.length;

        int[] left = new int[n];
        int[] right = new int[n];
        int total = 0;

        Arrays.fill(left, 1);
        Arrays.fill(right, 1);

        for (int i = 1; i < n; i++) {
            if (students[i] > students[i - 1]) {
                left[i] = left[i - 1] + 1;
            }
        }

        for (int i = n - 2; i >= 0; i--) {
            if (students[i] > students[i + 1]) {
                right[i] = right[i + 1] + 1;
            }
        }

        for (int i = 0; i < n; i++) {
            total += Math.max(left[i], right[i]);
        }
        return total;
    }
}