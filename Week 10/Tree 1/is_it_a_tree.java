
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {

    public static boolean isCyclicConntected(ArrayList<ArrayList<Integer>> adj, int s, int V, boolean visited[]) {
        // Set parent vertex for every vertex as -1.
        int parent[] = new int[V];
        for (int i = 0; i < V; i++) {
            parent[i] = -1;
        }

        // Create a queue for BFS
        Queue<Integer> q = new LinkedList<>();

        // Mark the current node as visited and enqueue it
        visited[s] = true;
        q.add(s);

        while (!q.isEmpty()) {
            // Dequeue a vertex from queue
            int u = q.poll();

            // Get all adjacent vertices of the vertex u. If adjacent has not been visited,
            // enqueue it.
            for (int i = 0; i < adj.get(u).size(); i++) {
                int v = adj.get(u).get(i);
                if (!visited[v]) {
                    visited[v] = true;
                    q.add(v);

                    // mark parent so that parent is not considered for cycle.
                    parent[v] = u;
                } else if (parent[u] != v)
                    return true;
            }
        }

        return false;
    }

    public static boolean isTree(ArrayList<ArrayList<Integer>> adj, int V) {
        boolean[] visited = new boolean[V];

        // Do BFS from vertex 1
        if (isCyclicConntected(adj, 0, V, visited))
            return false;

        // check for connected component.
        for (int i = 0; i < V; i++) {
            if (visited[i] == false) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        int V = s.nextInt();
        int E = s.nextInt();

        // adjacency list
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            adj.add(list);
        }

        // add edges to list
        for (int i = 0; i < E; i++) {
            int x = s.nextInt();
            int y = s.nextInt();
            adj.get(x - 1).add(y - 1);
            adj.get(y - 1).add(x - 1);
        }

        if (isTree(adj, V))
            System.out.println("YES");
        else {
            System.out.println("NO");
        }
    }

}
