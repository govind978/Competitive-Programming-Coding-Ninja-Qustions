/*
	Time Complexity: O((N*K)^2)
    Space Complexity: O(1)

    Where 'N' denotes the number of strings and 'K' is the maximum length of the strings.
*/

public class Solution {

	public static String[] ninjaParty(String[] arr) {

		int n = arr.length;

		for (int i = 0; i < n; i++) {
			StringBuffer temp = new StringBuffer(arr[i]);
			temp.reverse();
			arr[i] = temp.toString();
		}

		for (int i = 0; i < n - 1; i++) {

			for (int j = i + 1; j < n; j++) {
				if (arr[i].compareTo(arr[j]) > 0) {
					String temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}

		for (int i = 0; i < n; i++) {
			StringBuffer temp = new StringBuffer(arr[i]);
			temp.reverse();
			arr[i] = temp.toString();
		}

		return arr;

	}
}