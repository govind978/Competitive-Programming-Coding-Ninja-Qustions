class Solution {
    public static int ninjaGame(int a[], int n) {
        // Write your code here.
        int x = 0;
        for (int i : a) {
            x ^= i;
        }
        if (x == 0)
            return 0;
        else
            return 1;
    }
}