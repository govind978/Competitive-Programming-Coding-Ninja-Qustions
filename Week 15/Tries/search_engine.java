/*
    Time complexity: O(M * 26)
    Space complexity: O(M * 26)
    where M is the total length of input string.
*/

import java.util.ArrayList;

class Node {
    Node[] next;
    int maxSubtree;

    Node() {
        this.maxSubtree = 0;
        this.next = new Node[26];

        for (int i = 0; i < 26; i++) {
            this.next[i] = null;
        }
    }
};

public class Solution {
    static void insert(Node curr, Entry databaseEntry, int index) {
        // Setting the Priority of the current text.
        if (index == databaseEntry.str.length()) {
            curr.maxSubtree = databaseEntry.weight;
            return;
        }

        curr.maxSubtree = Math.max(curr.maxSubtree, databaseEntry.weight);

        // Getting the next character.
        int nextIndex = (databaseEntry.str.charAt(index) - 'a');

        // Creating a new node in Trie.
        if (curr.next[nextIndex] == null) {
            curr.next[nextIndex] = new Node();
        }

        insert(curr.next[nextIndex], databaseEntry, index + 1);
    }

    static int query(Node curr, String txt, int index) {
        // String not found.
        if (curr == null) {
            return -1;
        }

        // String found.
        if (index == txt.length()) {
            return curr.maxSubtree;
        }

        // The next character that we need.
        int nextIndex = (txt.charAt(index) - 'a');

        return query(curr.next[nextIndex], txt, index + 1);
    }

    static ArrayList<Integer> searchEngine(ArrayList<Entry> database, ArrayList<String> text) {
        // Creating a Trie data-structure.
        Node root = new Node();

        int n = database.size();

        // Inserting the database in the Trie.
        for (int i = 0; i < n; i++) {
            insert(root, database.get(i), 0);
        }

        ArrayList<Integer> ans = new ArrayList<>();

        // Iterating in each query.
        for (String s : text) {
            // Appending the answer in 'ans'.
            ans.add(query(root, s, 0));
        }

        return ans;
    }
}