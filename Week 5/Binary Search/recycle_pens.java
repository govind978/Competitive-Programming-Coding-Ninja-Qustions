/*
        Time complexity : O(N)
        Space complexity : O(1),

        where N is the number of empty pens.
*/

public class Solution {

	public static int recyclePens(int n, int r, int k, int c) {
		int max = 0;
		int left = 0;
		int right = n;
		while (left <= right) {
			long i = (left + right) / 2;
			long recycle_cost = (long) (n - i) * k;
			long total_cost = recycle_cost + r;
			if (total_cost >= (long) c * i) {
				left = (int) i + 1;
				max = (int) i;
			} else {
				right = (int) i - 1;
			}
		}
		return max;
	}

}