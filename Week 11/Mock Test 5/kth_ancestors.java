
/*
Time Complexity: O(N)
Space Complexity: O(N)
where N is the number of members in the family tree.
*/
import java.util.ArrayList;

public class Solution {
    private static void dfs(int cur, int parent, int k, ArrayList<ArrayList<Integer>> tree,
            ArrayList<Integer> ancestors, int[] ans) {
        if (ancestors.size() < k) {
            // K'th Ancestor of CUR does not exist.
            ans[cur] = -1;
        } else {
            // Set the kth value from the end as ans[cur].
            ans[cur] = ancestors.get(ancestors.size() - k);
        }
        ancestors.add(cur);
        // Recursive dfs for all child nodes of 'CUR'.
        for (int i : tree.get(cur)) {
            if (i == parent) {
                continue;
            } else {
                dfs(i, cur, k, tree, ancestors, ans);
            }
        }
        ancestors.remove(ancestors.size() - 1);
    }

    public static int[] kthAncestor(int n, int k, int[][] edges) {
        ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            tree.add(new ArrayList<>());
        }
        // Building Adjacency List for the Tree.
        for (int[] edge : edges) {
            tree.get(edge[0]).add(edge[1]);
            tree.get(edge[1]).add(edge[0]);
        }
        // ans array to store the kth ancestors of each node.
        int ans[] = new int[n];
        // ancestors array to store the ancestors of current node.
        ArrayList<Integer> ancestors = new ArrayList<>();

        dfs(0, -1, k, tree, ancestors, ans);
        return ans;
    }
}