import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int N = sc.nextInt();
            int M = sc.nextInt();
            int[][] dist = new int[N + 1][N + 1];
            int INF = (int) 1e9;

            for (int i = 1; i <= N; i++) {
                Arrays.fill(dist[i], INF);
                dist[i][i] = 0;
            }

            for (int i = 0; i < M; i++) {
                int a = sc.nextInt();
                int b = sc.nextInt();
                int c = sc.nextInt();
                dist[a][b] = Math.min(dist[a][b], c);
                dist[b][a] = Math.min(dist[b][a], c);
            }

            for (int k = 1; k <= N; k++) {
                for (int i = 1; i <= N; i++) {
                    for (int j = 1; j <= N; j++) {
                        if (dist[i][k] < INF && dist[k][j] < INF) {
                            dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                        }
                    }
                }
            }

            int Q = sc.nextInt();
            for (int q = 0; q < Q; q++) {
                int a = sc.nextInt();
                int b = sc.nextInt();
                int shortestDistance = dist[a][b];
                if (shortestDistance == INF) {
                    System.out.println((int) Math.pow(10, 9));
                } else {
                    System.out.println(shortestDistance);
                }
            }
        }
    }
}