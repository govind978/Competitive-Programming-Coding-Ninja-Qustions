/*
    Time Complexity : O(N)
    Space Complexity : O(N)

    where 'N' is the number of nodes in the tree.
*/

public class Solution {

    public static int findMaxSum(int n, TreeNode root) {

        // If 'ROOT' is null, return 0.
        if (root == null) {

            return 0;
        }

        // Initialize a single element array 'MAXSUM' to store maximum path sum.
        int maxSum[] = { 0 };

        DFS(root, root.data, maxSum);

        // Return 'MAXSUM'.
        return maxSum[0];
    }

    public static void DFS(TreeNode node, int pathSum, int[] maxSum) {

        // If the cuurent node is a leaf node.
        if (node.children.size() == 0) {

            // Update 'MAXSUM'.
            maxSum[0] = Math.max(maxSum[0], pathSum);
            return;
        }

        for (TreeNode child : node.children) {

            // Call DFS() on the current node's children by incrementing the 'PATHSUM'.
            DFS(child, pathSum + child.data, maxSum);
        }
    }
}