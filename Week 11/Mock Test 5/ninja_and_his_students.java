
/*
Time complexity: O(N * logN)
Space complexity: O(N + K)
Where 'N' is the number of students in the class
and 'K' is the number of relations between them.
*/
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.LinkedList;
import java.util.Comparator;
import java.util.Collections;
import javafx.util.Pair;

public class Solution {
    public static int[] rankOfEach(int n, int k, int[][] relations) {
        // Declaring the 'adjacencyList' , 'answer' and 'indegree' vectors.
        ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<ArrayList<Integer>>();
        int[] indegree = new int[n];
        int[] answer = new int[n];
        int[] visited = new int[n];
        ArrayList<Pair<Integer, Integer>> order = new ArrayList<Pair<Integer, Integer>>();
        for (int i = 0; i < n; i++) {
            indegree[i] = 0;
            answer[i] = 0;
            visited[i] = 0;
            adjacencyList.add(new ArrayList<Integer>());
        }
        // Looping over the 'relations' and updating the 'adjacencyList' and 'indegree'.
        for (int i = 0; i < relations.length; i++) {
            indegree[relations[i][0] - 1]++;
            adjacencyList.get(relations[i][1] - 1).add(relations[i][0] - 1);
        }
        // Initializing the 'bfs' queue.
        customComparator comp = new customComparator();
        PriorityQueue<Pair<Integer, Integer>> bfs = new PriorityQueue<>(comp);
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                bfs.add(new Pair<Integer, Integer>(-i, 1));
                visited[i] = 1;
            }
        }
        // Running a loop till current queue is not empty.
        while (bfs.size() != 0) {
            // Extracting the top element.
            Pair<Integer, Integer> f = bfs.peek();
            bfs.remove();
            int key = f.getKey() * -1;
            int value = f.getValue();
            // Pushing the current order pair.
            order.add(new Pair<Integer, Integer>(value, key));
            // Iterating over all elements of the current adjacent.
            for (int i = 0; i < adjacencyList.get(key).size(); i++) {

                // Condition when current node is not visited.
                if (visited[adjacencyList.get(key).get(i)] == 0) {
                    indegree[adjacencyList.get(key).get(i)]--;
                    if (indegree[adjacencyList.get(key).get(i)] == 0) {
                        visited[adjacencyList.get(key).get(i)] = 1;
                        bfs.add(new Pair<Integer, Integer>(-1 * adjacencyList.get(key).get(i), value + 1));
                    }
                }
            }
        }
        // Sorting out the order.
        Collections.sort(order, comp);
        // Placing elements order wise.
        for (int i = 0; i < n; i++) {
            answer[i] = order.get(n - i - 1).getValue() + 1;
        }
        return answer;
    }

    static class customComparator implements Comparator<Pair<Integer, Integer>> {
        public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
            if (o1.getKey() == o2.getKey()) {
                return -o1.getValue() + o2.getValue();
            }
            return -o1.getKey() + o2.getKey();
        }
    }
}