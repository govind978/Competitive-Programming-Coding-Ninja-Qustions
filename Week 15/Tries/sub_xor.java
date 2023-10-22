import java.util.*;

class TrieNode {
    int left_count, right_count;
    TrieNode left;
    TrieNode right;

    TrieNode() {
        left_count = 0;
        right_count = 0;
        // Left denotes 0
        left = null;
        // Right denotes 1
        right = null;
    }
}

class Solution {
    static void insert(TrieNode root, int element) {
        for (int i = 31; i >= 0; i--) {
            int x = (element >> i) & 1;
            // If the current bit is 1
            if (x == 1) {
                root.right_count++;
                if (root.right == null)
                    root.right = new TrieNode();
                root = root.right;
            } else {
                root.left_count++;
                if (root.left == null)
                    root.left = new TrieNode();
                root = root.left;
            }
        }
    }

    static int query(TrieNode root, int element, int k) {
        if (root == null)
            return 0;
        int res = 0;
        for (int i = 31; i >= 0; i--) {
            int current_bit_of_k = (k >> i) & 1;
            int current_bit_of_element = (element >> i) & 1;
            // If the current bit of k is 1
            if (current_bit_of_k == 1) {
                // If current bit of element is 1
                if (current_bit_of_element == 1) {
                    res += root.right_count;
                    if (root.left == null)
                        return res;
                    root = root.left;
                }
                // If current bit of element is 0
                else {
                    res += root.left_count;
                    if (root.right == null)
                        return res;
                    root = root.right;
                }
            }
            // If the current bit of k is zero
            else {
                // If current bit of element is 1
                if (current_bit_of_element == 1) {
                    if (root.right == null)
                        return res;
                    root = root.right;
                }
                // If current bit of element is 0
                else {
                    if (root.left == null)
                        return res;
                    root = root.left;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        /*
         * Your class should be named Solution.
         * Read input as specified in the question.
         * Print output as specified in the question.
         * Write your code here
         */
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int K = scanner.nextInt();
        int[] A = new int[N];

        for (int i = 0; i < N; i++) {
            A[i] = scanner.nextInt();
        }
        int temp, temp1, temp2 = 0;
        TrieNode root = new TrieNode();
        insert(root, 0);
        long total = 0;
        for (int i = 0; i < N; i++) {
            temp = A[i];
            temp1 = temp2 ^ temp;
            total += query(root, temp1, K);
            insert(root, temp1);
            temp2 = temp1;
        }

        System.out.println(total);
    }
}
