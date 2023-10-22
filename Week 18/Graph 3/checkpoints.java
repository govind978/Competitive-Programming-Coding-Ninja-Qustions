import java.util.ArrayList;
import java.util.Collections;

public class Solution {

    static ArrayList<Integer> order, component;

    static void dfs1(int v, ArrayList<ArrayList<Integer>> adj, boolean[] used) {
        used[v] = true;

        for (int u : adj.get(v)) {
            if (!used[u]) {
                dfs1(u, adj, used);
            }
        }
        order.add(v);
    }

    static void dfs2(int v, ArrayList<ArrayList<Integer>> adj_rev, boolean[] used) {
        used[v] = true;
        component.add(v);

        for (int u : adj_rev.get(v)) {
            if (!used[u]) {
                dfs2(u, adj_rev, used);
            }
        }
    }

    static int[] checkpoints(int n, int m, int c[], int edges[][]) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        ArrayList<ArrayList<Integer>> adj_rev = new ArrayList<>();
        boolean used[] = new boolean[n];
        order = new ArrayList<>();
        component = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
            adj_rev.add(new ArrayList<>());
        }

        int mod = 1000 * 1000 * 1000 + 7;
        for (int i = 0; i < m; i++) {
            int a = edges[i][0] - 1;
            int b = edges[i][1] - 1;
            adj.get(a).add(b);
            adj_rev.get(b).add(a);
        }
        for (int i = 0; i < n; i++) {
            if (!used[i]) {
                dfs1(i, adj, used);
            }
        }

        for (int i = 0; i < n; i++) {
            used[i] = false;
        }

        Collections.reverse(order);

        int ans = 0, ways = 1;
        for (int v : order) {
            if (!used[v]) {
                dfs2(v, adj_rev, used);
                int mn = Integer.MAX_VALUE, cnt = 0;
                for (int x : component) {
                    if (c[x] < mn) {
                        mn = c[x];
                        cnt = 1;
                    } else if (c[x] == mn) {
                        cnt++;
                    }
                }
                ans += mn;
                ways = (ways * cnt) % mod;
                component = new ArrayList<>();
            }
        }
        return new int[] { ans, ways };
    }
}
