/*
    Time complexity: O(N)
    Space complexity: O(1) 

    Where N represents the size of the array 'ARR'.
*/

import java.util.ArrayList;

public class Solution {
    public static int stringKhel(ArrayList<String> arr, int n) {

        int bb = 0, rb = 0, rr = 0, br = 0;
        int len = arr.get(0).length();
        for (int i = 0; i < n; i++) {

            char first = arr.get(i).charAt(0);
            char last = arr.get(i).charAt(len - 1);

            if (first == 'R' && last == 'B') {
                rb++;
            }

            if (first == 'B' && last == 'R') {
                br++;
            }

            if (first == 'R' && last == 'R') {
                rr++;
            }

            if (first == 'B' && last == 'B') {
                bb++;
            }
        }

        int ans;
        if (br == 0 && rb == 0) {
            ans = Math.max(bb, rr);
        } else {
            if (br == rb) {
                ans = rr + bb + 2 * Math.min(br, rb);
            } else {
                ans = rr + bb + 2 * Math.min(br, rb) + 1;
            }
        }

        if (ans == 1) {
            return 0;
        } else {
            return (ans * len);
        }
    }
}