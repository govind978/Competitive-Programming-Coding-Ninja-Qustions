import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;

public class Solution {
    static Map<Integer, ArrayList<Integer>> tree;
    static ArrayList<Integer> path;
    static int maxHeight, maxHeightNode;

    static boolean getDiameterPath(int vertex, int targetVertex, int parent, ArrayList<Integer> path) {
        if (vertex == targetVertex) {
            path.add(vertex);
            return true;
        }

        for (Integer i : tree.get(vertex)) {
            if (i == parent)
                continue;

            if (getDiameterPath(i, targetVertex, vertex, path)) {
                path.add(vertex);
                return true;
            }
        }
        return false;
    }

    static void farthestNode(int vertex, int parent, int height) {
        if (height > maxHeight) {
            maxHeight = height;
            maxHeightNode = vertex;
        }

        if (tree.get(vertex) != null)
            for (Integer i : tree.get(vertex)) {
                if (i == parent)
                    continue;

                farthestNode(i, vertex, height + 1);
            }
    }

    static void addedge(int a, int b) {
        if (tree.get(a) == null)
            tree.put(a, new ArrayList<>());

        tree.get(a).add(b);

        if (tree.get(b) == null)
            tree.put(b, new ArrayList<>());

        tree.get(b).add(a);
    }

    public static ArrayList<Integer> Center(int n, int[][] edges) {
        tree = new HashMap<>();
        for (int i = 0; i < n - 1; i++) {
            addedge(edges[i][0], edges[i][1]);
        }

        maxHeight = -1;
        maxHeightNode = -1;
        farthestNode(0, -1, 0);

        int leaf1 = maxHeightNode;

        maxHeight = -1;
        farthestNode(leaf1, -1, 0);

        int leaf2 = maxHeightNode;

        path = new ArrayList<>();
        getDiameterPath(leaf1, leaf2, -1, path);

        ArrayList<Integer> result = new ArrayList<>();
        int pathSize = path.size();

        if (pathSize % 2 == 1) {
            result.add(path.get(pathSize / 2));
        } else {
            result.add(path.get(pathSize / 2));
            result.add(path.get((pathSize - 1) / 2));
        }
        Collections.reverse(result);
        return result;
    }
}
