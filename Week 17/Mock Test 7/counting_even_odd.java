import java.io.*;
import java.util.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] arr = new int[100004];
    static int[] tree = new int[400004];

    static void build(int st, int l, int r) {
        if (l == r) {
            tree[st] = (arr[l] % 2);
            return;
        }
        int mid = (l + r) / 2;
        int lef = 2 * st;
        int rit = 2 * st + 1;

        build(lef, l, mid);
        build(rit, mid + 1, r);

        tree[st] = (tree[lef] + tree[rit]);
    }

    static void update(int st, int l, int r, int idx, int val) {
        if (l == r) {
            tree[st] = (val % 2);
            return;
        }

        int lef = st + st;
        int rit = lef + 1;
        int mid = (l + r) / 2;

        if (idx >= l && idx <= mid)
            update(lef, l, mid, idx, val);
        else
            update(rit, mid + 1, r, idx, val);
        tree[st] = (tree[lef] + tree[rit]);
    }

    static int query(int st, int l, int r, int x, int y) {
        if (l > y || r < x)
            return 0;
        if (l >= x && r <= y)
            return tree[st];

        int mid = (l + r) / 2;

        return (query(2 * st, l, mid, x, y) + query(2 * st + 1, mid + 1, r, x, y));
    }

    public static void main(String[] args) throws IOException {
        int n, q, a, b, qu;
        n = Integer.parseInt(br.readLine().trim());

        StringTokenizer tk = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < n; ++i) {
            arr[i] = Integer.parseInt(tk.nextToken());
        }

        build(1, 0, n - 1);
        q = Integer.parseInt(br.readLine().trim());
        while (q != 0) {
            String[] strNums1 = br.readLine().split(" ");
            qu = Integer.parseInt(strNums1[0]);
            a = Integer.parseInt(strNums1[1]);
            b = Integer.parseInt(strNums1[2]);
            if (qu == 0) {
                update(1, 0, n - 1, a - 1, b);
            } else if (qu == 1) {
                int ans = query(1, 0, n - 1, a - 1, b - 1);
                System.out.println((b - a + 1 - ans));
            } else {
                System.out.println(query(1, 0, n - 1, a - 1, b - 1));
            }
            q--;
        }
    }
}
