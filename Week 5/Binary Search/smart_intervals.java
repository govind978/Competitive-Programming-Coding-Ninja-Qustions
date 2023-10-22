/*
   Time Complexity: O(N ^ 2)
   Space Complexity: O(N)
	
	Where N is the number of intervals.
*/

import java.util.ArrayList;

public class Solution {

    public static ArrayList<Integer> smartInterval(ArrayList<ArrayList<Integer>> intervals, int n) {

        ArrayList<Integer> result = new ArrayList<Integer>(n);

        // Loop to consider all the intervals
        for (int i = 0; i < n; i++) {
            // Initialise the index of smart interval
            int smartIndex = -1;

            // To find the smart interval with minimum starting point
            int lowestInterval = Integer.MAX_VALUE;

            // Loop to find the smart interval
            for (int j = 0; j < n; j++) {
                // Check if this interval satisfy the constraints
                if (intervals.get(j).get(0) >= intervals.get(i).get(1) && intervals.get(j).get(0) < lowestInterval) {
                    lowestInterval = intervals.get(j).get(0);
                    smartIndex = j;
                }
            }
            // Update the index of smart interval in output array
            result.add(smartIndex);
        }

        return result;

    }

}
