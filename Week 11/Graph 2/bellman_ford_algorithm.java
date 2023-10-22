import java.util.*;

public class Solution {

    public static int shortestDistance(int n, int m, int src, int des, List<List<Integer>> edges) {

        // Create an array to store the distances from the source.
        int[] distance = new int[n + 1];
        Arrays.fill(distance, 10000000);

        // Distance of source to source is 0.
        distance[src] = 0;

        // Apply Bellman-Ford algorithm.
        for (int i = 1; i < n; i++) {
            for (List<Integer> edge : edges) {
                int u = edge.get(0);
                int v = edge.get(1);
                int w = edge.get(2);

                if (distance[u] != 10000000 && distance[v] > (distance[u] + w)) {
                    distance[v] = distance[u] + w;
                }
            }
        }

        return distance[des];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int src = sc.nextInt();
            int des = sc.nextInt();

            List<List<Integer>> edges = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                List<Integer> edge = new ArrayList<>();
                edge.add(sc.nextInt());
                edge.add(sc.nextInt());
                edge.add(sc.nextInt());
                edges.add(edge);
            }

            int result = shortestDistance(n, m, src, des, edges);
            System.out.println((result == 10000000 ? "1000000000" : result));
        }
    }
}
