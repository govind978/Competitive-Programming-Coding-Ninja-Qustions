
/*
	Time Complexity: O((log(max(A, B)) ^ 2) * K)
	Space Complexity: O((log(max(A, B)) ^ 2) * K)
	
	Where 'A', 'B' and 'K' are given numbers
*/

import java.util.ArrayList;
import java.util.List;

public class Solution {
    static boolean isPrime[] = new boolean[122];

    static long dp[][][][] = new long[13][121][1005][3];

    // Function to mark the prime numbers in 'isPrime'
    public static void sieve() {

        isPrime[0] = false;
        isPrime[1] = false;

        for (int i = 2; i * i <= 120; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= 120; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        return;
    }

    // Utility function to find the number magical number
    public static long helper(List<Integer> list, int idx, int sum, int rem, int K, int isSmall) {

        // If vector size is equal to 'idx'
        if (list.size() == idx) {

            // If 'sum' is prime number and remainder 'rem' is zero.
            if (isPrime[sum] && rem == 0) {
                // return 1
                return 1;
            }

            // Else return 0
            return 0;
        }

        // If 'dp[idx][sum][rem][isSmall]' is not equal to -1 then return
        // 'dp[idx][sum][rem][isSmall]'
        if (dp[idx][sum][rem][isSmall] != -1) {
            return dp[idx][sum][rem][isSmall];
        }

        // Set the limit of current digit, if 'isSmall' is one then limit is 9,
        // otherwise vec[idx]
        int limit = isSmall > 0 ? 9 : list.get(idx);

        // Take a variable 'ans' and initialized it to zero
        long ans = 0;

        // Run a loop from 0 to limit, to set the digit to current index
        for (int i = 0; i <= limit; i++) {

            // If 'isSmall' is 0 and 'i' is equal to 'limit'
            if (!(isSmall > 0) && limit == i) {
                // Recursively call the helper function on next index 'idx'
                ans += helper(list, idx + 1, sum + i, (rem * 10 + i) % K, K, isSmall);

            } else {
                // Recursively call the helper function on next index 'idx', updated sum,
                // remainder and setting number as small
                ans += helper(list, idx + 1, sum + i, (rem * 10 + i) % K, K, 1);
            }
        }

        // Update the 'dp[idx][sum][rem][isSmall]'' to 'ans'
        dp[idx][sum][rem][isSmall] = ans;

        // Return 'ans'
        return ans;

    }

    public static List<Integer> reverseArrayList(List<Integer> alist) {
        // Arraylist for storing reversed elements
        ArrayList<Integer> revArrayList = new ArrayList<Integer>();
        for (int i = alist.size() - 1; i >= 0; i--) {

            // Append the elements in reverse order
            revArrayList.add(alist.get(i));
        }

        // Return the reversed arraylist
        return revArrayList;
    }

    public static long countNumbers(long num, int K) {
        // Take an array to store the digits of 'num'
        List<Integer> list = new ArrayList<>();

        // Run a loop while 'num' is not equal to zero
        while (num > 0) {
            // Push the lowest significant digit (last digit) in vector 'vec'
            list.add((int) (num % 10));

            // Remove the last digit of 'num'
            num /= 10;
        }

        // Reverse the sequence of digits in 'vec'
        list = reverseArrayList(list);

        // Initialize 'dp' with -1
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 120; j++) {
                for (int k = 0; k < 1005; k++) {
                    for (int l = 0; l < 3; l++) {
                        dp[i][j][k][l] = -1;
                    }
                }
            }
        }

        // Call the helper function
        long ans = helper(list, 0, 0, 0, K, 0);

        // return 'ans'
        return ans;
    }

    // Function to find the count of magical numbers
    public static long countMagicalNumbers(long A, long B, int K) {
        for (int i = 0; i <= 120; i++) {
            isPrime[i] = true;
        }
        sieve();
        long ans1 = countNumbers(B, K);
        long ans2 = countNumbers(A - 1, K);

        return (ans1 - ans2);
    }

}
