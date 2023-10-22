import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

class Edge implements Comparable<Edge> {
    int vertex, weight;

    public Edge(int vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }

    public int compareTo(Edge other) {
        return this.weight - other.weight;
    }
}

public class Main {

    public static void dijkstra(ArrayList<ArrayList<Edge>> graph, int V, int source) {
        int[] dist = new int[V];
        boolean[] visited = new boolean[V];

        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        dist[source] = 0;
        PriorityQueue<Edge> minHeap = new PriorityQueue<>();
        minHeap.add(new Edge(source, 0));

        while (!minHeap.isEmpty()) {
            Edge current = minHeap.poll();
            int currentVertex = current.vertex;

            if (visited[currentVertex]) {
                continue;
            }

            visited[currentVertex] = true;

            for (Edge neighbor : graph.get(currentVertex)) {
                int newDist = dist[currentVertex] + neighbor.weight;

                if (newDist < dist[neighbor.vertex]) {
                    dist[neighbor.vertex] = newDist;
                    minHeap.add(new Edge(neighbor.vertex, newDist));
                }
            }
        }

        for (int i = 0; i < V; i++) {
            System.out.println(i + " " + dist[i]);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int V = sc.nextInt();
            int E = sc.nextInt();

            ArrayList<ArrayList<Edge>> graph = new ArrayList<>();

            for (int i = 0; i < V; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < E; i++) {
                int vertex1 = sc.nextInt();
                int vertex2 = sc.nextInt();
                int weight = sc.nextInt();
                graph.get(vertex1).add(new Edge(vertex2, weight));
                graph.get(vertex2).add(new Edge(vertex1, weight));
            }

            dijkstra(graph, V, 0);
        }
    }
}
