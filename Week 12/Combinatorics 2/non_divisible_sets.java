public class Solution {
    public static int includedElements(int n, int[] set) {
        boolean[] divisible = new boolean[n + 1];

        for (int element : set) {
            for (int i = element; i <= n; i += element) {
                divisible[i] = true;
            }
        }

        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (!divisible[i]) {
                count++;
            }
        }

        return count;
    }
}
