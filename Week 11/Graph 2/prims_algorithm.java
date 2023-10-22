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

    public static int primMST(ArrayList<ArrayList<Edge>> graph) {
        int V = graph.size();
        int totalWeight = 0;
        boolean[] visited = new boolean[V];

        PriorityQueue<Edge> minHeap = new PriorityQueue<>();

        minHeap.add(new Edge(0, 0));

        while (!minHeap.isEmpty()) {
            Edge current = minHeap.poll();
            int currentVertex = current.vertex;

            if (visited[currentVertex]) {
                continue;
            }

            visited[currentVertex] = true;
            totalWeight += current.weight;

            for (Edge neighbor : graph.get(currentVertex)) {
                if (!visited[neighbor.vertex]) {
                    minHeap.add(neighbor);
                }
            }
        }

        return totalWeight;
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

            int totalWeight = primMST(graph);
            System.out.println(totalWeight);
        }
    }
}
