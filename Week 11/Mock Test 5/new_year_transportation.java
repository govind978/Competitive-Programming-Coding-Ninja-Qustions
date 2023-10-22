
/*
Time complexity: O(N)
Space complexity: O(N)
where N is the number of cells
*/
import java.io.*;
import java.util.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static void dfs(ArrayList<Integer>[] adj, int index, boolean[] visited) {
        visited[index] = true;
        for (int j : adj[index]) {
            if (!visited[j]) {
                dfs(adj, j, visited);
            }
        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        int t = Integer.parseInt(br.readLine().trim());
        while (t != 0) {
            int n = Integer.parseInt(br.readLine().trim());
            ArrayList<Integer>[] adj = new ArrayList[n + 1];
            for (int i = 0; i < n + 1; i++)
                adj[i] = new ArrayList<>();
            StringTokenizer tk = new StringTokenizer(br.readLine());
            for (int i = 1; i < n; i++) {
                int x = Integer.parseInt(tk.nextToken());
                adj[i].add(x + i);
            }
            boolean[] visited = new boolean[n + 1];
            dfs(adj, 1, visited);
            int q = Integer.parseInt(br.readLine().trim());
            while (q != 0) {
                int des = Integer.parseInt(br.readLine().trim());
                if (visited[des] == true) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
                q = q - 1;
            }
            t = t - 1;
        }
    }
}