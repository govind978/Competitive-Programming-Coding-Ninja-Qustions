/*
    Time complexity: O(N) 
    Space complexity: O(N)
	
    Where N is the size of the string.
*/

public class Solution {
    public static int specialPalindromes(String str) {
        // Initialise ans to store count of special palindromes
        int ans = 0;

        // Initialise variable to store length of string
        int n = str.length();

        // Create an array with all values 0 to store count of continuous same
        // characters
        int[] countSame = new int[n];
        for (int i = 0; i < n; i++) {
            countSame[i] = 0;
        }

        // First pointer
        int a = 0;

        // Start traversing through the string
        while (a < n) {

            // Initialise a variable to store temporary count of continuous same characters
            int countContinuous = 1;

            // Initialise a variable to store next character
            int b = a + 1;

            // Count same characters
            while (b < n && str.charAt(a) == str.charAt(b) && (b < n)) {
                countContinuous++;
                b++;
            }

            // CASE: 1
            // Count total substrings from the count of continuous same characters
            ans = ans + (countContinuous * (countContinuous - 1) / 2);

            // Store this count in the array
            countSame[a] = countContinuous;

            a = b;
        }

        // CASE: 2
        for (int i = 1; i < n; i++) {

            // If current character is equal to previous character
            if (str.charAt(i) == str.charAt(i - 1)) {

                // Update values of array
                countSame[i] = countSame[i - 1];
            }

            if (i > 0 && i < (n - 1) && (str.charAt(i - 1) == str.charAt(i + 1))
                    && (str.charAt(i) != str.charAt(i + 1))) {
                ans += Math.min(countSame[i - 1], countSame[i + 1]);
            }
        }

        // Return count
        return ans;
    }
}