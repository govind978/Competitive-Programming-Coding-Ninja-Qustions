public class Solution {

	public static int sum(int input[]) {
		/*
		 * Your class should be named Solution
		 * Don't write main().
		 * Don't read input, it is passed as function argument.
		 * Return output and don't print it.
		 * Taking input and printing output is handled automatically.
		 */
		int sum = 0;
		for (int i : input)
			sum += i;
		return sum;
	}
}