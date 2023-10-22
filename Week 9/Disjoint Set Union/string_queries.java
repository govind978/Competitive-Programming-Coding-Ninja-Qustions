
/*
Time Complexity: O(N + Q)
Space Complexity: O(N + Q)
where N and Q are the length of S and the number of queries.
*/
import java.util.ArrayList;
import java.util.Arrays;

class DSU {
    ArrayList<Integer> id, sz;

    public int root(int i) {
        while (i != id.get(i)) {
            id.set(i, id.get(id.get(i)));
            i = id.get(i);
        }
        return i;
    }

    public int size(int p) {
        return sz.get(root(p));
    }

    public void initialize(int n) {
        id = new ArrayList<Integer>(n);
        sz = new ArrayList<Integer>(n);
        for (int i = 0; i < n; i++) {
            id.add(i);
            sz.add(1);
        }
    }

    public Boolean connected(int p, int q) {
        if (root(p) == root(q))
            return true;
        else
            return false;
    }

    void connect(int p, int q) {
        int i = root(p);
        int j = root(q);
        if (i == j)
            return;
        if (sz.get(i) < sz.get(j)) {
            id.set(i, j);
            sz.set(j, sz.get(j) + sz.get(i));
        } else {
            id.set(j, i);
            sz.set(i, sz.get(i) + sz.get(j));
        }
    }
};

public class Solution {
    public static int[] stringQueries(String s, int q, int[][] query) {
        int inf = 1000000003;
        int n = s.length();

        // Declaring record array and initializing it to infinity.
        int record[] = new int[n];
        Arrays.fill(record, inf);
        // Declaring and initializing DSU d of size N.
        DSU d = new DSU();
        d.initialize(n);
        // Making a copy T of S.
        char t[] = s.toCharArray();
        /*
         * Itereate over queries to find out and store in record the
         * index of first query where s[i] is set to '#' for the first time.
         */
        for (int i = 0; i < q; i++) {
            if (query[i][0] == 2) {
                int ind = query[i][1];
                if (record[ind] == inf) {
                    record[ind] = i;
                    t[ind] = '#';
                }
            }
        }
        // Connect all the neighboring elements having same value.
        for (int i = 0; i < n - 1; i++) {
            if (t[i] == t[i + 1] && t[i] != '#') {
                d.connect(i, i + 1);
            }
        }
        // Declare ans to store answers to queries.
        ArrayList<Integer> ans = new ArrayList<Integer>();
        // Iterate over queries in reverse order.
        for (int i = q - 1; i >= 0; i--) {
            int ind = query[i][1];
            /*
             * If it is second type query and record[ind] is equal to i
             * then it is time to change t[ind] to s[ind].
             */
            if (query[i][0] == 2 && i == record[ind]) {
                t[ind] = s.charAt(ind);
                /*
                 * If previous element is similar after changing t[ind]
                 * then merge their sets in DSU.
                 */
                if (ind > 0 && t[ind - 1] == t[ind]) {
                    d.connect(ind - 1, ind);
                }
                /*
                 * Similarly if previous element is similar after
                 * changing t[ind] then merge their sets in DSU.
                 */
                if (ind < n - 1 && t[ind] == t[ind + 1]) {
                    d.connect(ind, ind + 1);
                }
            }
            // If is is first type query then append answer to ans accordingly.
            else if (query[i][0] == 1) {

                // If t[ind] = '#' the append -1.
                if (t[ind] == '#') {
                    ans.add(-1);
                }
                // Otherwise append the size of set of ind in DSU.
                else {
                    ans.add(d.size(ind));
                }
            }
        }
        // Reverse the ans array as we answered queries in reverse order.
        int answer[] = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            answer[ans.size() - 1 - i] = ans.get(i);
        }
        // Return ans array containing answer to given queries.
        return answer;
    }
}