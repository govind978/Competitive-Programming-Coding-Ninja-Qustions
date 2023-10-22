
import java.util.ArrayList;

public class Solution {
    public static int search(int arr[], int k) {
        int n = arr.length;
        int s = 0, e = n - 1;

        while (s <= e)

        {

            int mid = s + (e - s) / 2;

            if (arr[(mid)] == k)
                return mid;

            if (arr[(0)] < arr[(mid)])

            {

                if (arr[(s)] <= k && k <= arr[(mid)])

                    e = mid - 1;

                else

                    s = mid + 1;

            }

            else

            {

                if (arr[(mid)] <= k && k <= arr[(e)])

                    s = mid + 1;

                else

                    e = mid - 1;

            }

        }

        return -1;
    }
}