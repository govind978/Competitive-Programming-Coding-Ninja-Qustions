import java.util.*;

public class Solution {

  public static ArrayList<ArrayList<Integer>> findTriplets(int[] arr, int n, int K) {

    // Write your code here.

    ArrayList<ArrayList<Integer>> ans = new ArrayList<>();

    Arrays.sort(arr);

    for (int i = 0; i < n; i++) {

      if (i > 0 && arr[i] == arr[i - 1])
        continue;

      int j = i + 1;

      int k = n - 1;

      while (j < k) {

        int sum = arr[i] + arr[j] + arr[k];

        if (sum > K)

          k--;

        else if (sum < K)

          j++;

        else {

          ArrayList<Integer> temp = new ArrayList<>();

          temp.add(arr[i]);

          temp.add(arr[j]);

          temp.add(arr[k]);

          ans.add(temp);

          j++;

          k--;

          while (j < k && arr[j] == arr[j - 1])
            j++;

          while (j < k && arr[k] == arr[k + 1])
            k--;

        }

      }

    }

    return ans;

  }

}
