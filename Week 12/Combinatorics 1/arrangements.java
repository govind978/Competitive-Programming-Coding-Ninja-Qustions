public class Solution {
    public static int countArrangement(int n) {
        boolean[] used = new boolean[n + 1];
        return countArrangements(n, 1, used);
    }

    private static int countArrangements(int n, int position, boolean[] used) {
        if (position > n) {
            return 1;
        }

        int count = 0;
        for (int num = 1; num <= n; num++) {
            if (!used[num] && (num % position == 0 || position % num == 0)) {
                used[num] = true;
                count += countArrangements(n, position + 1, used);
                used[num] = false;
            }
        }

        return count;
    }
}
