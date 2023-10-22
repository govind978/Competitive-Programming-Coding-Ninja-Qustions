
public class Solution {
	public static int minSwap(int N, int M, int K, String S, char[] arr) {

		/*
		 * Declare a array of type boolean , where index 0 represent letter 'a', 1
		 * represent letter 'b'.
		 * if flag[i] is true means the letter corresponding to 'i' is present in 'arr'.
		 */
		boolean flag[] = new boolean[26];

		// Initialize the flag array with false(initially no letter is present)
		for (int i = 0; i < 26; i++) {
			flag[i] = false;
		}

		// Iterate through 'arr' and mark index corresponding to that letter
		for (int i = 0; i < M; i++) {
			char c = arr[i];

			// Mark index corresponding to c as true( present )
			flag[c - 'a'] = true;
		}

		// Declare 2D array, which keep count of a letter in the sequence of k-th index,
		int frequency[][] = new int[K][26];

		// Initially to 0 there is no letter
		for (int i = 0; i < K; i++) {

			for (int j = 0; j < 26; j++) {
				frequency[i][j] = 0;
			}
		}

		// Now loop through string 'S' from 0 to N-1
		for (int i = 0; i < N; i++) {

			// Increment the frequency of letter 's[i]' in the sequence of kth index by 1
			frequency[i % K][S.charAt(i) - 'a']++;
		}

		// Declare variable to store the answer (minimum number of swaps required)
		int ans = 0;

		// Now loop for k from 0 to K - 1
		for (int i = 0; i < K; i++) {

			// Stores the maximum count of a letter in current ith series
			int mx = 0;

			// Variable to store the toatal number of letters in the ith series
			int totalCount = 0;

			// Loop for j from 0 to 25
			for (int j = 0; j < 26; j++) {

				// Update totalCount
				totalCount += frequency[i][j];

				// If frequency[i][j] is greater than current maximum i.e mx and flag[j] is
				// equal to 1(letter is present in given arr)
				if (frequency[i][j] > mx && flag[j]) {
					mx = frequency[i][j];
				}
			}

			// Update the answer by number of swaps required for current ith series
			ans += (totalCount - mx);
		}

		// Return answer.
		return ans;

	}

}