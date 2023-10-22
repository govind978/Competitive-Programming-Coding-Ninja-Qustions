/*
    Time Complexity: O(N^2)
    Space Complexity: O(N^3),

    where 'N' is the number of peoples in each city.
*/

import java.util.ArrayList;

public class Solution {
	// Create a dp with 3-states.
	public static int[][][] memo = new int[101][51][51];

	public static int solve(int index, int countA, int countB, ArrayList<ArrayList<Integer>> cost) {
		// If we are at the end of the vector cost.
		if (index == cost.size()) {
			return 0;
		}

		// If this state is already being solved.
		if (memo[index][countA][countB] != -1) {
			return memo[index][countA][countB];
		}

		// If we have already sent N persons to City A.
		if (countA == cost.size() / 2) {
			return memo[index][countA][countB] = cost.get(index).get(1) + solve(index + 1, countA, countB + 1, cost);
		}

		// Else if we have already sent N persons to City B.
		else if (countB == cost.size() / 2) {
			return memo[index][countA][countB] = cost.get(index).get(0) + solve(index + 1, countA + 1, countB, cost);
		}

		// Else send the ith person to both the cities.
		else {
			return memo[index][countA][countB] = Math.min(
					cost.get(index).get(0) + solve(index + 1, countA + 1, countB, cost),
					cost.get(index).get(1) + solve(index + 1, countA, countB + 1, cost));
		}

	}

	public static int findMinCost(int n, ArrayList<ArrayList<Integer>> cost) {
		// Initialize memo with -1.
		for (int i = 0; i <= 100; i++) {
			for (int j = 0; j <= 50; j++) {
				for (int k = 0; k <= 50; k++) {
					memo[i][j][k] = -1;
				}

			}

		}

		return solve(0, 0, 0, cost);
	}
}