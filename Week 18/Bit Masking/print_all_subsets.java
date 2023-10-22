/*
    Time Complexity: O(N*2^N)
    Space Complexity: O(N*2^N)

    where N is the size of given input array.
*/

import java.util.ArrayList;
import java.util.Collections;

public class Solution {

    static void printAllSubsets(int n, ArrayList<Integer> arr) {

        // 2D vector to store answer
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();

        // Temporary vector to store current subset
        ArrayList<Integer> temp = new ArrayList<>();

        // Initial call to recursive function
        SubsetFinder(0, temp, ans, arr);

        // Printing the final answer
        for (int i = 0; i < ans.size(); i++) {
            // Sorting and printing each subset
            Collections.sort(ans.get(i));
            for (int j = 0; j < ans.get(i).size(); j++) {
                System.out.print(ans.get(i).get(j) + " ");
            }
            System.out.println();
        }

    }

    private static void SubsetFinder(int i, ArrayList<Integer> temp, ArrayList<ArrayList<Integer>> ans,
            ArrayList<Integer> arr) {

        // Base condition
        if (i == arr.size()) {
            // Adding non-empty current subset generated to answer
            if (temp.size() > 0) {
                ans.add(temp);
            }
            return;
        }

        // Generating subset which has ith array element in it
        ArrayList<Integer> temp1 = new ArrayList<>(temp);
        temp1.add(arr.get(i));
        SubsetFinder(i + 1, temp1, ans, arr);

        // Generating subset which doesn't has ith array element
        SubsetFinder(i + 1, temp, ans, arr);
    }

}
