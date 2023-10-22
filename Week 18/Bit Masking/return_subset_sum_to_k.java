/*
    Time Complexity : O(N * (2 ^ N))
    Space Complexity :  O(1)

    Where 'N' denotes the number of elements in the array.
*/

import java.util.ArrayList;

public class Solution {

    public static ArrayList<ArrayList<Integer>> findSubsetsThatSumToK(ArrayList<Integer> arr, int n, int k) {

        // Ans vector contains all the subset which sum upto 'K'.
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < (1 << n); i++) {
            int sum = 0;
            ArrayList<Integer> vec = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                // Checking whether the element is present the subset or not.
                if (((1 << j) & i) != 0) {
                    sum += arr.get(j);
                    vec.add(arr.get(j));
                }
            }

            // If sum is 'K'.
            if (sum == k) {
                ans.add(vec);
            }
        }

        // Return ans.
        return ans;
    }
}