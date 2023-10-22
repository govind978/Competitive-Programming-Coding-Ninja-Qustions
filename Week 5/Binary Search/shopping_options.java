/*
	Time Complexity: O((R * S + P * Q) * log(P * Q))
	Space Complexity: O(P * Q)

	Where 'P', 'Q', 'R', and 'S' denote the number of pants, shirts, shoes, skirts respectively.
*/

import java.util.*;

public class Solution {
    public static int getSmallerNumbers(int[] vec, int val) {
        int left = 0, right = vec.length;

        while (left < right) {
            int mitd = (left + right) / 2;

            if (vec[mid] <= val) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    public static int shoppingOptions(int[] pants, int[] shirts, int[] shoes, int[] skirts, int budget) {

        // To store number of ways to buy
        int answer = 0;

        int X = pants.length;
        int Y = shirts.length;

        int[] sumOfPantsAndShirts = new int[X * Y];

        int counter = 0;

        // To generate every possible combinations of pants and shirts.
        for (int i = 0; i < pants.length; i++) {
            for (int j = 0; j < shirts.length; j++) {

                // Store the sum in the array
                sumOfPantsAndShirts[counter] = pants[i] + shirts[j];

                counter = counter + 1;
            }
        }

        // Sort all the combinations in ascending order.
        Arrays.sort(sumOfPantsAndShirts);

        for (int i = 0; i < shoes.length; i++) {
            for (int j = 0; j < skirts.length; j++) {

                // To store remaining amount
                int remainingAmount = budget - shoes[i] - skirts[j];

                // Adding all smaller or equal numbers than remainingAmount.
                answer = answer + getSmallerNumbers(sumOfPantsAndShirts, remainingAmount);
            }
        }

        return answer;
    }
}