import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {

    static final double eps = 1e-9;

    static double probabilityOfSurvival(int x0, int y0, int v, int T, int x[], int y[], int r[]) {
        double r0 = v * T;

        List<Range> ranges = new ArrayList<>();

        int n = x.length;
        for (int i = 0; i < n; i++) {
            long xi = x[i];
            long yi = y[i];
            long ri = r[i];
            double d = dist(x0, y0, xi, yi);

            if (d < ri + eps) {
                return 1.0;
            }

            if (r0 + ri < d - eps)
                continue;

            double alpha = Math.atan2(yi - y0, xi - x0);
            if (alpha < 0)
                alpha += 2 * Math.PI;
            double theta;
            double ti = Math.sqrt(d * d - ri * ri);
            if (ti < r0 + eps) {
                theta = Math.asin(ri / d);
            } else {
                theta = Math.acos((d * d + r0 * r0 - ri * ri) / (2 * d * r0));
            }

            double L = alpha - theta, R = alpha + theta;
            if (L < 0) {
                ranges.add(new Range(L + 2 * Math.PI, 2 * Math.PI));
                ranges.add(new Range(0, R));
            } else if (R > 2 * Math.PI) {
                ranges.add(new Range(L, Math.PI * 2));
                ranges.add(new Range(0, R - 2 * Math.PI));
            } else {
                ranges.add(new Range(L, R));
            }
        }

        Collections.sort(ranges);

        double ans = 0;
        int p = 0;
        Range prev = null;
        while (p < ranges.size()) {
            Range cur = ranges.get(p);
            if (prev != null && prev.to >= cur.from) {
                prev.to = Math.max(prev.to, cur.to);
            } else {
                if (prev != null)
                    ans += prev.to - prev.from;
                prev = cur;
            }
            p++;
        }
        if (prev != null)
            ans += prev.to - prev.from;

        double res = ans / (2 * Math.PI);
        return res;
    }

    static double dist(long x0, long y0, long x1, long y1) {
        return Math.sqrt((x0 - x1) * (x0 - x1) + (y0 - y1) * (y0 - y1));
    }

    static class Range implements Comparable<Range> {
        double from, to;

        public Range(double from, double to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public int compareTo(Range r) {
            if (from != r.from)
                return Double.compare(from, r.from);
            return Double.compare(to, r.to);
        }
    }
}