/*
	Time complexity: O(N ^ 2 * (N + M))
	Space complexity: O(N)
	
	Where N is the number of fruits, 
	M is the number of pairs of fruit ids.
*/

import java.util.ArrayList;

public class Solution {

    static void dfs(int u, int dest, ArrayList<ArrayList<Integer>> adj, boolean[] visited) {
        if (visited[u] == true || visited[dest] == true) {
            /*
             * No need to explore the current node if: - Current node is already visited,
             * i.e. visited[u] = true. - Or we have visited the destination once, i.e.
             * visited[dest] = true.
             */
            return;
        }

        // Current node has not be explored before. So, mark it visited.
        visited[u] = true;

        // Traverse the adjacent nodes.
        int edges = adj.get(u).size();

        for (int i = 0; i < edges; i++) {
            int v = adj.get(u).get(i);

            dfs(v, dest, adj, visited);
        }

        return;
    }

    public static int countWays(int n, ArrayList<ArrayList<Integer>> fruitIds) {
        int m = fruitIds.size();

        /*
         * Create an adjacency list considering the fruit ids as vertices, and the pairs
         * of ids as an undirected edge.
         */
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int u = fruitIds.get(i).get(0);
            int v = fruitIds.get(i).get(1);

            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        // 'ans' stored the required number of ways.
        int ans = 0;

        // Generate all the possible pairs.
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // Check if the fruits in the pair belong to different boxes or not.
                boolean[] visited = new boolean[n];

                dfs(i, j, adj, visited);

                if (visited[j] == false) {
                    // 'j' is not reachable from 'i'. So, they belong to different boxes.
                    ++ans;
                }
            }
        }

        return ans;

    }
}