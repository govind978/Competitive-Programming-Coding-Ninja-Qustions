/*
    Time Complexity: O(N)
    Space Complexity: O(N)

    Where 'N' is the number nodes in the tree.
*/

import java.util.ArrayList;
import java.util.Arrays;

public class Solution {

    private static ArrayList<Integer> visited;
    private static ArrayList<ArrayList<Integer>> graph;
    private static int k;
    private static int ans;

    // Utility function to get the kth ancestor
    private static int getKthAncestor(int node, int target) {

        // If current node is target node
        if (target == node) {
            k--;
            return 1;
        }

        visited.set(node, 1);

        // Run through all adjacent of 'node'
        for (int i = 0; i < graph.get(node).size(); i++) {
            int adj = graph.get(node).get(i);

            if (visited.get(adj) == 0) {
                // If ancestor is found the backtrack
                if (getKthAncestor(adj, target) == 1) {
                    // The node 'node' is our answer because here 'k' becomes zero
                    if (k == 0) {
                        ans = node;
                    }

                    k--;

                    return 1;
                }
            }
        }

        // Return false
        return 0;
    }

    public static int kthAncestor(int n, ArrayList<Integer> parent, int node, int K) {

        graph = new ArrayList<ArrayList<Integer>>(n);
        visited = new ArrayList<Integer>(n);

        k = K;
        ans = -1;

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<Integer>());
            visited.add(0);
        }

        for (int i = 1; i < parent.size(); i++) {
            // Store parent of 'i'
            int u = parent.get(i);

            // Create edge b/w parent and child
            graph.get(u).add(i);
        }

        // Call helper function
        getKthAncestor(0, node);

        return ans;
    }
}