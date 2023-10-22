import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int maximumPoints(int n, ArrayList<Integer> arr) {
        int M = 0;

        // Run a loop from 0 to 'n' and find the maximum element
        for (int i = 0; i < n; i++) {
            M = Math.max(M, arr.get(i));
        }

        M++;

        // Take a frequency array and store the initial frequency of all elements from 1
        // to 1000 to 0.
        int[] frequency = new int[M];
        Arrays.fill(frequency, 0);

        // Run a loop from 0 to 'n' and update the frequency of each number
        for (int i = 0; i < n; i++) {
            frequency[arr.get(i)]++;
        }

        // Take a 'dp' array of length 'M', it stores the maximum Points till 'i'
        int[] dp = new int[M];

        // Initialize 'dp[0]' to 0 and dp[1] equal to frequency[1]
        dp[0] = 0;
        dp[1] = frequency[1];

        // Run a loop from 2 to 'M' and update the value of dp[i]
        for (int i = 2; i < M; i++) {
            // This is the transition formula of dp
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + i * frequency[i]);
        }

        // Return dp[M - 1]
        return dp[M - 1];
    }

    public static void main(String[] args) {
        /*
         * Your class should be named Main.
         * Read input as specified in the question.
         * Print output as specified in the question.
         */

        Scanner sc = new Scanner(System.in);
        int test = sc.nextInt();
        while (test-- > 0) {
            int n = sc.nextInt();
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                list.add(sc.nextInt());
            }
            System.out.println(maximumPoints(n, list));
        }

    }

}