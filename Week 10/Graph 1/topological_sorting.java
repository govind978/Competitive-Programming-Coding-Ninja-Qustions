import java.util.*;

public class Solution {

    public static List<Integer> topologicalSort(int[][] edges, int e, int v) {
        int[] inDegree = new int[v];
        List<Integer>[] graph = new ArrayList[v];

        for (int i = 0; i < v; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            graph[from].add(to);
            inDegree[to]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < v; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        int count = 0;
        List<Integer> topologicalOrder = new ArrayList<>();

        while (!queue.isEmpty()) {
            int node = queue.poll();
            topologicalOrder.add(node);

            for (int neighbor : graph[node]) {
                if (--inDegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }

            count++;
        }

        if (count != v) {
            return new ArrayList<>();
        } else {
            return topologicalOrder;
        }
    }
}
