import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            System.out.println(countWays(n, m));
        }
        sc.close();
    }

    public static long countWays(int n, int m) {
        int N = n + m;
        int R = Math.min(n, m);
        long result = 1;

        for (int i = 1; i <= R; i++) {
            result *= (N - i + 1);
            result /= i;
        }

        return result;
    }
}
