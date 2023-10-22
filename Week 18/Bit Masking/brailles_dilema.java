import java.io.*;
import java.util.*;

/*
   Time Complexity:O((2^n) *k)
   Space Complexity:O((2^n))

   n is the number of strings and k is the length of the string.

*/

import java.util.ArrayList;

public class Solution {

    public static void main(String[] args) throws IOException {

        // Your class should be named Solution
        // Write your code here
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt(); // Number of test cases

        while (T-- > 0) {
            int N = scanner.nextInt(); // Number of binary strings
            ArrayList<String> binaryStrings = new ArrayList<>();

            // Read the binary strings
            for (int i = 0; i < N; i++) {
                String a = scanner.next();
                binaryStrings.add(a);
            }

            // Call the helper function to process the input
            int result = minimumTouchesRequired(N, binaryStrings);

            // Print the output from the function
            System.out.println(result);
        }

    }

    public static // Create a recursive function count_steps(int mask, String arr[],int k,int n,
                  // int pos = 0) where k is the size of string.
    int count_touches(int mask, ArrayList<String> arr, int k, int n, int[] dp) {

        // When mask contains only 1 bit it means every string is recognised.
        if (mask > 0 && (mask & (mask - 1)) == 0) {
            // Return 0;
            return 0;
        }

        // Initialise a variable 'ans' = 1e9 to store the answer for current mask.
        int ans = (int) 1e9;

        // Adding memoization condition.
        if (dp[mask] != -1) {
            return dp[mask];
        }

        // Checking for each positions from '0' to 'k-1' where n is the size of binary
        // strings, to find the minimum answer.
        for (int pos = 0; pos < k; pos++) {

            // Create two-variable mask1=0 and mask2=0 to group strings having pos character
            // 1 and 0.
            int mask1 = 0;
            int mask2 = 0;

            // Declare a variable 'cnt' to store the total number of touches for the current
            // 'pos'.
            int cnt = 0;

            // Iterate over all the strings for variable 'i' from 0 to 'n-1' to split the
            // strings based on character at pos-th position.
            for (int i = 0; i < n; i++) {
                // If position 'i' is set in mask means we have to check for the ith string.
                if (((1 << i) & mask) != 0) {
                    // Increment cnt.
                    cnt++;

                    // check if 'arr[i][pos]' == 1.
                    if (arr.get(i).charAt(pos) == '1') {
                        // Set the ith bit in mask2's binary representation.
                        mask2 |= (1 << i);
                    }
                    // Else
                    else {
                        // Set the ith bit in mask1's binary representation.
                        mask1 |= (1 << i);
                    }
                }
            }

            // Check if 'mask1' is greater than 0 and 'mask2' is greater than zero.
            if (mask1 > 0 && mask2 > 0) {

                // Declare a variable 'a' to store the ans of the recursive function for mask1.
                int a = count_touches(mask1, arr, k, n, dp);

                // Declare a variable 'b' to store the ans of the recursive function for mask2.
                int b = count_touches(mask2, arr, k, n, dp);

                // Make 'ans' = min('ans', 'cnt'+'a'+'b').
                ans = Math.min(ans, cnt + a + b);
            }
        }

        // Return 'dp[mask]' = 'ans'.
        return dp[mask] = ans;

    }

    public static int minimumTouchesRequired(int n, ArrayList<String> arr) {

        // Initialise a variable 'k' with arr[0].size to store the length of the binary
        // string.
        int k = arr.get(0).length();

        // Initialise a dp of size 2^n with -1.
        int[] dp = new int[1 << n];
        for (int i = 0; i < (1 << n); ++i) {
            dp[i] = -1;
        }

        // Return the recursive function with value of current mask as 2^n-1;
        return count_touches((1 << n) - 1, arr, k, n, dp);
    }
}