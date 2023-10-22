import java.util.*;

public class Solution {
    public static Boolean isCyclic(int[][] edges, int v, int e) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>(v);

        for (int i = 0; i < v; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
        }

        boolean[] visited = new boolean[v];
        boolean[] recursionStack = new boolean[v];

        for (int i = 0; i < v; i++) {
            if (isCyclicUtil(i, visited, recursionStack, graph)) {
                return true;
            }
        }

        return false;
    }

    private static boolean isCyclicUtil(int v, boolean[] visited, boolean[] recursionStack,
            ArrayList<ArrayList<Integer>> graph) {
        if (!visited[v]) {
            visited[v] = true;
            recursionStack[v] = true;

            for (Integer neighbor : graph.get(v)) {
                if (!visited[neighbor] && isCyclicUtil(neighbor, visited, recursionStack, graph)) {
                    return true;
                } else if (recursionStack[neighbor]) {
                    return true;
                }
            }
        }

        recursionStack[v] = false;
        return false;
    }

}
