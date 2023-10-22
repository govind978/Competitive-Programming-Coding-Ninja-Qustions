import java.util.ArrayList;

public class Solution {

    private static int dfs(ArrayList<ArrayList<Integer>> graph, int curr, int prev, int[] dp) {
        int val = curr;

        for (int i = 0; i < graph.get(curr).size(); ++i) {
            if (graph.get(curr).get(i) != prev) {
                val ^= dfs(graph, graph.get(curr).get(i), curr, dp);
            }
        }

        dp[curr] = val;

        return val;
    }

    public static int[] XORquery(int n, int[][] edges, int q, int[] query) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; ++i) {
            graph.get(edges[i][0]).add(edges[i][1]);
        }

        int dp[] = new int[n];

        dfs(graph, 0, -1, dp);
        int res[] = new int[q];

        for (int i = 0; i < q; ++i) {
            res[i] = dp[query[i]];
        }

        return res;
    }
}