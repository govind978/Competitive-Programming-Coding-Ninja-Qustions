
/************************************************************

   Following is the TreeNode class structure

   class TreeNode {
        public int data;
        public ArrayList<TreeNode> children;

        TreeNode(int data) {
            this.data = data;
            children = new ArrayList<TreeNode>();
        }
   }

 ************************************************************/
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Solution {

    public static ArrayList<Integer> zigzagLevelOrder(int n, TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        Stack<Integer> levelValues = new Stack<>();
        boolean leftToRight = true; // Flag to indicate the direction.

        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();
                if (!leftToRight) {
                    levelValues.push(current.data);
                } else {
                    result.add(current.data);
                }

                // Enqueue child nodes according to the direction.
                for (TreeNode child : current.children) {
                    queue.offer(child);
                }
            }

            // Add values from the stack if moving from right to left.
            if (!leftToRight) {
                while (!levelValues.isEmpty()) {
                    result.add(levelValues.pop());
                }
            }

            leftToRight = !leftToRight; // Toggle the direction for the next level.
        }

        return result;
    }
}
