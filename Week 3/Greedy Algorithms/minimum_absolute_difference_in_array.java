import java.util.*;

public class Solution {

    public static void main(String[] args) {
        /*
         * Your class should be named Solution.
         * Read input as specified in the question.
         * Print output as specified in the question.
         * Write your code here
         */
        Scanner sc = new Scanner(System.in);
        int test = sc.nextInt();
        while (test-- > 0) {
            int n = sc.nextInt();
            int arr[] = new int[n];
            for (int i = 0; i < n; i++)
                arr[i] = sc.nextInt();
            System.out.println(minDiff(n, arr));
        }
    }

    static int minDiff(int n, int[] arr) {

        int ans = Integer.MAX_VALUE;

        Arrays.sort(arr);

        for (int i = 1; i <= arr.length - 1; i++) {

            ans = Math.min(ans, Math.abs(arr[i] - arr[i - 1]));

        }

        return ans;

    }

}
