
public class Solution {

    // Function to find the gcd of two integers.
    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    // Function to find the gcd of all the elements of an array.
    public static int gcdCalc(int[] ar, int n) {
        int ans = ar[0];

        for (int i = 1; i < n; i++) {
            ans = gcd(ar[i], ans);
        }
        return ans;
    }

    public static String gameWinner(int[] arr, int n) {

        int gcdVal = gcdCalc(arr, n);

        int max = -1;

        for (int i = 0; i < n; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        // Total moves performed in game.
        int movesLeft = max / gcdVal - n;

        String ans;

        // If total moves are odd , Alice wins.
        if (movesLeft % 2 != 0) {
            ans = "Alice";
        } else {
            ans = "Bob";
        }
        return ans;
    }
}