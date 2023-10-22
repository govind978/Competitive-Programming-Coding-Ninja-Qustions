/*
	Time Complexity : O(N * log(N))
	Space Complexity : O(N)

	Where 'N' is the number of lights.
*/

import java.util.ArrayList;
import java.util.Arrays;

public class Solution {
	public static ArrayList<Integer> calculateTrafficLights(int n, int[] points, int[] sortedPoints) {
		int left[] = new int[n + 2];
		int right[] = new int[n + 2];
		int passages[] = new int[n + 1];
		int maxLength = 0;

		ArrayList<Integer> ans = new ArrayList<>();

		for (int i = 1; i < n + 1; i++) {
			right[i - 1] = left[i + 1] = i;
			maxLength = Math.max(maxLength, sortedPoints[i] - sortedPoints[i - 1]);
		}

		maxLength = Math.max(sortedPoints[n + 1] - sortedPoints[n], maxLength);
		left[1] = 0;
		right[n] = n + 1;

		// Descending loop.
		for (int i = n; i > 0; i--) {

			// Write the maximum calculated length in its corresponding position.
			passages[i] = maxLength;

			// Find key points[i] in array sortedPoints with binarySearch.
			int pos = Arrays.binarySearch(sortedPoints, points[i]);

			// Swap values in position.
			left[right[pos]] = left[pos];
			right[left[pos]] = right[pos];
			maxLength = Math.max(sortedPoints[right[pos]] - sortedPoints[left[pos]], maxLength);
		}

		for (int i = 1; i < n + 1; i++) {
			ans.add(passages[i]);
		}

		return ans;
	}

	public static ArrayList<Integer> trafficLights(int n, int x, int[] pos) {

		ArrayList<Integer> ans = new ArrayList<>();
		int sortedPoints[] = new int[n + 2];

		int[] points = new int[n + 1];
		for (int i = 1; i <= n; i++)
			points[i] = pos[i - 1];

		for (int i = 1; i <= n; i++) {
			sortedPoints[i] = pos[i - 1];
		}

		// Add min value to left = 0 and max value to right = x, the length of the
		// street.
		sortedPoints[0] = 0;
		sortedPoints[n + 1] = x;

		// Sort array of positions with min and max values.
		Arrays.sort(sortedPoints);
		ans = calculateTrafficLights(n, points, sortedPoints);

		return ans;
	}
}
