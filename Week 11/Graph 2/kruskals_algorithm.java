import java.util.*;
import java.util.ArrayList;

class Edge implements Comparable<Edge> {
    int src, dest, weight;

    public int compareTo(Edge compareEdge) {
        return this.weight - compareEdge.weight;
    }
}

class KruskalMST {
    int V, E;
    Edge[] edges;

    KruskalMST(int v, int e) {
        V = v;
        E = e;
        edges = new Edge[e];
        for (int i = 0; i < e; ++i)
            edges[i] = new Edge();
    }

    int find(int[] parent, int i) {
        if (parent[i] == -1)
            return i;
        return find(parent, parent[i]);
    }

    void Union(int[] parent, int x, int y) {
        int xset = find(parent, x);
        int yset = find(parent, y);
        parent[xset] = yset;
    }

    int kruskalMST() {
        Edge[] result = new Edge[V];
        int e = 0;
        int i = 0;
        for (i = 0; i < V; ++i)
            result[i] = new Edge();

        Arrays.sort(edges);

        int[] parent = new int[V];

        Arrays.fill(parent, -1);

        i = 0;

        while (e < V - 1) {
            Edge next_edge = new Edge();
            next_edge = edges[i++];

            int x = find(parent, next_edge.src);
            int y = find(parent, next_edge.dest);

            if (x != y) {
                result[e++] = next_edge;
                Union(parent, x, y);
            }
        }

        int totalWeight = 0;
        for (i = 0; i < e; ++i)
            totalWeight += result[i].weight;
        return totalWeight;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int v = sc.nextInt();
            int e = sc.nextInt();
            KruskalMST graph = new KruskalMST(v, e);

            for (int i = 0; i < e; i++) {
                int src = sc.nextInt();
                int dest = sc.nextInt();
                int weight = sc.nextInt();
                graph.edges[i].src = src;
                graph.edges[i].dest = dest;
                graph.edges[i].weight = weight;
            }

            System.out.println(graph.kruskalMST());
        }
    }
}
