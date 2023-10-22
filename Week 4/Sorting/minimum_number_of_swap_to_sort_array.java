/*
	Time complexity: O(N ^ 2)
	Space complexity: O(N)

	where 'N' is the size of the given array.
*/

import java.util.Arrays;

public class Solution {

	// Swap arr[i] with arr[j].
	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	// Returns index of element if present in array, else return -1.
	private static int indexOf(int[] arr, int element) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == element) {
				return i;
			}
		}
		return -1;
	}

	// Returns the minimum number of swaps required to sort the array.
	public static int minSwaps(int[] arr) {

		int count = 0;
		int[] temp = Arrays.copyOfRange(arr, 0, arr.length);
		Arrays.sort(temp);
		for (int i = 0; i < arr.length; i++) {
			/*
			 * Checking whether the current element
			 * is at the right place or not.
			 */
			if (arr[i] != temp[i]) {
				count++;

				/*
				 * Swap the current element with the right index
				 * so that arr[0] to arr[i] is sorted.
				 */
				swap(arr, i, indexOf(arr, temp[i]));
			}
		}

		return count;
	}
}