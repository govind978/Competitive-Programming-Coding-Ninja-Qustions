import java.util.*;

public class Solution {
    public static int elementThatAppearsOnce(int[] a) {
        // Write your code here
        int n = a.length;
        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int i = 0; i < n; i++) {
            hm.put(a[i], hm.getOrDefault(a[i], 0) + 1);
        }

        for (int val : hm.keySet()) {
            if (hm.get(val) == 1) {
                return val;
            }
        }

        return -1;
    }
}