/*
    Time Complexity: O(N * M)
    Space Complexity: O(1)

    where 'N', and 'M' are the size of the given array.
*/

import java.util.ArrayList;

public class Solution {
    public static int maxXOR(int n, int m, ArrayList<Integer> arr1, ArrayList<Integer> arr2) {

        int maxVal = 0;

        // Check each possible pairs.
        for (int i = 0; i < n; i++) {

            for (int j = 0; j < m; j++) {

                // If xor value of current pair is greater than maxVal.
                if ((arr1.get(i) ^ arr2.get(j)) > maxVal) {
                    maxVal = arr1.get(i) ^ arr2.get(j);
                }
            }
        }

        return maxVal;
    }
}