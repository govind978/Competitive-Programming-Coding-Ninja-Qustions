import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    static Point[] points = new Point[100001];
    static Point pivot;

    static int orientation(Point a, Point b, Point c) {
        long o = (c.x - b.x) * (b.y - a.y) - (c.y - b.y) * (b.x - a.x);
        // 0 = collinear
        // -1 = anti-clockwise
        // 1 = clockwise
        if (o == 0)
            return 0;
        else if (o > 0)
            return 1;
        else
            return -1;
    }

    public static void main(String[] args) {
        /*
         * Your class should be named Solution.
         * Read input as specified in the question.
         * Print output as specified in the question.
         * Write your code here
         */
        int t, n, x, y;
        // File file = new File("input.txt");
        // Scanner scanner = new Scanner(file);
        Scanner scanner = new Scanner(System.in);
        t = scanner.nextInt();
        for (int i = 1; i <= 100000; i++)
            points[i] = new Point(0, 0);
        while (t-- > 0) {
            n = scanner.nextInt();
            for (int i = 1; i <= n; i++) {
                x = scanner.nextInt();
                y = scanner.nextInt();
                points[i].x = x;
                points[i].y = y;
                if (i == 1)
                    pivot = new Point(x, y);
                else if (points[i].x < pivot.x)
                    pivot = points[i];
                else if (points[i].x == pivot.x && points[i].y < pivot.y)
                    pivot = points[i];
            }
            Arrays.sort(points, 1, n + 1);
            // taking care of collinear points
            int i = n - 1;
            while (i > 1 && orientation(pivot, points[i], points[n]) == 0)
                i--;
            int L = i + 1;
            int R = n;

            while (L < R) {
                Point temp = points[L];
                points[L] = points[R];
                points[R] = temp;
                L++;
                R--;
            }
            List<Point> hull = new ArrayList<>();
            for (i = 1; i <= n; i++) {
                while (hull.size() > 1 && orientation(hull.get(hull.size() - 2),
                        hull.get(hull.size() - 1), points[i]) >= 0) {
                    hull.remove(hull.size() - 1);
                }
                hull.add(points[i]);
            }
            // for(Point p : hull) writer.write("x : " + p.x + " y = " + p.y + "\n");
            for (Point p : hull)
                System.out.print(p.x + " " + p.y + "\n");
        }
    }
}

class Point implements Comparable<Point> {
    long x;
    long y;

    Point(long x, long y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Point o) {
        int val = Solution.orientation(Solution.pivot, this, o);
        if (val == 0) {
            long d1 = distance(this, Solution.pivot);
            long d2 = distance(o, Solution.pivot);
            if (d1 < d2)
                return -1;
            else
                return 1;
        } else if (val == 1) {
            return 1;
        } else {
            return -1;
        }
    }

    long distance(Point a, Point b) {
        long dx = a.x - b.x;
        long dy = a.y - b.y;
        return dx * dx + dy * dy;
    }
}