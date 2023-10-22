/*
	Time Complexity  : O(N)
	Space Complexity : O(N)

	Where N is the given number.
*/

import java.util.List;
import java.util.ArrayList;

public class Solution {
    public static List<Integer> printSeries(int n, int k) {

        // Declare an empty array to store our series
        List<Integer> answer = new ArrayList<>();

        // Call the series function
        series(n, k, answer);

        // Return the answer
        return answer;
    }

    private static void series(int n, int k, List<Integer> answer) {

        // Add n to answer
        answer.add(n);

        // If n<=0 then break the recursion
        if (n <= 0) {
            return;
        }

        // Recur for series(answer, n, n-k).
        series(n - k, k, answer);

        // Add n to answer.
        answer.add(n);
    }
}