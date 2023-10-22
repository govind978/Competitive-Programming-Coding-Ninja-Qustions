import java.util.ArrayList;

public class Solution {

    public static ArrayList<ArrayList<Integer>> adj;
    public static int height, maxHt;

    private static void dfs(int source, Boolean[] vis) {
        height++;
        vis[source] = true;

        // If height of the current vertex is more than maxHt, then set maxHt equal to
        // height.
        maxHt = Math.max(maxHt, height);

        // Traverse over children of the source and call DFS recursively on them.
        for (int v : adj.get(source)) {
            dfs(v, vis);
        }

        height--;
    }

    public static int maxHeight(int par[], int n) {
        // Building adjacency list for our given forest which is to be used while we do
        // DFS.
        adj = new ArrayList<ArrayList<Integer>>(n + 1);
        for (int i = 0; i <= n; i++) {
            ArrayList<Integer> temp = new ArrayList<Integer>();
            adj.add(temp);
        }

        for (int i = 1; i <= n; i++) {
            if (par[i] != -1) {
                adj.get(par[i]).add(i);
            }
        }

        Boolean vis[] = new Boolean[n + 1];
        for (int i = 0; i <= n; i++) {
            vis[i] = false;
        }
        height = 0;
        maxHt = 0;

        // Traverse over all nodes and call DFS over the root of every tree.
        for (int i = 1; i <= n; i++) {
            if (!vis[i] && par[i] == -1) {
                dfs(i, vis);
            }
        }

        // Return maxHt as it has the maximum height of the forest.
        return maxHt;
    }
}