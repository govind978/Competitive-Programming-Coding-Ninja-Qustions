import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int[] x = new int[3];
            int[] y = new int[3];

            for (int i = 0; i < 3; i++) {
                String[] point = br.readLine().split(" ");
                x[i] = Integer.parseInt(point[0]);
                y[i] = Integer.parseInt(point[1]);
            }

            if (areCollinear(x, y)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    static boolean areCollinear(int[] x, int[] y) {
        // Check if the slopes between points (x0, y0) and (x1, y1) and between
        // (x0, y0) and (x2, y2) are equal
        return (y[1] - y[0]) * (x[2] - x[0]) == (y[2] - y[0]) * (x[1] - x[0]);
    }
}