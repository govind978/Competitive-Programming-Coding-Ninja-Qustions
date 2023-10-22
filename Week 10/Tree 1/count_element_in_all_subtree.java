/*
    Time Complexity - O(N)
    Space Complexity - O(N)

	where 'N' is the number of nodes in the tree
*/

import java.util.ArrayList;

public class Solution {
    private static void countNodesDFS(ArrayList<ArrayList<Integer>> adj, ArrayList<Integer> arr, int src, int parent) {

        arr.set(src, 1);
        for (int it : adj.get(src)) {
            if (it == parent) {
                continue;
            }
            countNodesDFS(adj, arr, it, src);
            arr.set(src, arr.get(it) + arr.get(src));
        }

    }

    public static ArrayList<Integer> countNodesInAllSubtrees(int n, ArrayList<ArrayList<Integer>> adj) {

        ArrayList<Integer> arr = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            arr.add(0);
        }
        countNodesDFS(adj, arr, 0, -1);
        return arr;
    }
}