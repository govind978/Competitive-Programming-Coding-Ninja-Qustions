
/*
Time complexity: O(N)
Space complexity: O(N)
where N is the number of vertex
*/
import java.util.*;
import java.io.*;

class Point {
    double x, y;

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    double cross(Point p) {
        return x * p.y - y * p.x;
    }
}

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static double polygonArea(ArrayList<Point> v) {
        double a = v.get(v.size() - 1).cross(v.get(0));
        for (int i = 0; i < (int) (v.size()) - 1; i++) {
            a += v.get(i).cross(v.get(i + 1));
        }
        return a / 2;
    }

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine().trim());
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine().trim());
            ArrayList<Point> v = new ArrayList<>(n);
            for (int j = 0; j < n; j++) {
                double a, b;
                StringTokenizer tk = new StringTokenizer(br.readLine().trim());
                a = Double.parseDouble(tk.nextToken());
                b = Double.parseDouble(tk.nextToken());
                Point temp = new Point(a, b);
                v.add(temp);
            }
            int ans = (int) polygonArea(v);
            System.out.println(ans);
        }
    }
}