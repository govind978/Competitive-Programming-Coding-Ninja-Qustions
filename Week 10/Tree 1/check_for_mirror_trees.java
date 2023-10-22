
/*
	Time Complexity: O(N)
	Space Complexity: O(N)
	
	where N is the number of nodes in the tree.
*/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Solution {

    private static void fillStack(int cur, ArrayList<ArrayList<Integer>> treeA, Stack<Integer> s) {

        s.push(cur);

        // Recursive call for all child nodes.
        for (int i : treeA.get(cur)) {
            fillStack(i, treeA, s);
        }

    }

    private static void fillQueue(int cur, ArrayList<ArrayList<Integer>> treeB, Queue<Integer> q) {

        for (int i : treeB.get(cur)) {
            fillQueue(i, treeB, q);
        }

        q.add(cur);
    }

    public static boolean checkMirror(int n, int[][] edgesA, int[][] edgesB) {

        ArrayList<ArrayList<Integer>> treeA = new ArrayList<>();
        ArrayList<ArrayList<Integer>> treeB = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            treeA.add(new ArrayList<>());
            treeB.add(new ArrayList<>());
        }

        // Building Adjacency List for the TreeA and TreeB.
        for (int[] edge : edgesA) {

            treeA.get(edge[0]).add(edge[1]);
        }

        for (int[] edge : edgesB) {

            treeB.get(edge[0]).add(edge[1]);
        }

        // s and q are the stack and the queue.
        Stack<Integer> s = new Stack<>();
        Queue<Integer> q = new LinkedList<>();

        fillStack(0, treeA, s);
        fillQueue(0, treeB, q);

        // Stack 'S' and queue 'Q' are filled, now comparing the values.

        while (s.size() > 0) {
            int a = q.peek();
            q.poll();
            int b = s.peek();
            s.pop();
            if (a != b) {
                return false;
            }
        }

        return true;
    }

}
