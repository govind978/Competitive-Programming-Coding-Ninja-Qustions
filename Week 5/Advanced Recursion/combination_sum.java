
/*
    Time Complexity: O(2^N)
    Space Complexity: O(N*2^N)

    where N is the total number of elements in the aray.
*/
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Solution {
    public static List<List<Integer>> combSum(int[] arr, int b) {
        Arrays.sort(arr);

        List<List<Integer>> result = new ArrayList<>();

        List<Integer> currList = new ArrayList<>();

        solve(result, 0, 0, currList, b, arr);

        return result;
    }

    public static void solve(List<List<Integer>> res, int currIndex, int currSum, List<Integer> currList, int b,
            int[] arr) {
        if (currSum == b) {
            res.add(new ArrayList<>(currList));
            return;
        }
        if (currIndex == arr.length) {
            return;
        }

        solve(res, currIndex + 1, currSum, currList, b, arr);

        int count = 0;
        while (currSum <= b) {
            count++;

            currSum += arr[currIndex];

            currList.add(arr[currIndex]);

            solve(res, currIndex + 1, currSum, currList, b, arr);
        }

        /*
         * We remove ARR[currIndex] from currList, 'count'
         * number of times for backtracking
         */
        while (count-- > 0) {
            currList.remove(currList.size() - 1);
        }
    }
}