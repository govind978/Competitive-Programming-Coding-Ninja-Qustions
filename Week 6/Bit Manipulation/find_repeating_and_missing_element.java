
import java.util.ArrayList;

public class Solution {

    public static int[] findRepeatingAndMissingNumbers(int[] arr) {
        int n = arr.length;
        int r = 0, m = 0;

        // Traversing the array
        for (int i = 0; i < n; i += 1) {
            if (arr[(Math.abs(arr[(i)]) - 1)] > 0) {

                int x = Math.abs(arr[(i)]);
                arr[x - 1] = -arr[x - 1];
            }
            // If arr[i] is already negative, it is the repeating number
            else {
                r = Math.abs(arr[(i)]);
            }
        }

        for (int i = 0; i < n; i += 1) {
            // The index at which the element is positive is the missing number
            if (arr[(i)] > 0) {
                m = i + 1;
                break;
            }
        }

        int[] ans = new int[] { r, m };

        return ans;
    }

}