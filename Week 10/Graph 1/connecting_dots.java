import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt(); // Number of rows
        int M = scanner.nextInt(); // Number of columns
        scanner.nextLine(); // Consume the newline character

        char[][] board = new char[N][M];

        for (int i = 0; i < N; i++) {
            String row = scanner.nextLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = row.charAt(j);
            }
        }

        int result = hasCycle(N, M, board);
        System.out.println(result);

        scanner.close();
    }

    public static int hasCycle(int N, int M, char[][] board) {
        int[][] visited = new int[N][M];

        // Define possible movement directions (right, left, up, down)
        int[] dx = { 0, 0, 1, -1 };
        int[] dy = { 1, -1, 0, 0 };

        // Iterate through each cell in the board
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j] == 0) {
                    char color = board[i][j];
                    int cycleLength = dfs(board, visited, i, j, -1, -1, color, 0);
                    if (cycleLength >= 4) {
                        return 1;
                    }
                }
            }
        }

        return 0;
    }

    public static int dfs(char[][] board, int[][] visited, int x, int y, int prevX, int prevY, char targetColor,
            int length) {
        visited[x][y] = 1;
        int N = board.length;
        int M = board[0].length;

        // Define possible movement directions (right, left, up, down)
        int[] dx = { 0, 0, 1, -1 };
        int[] dy = { 1, -1, 0, 0 };

        for (int dir = 0; dir < 4; dir++) {
            int newX = x + dx[dir];
            int newY = y + dy[dir];

            // Check if the new cell is within bounds
            if (newX >= 0 && newX < N && newY >= 0 && newY < M) {
                // Check if it is the previous cell or the color matches
                if ((newX != prevX || newY != prevY) && board[newX][newY] == targetColor) {
                    if (visited[newX][newY] == 1) {
                        return length;
                    }
                    int cycleLength = dfs(board, visited, newX, newY, x, y, targetColor, length + 1);
                    if (cycleLength >= 4) {
                        return cycleLength;
                    }
                }
            }
        }

        visited[x][y] = 2;
        return length;
    }
}
