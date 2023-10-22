/*
    Time Complexity : O(N * M)
    Space Complexity : O(26 * N)

    Where 'N' is the number of strings in the 'dictionary'.
    Where 'M' is the maximum length of the string present in the 'dictionary'.
*/

import java.util.ArrayList;

// Trie Node.
class Trie {
    Trie[] child = new Trie[26];
    boolean isEnd;

    Trie() {
        for (int i = 0; i < 26; i++) {
            child[i] = null;
        }
        isEnd = false;
    }
}

public class Solution {
    // Function to insert string, 'STR' in the 'Trie'.
    public static void insert(Trie root, String str) {
        int i = 0;

        while (i < str.length()) {
            // Index of current character in 'Trie'.
            int index = str.charAt(i) - 'a';

            // Current character is not present.
            if (root.child[index] == null) {
                // Create a new node for the current character.
                root.child[index] = new Trie();
            }

            root = root.child[index];
            i++;
        }
        // Mark the end of the string.
        root.isEnd = true;
    }

    // Function to find suggested correct spellings.
    public static void findSuggestions(Trie root, String possibleAnswer, ArrayList<String> res) {
        // String 'possibleAnswer' is present as a complete string.
        if (root.isEnd == true) {
            res.add(possibleAnswer);
            return;
        }

        for (int i = 0; i < 26; i++) {
            // Character is present in the 'Trie'.
            if (root.child[i] != null) {
                // Append the character to the 'possibleAnswer'.
                possibleAnswer += (char) (i + 'a');

                findSuggestions(root.child[i], possibleAnswer, res);

                // Pop the last appended character.
                possibleAnswer = possibleAnswer.substring(0, possibleAnswer.length() - 1);
            }
        }
    }

    public static ArrayList<String> spellChecker(ArrayList<String> dictionary, String query) {
        // Creating root node of 'Trie'.
        Trie root = new Trie();

        for (int i = 0; i < dictionary.size(); i++) {
            insert(root, dictionary.get(i));
        }

        ArrayList<String> result = new ArrayList<>();

        for (int i = 0; i < query.length(); i++) {
            // Index of current character in 'Trie'.
            int index = query.charAt(i) - 'a';

            // Current character is not present in the 'Trie'.
            if (root.child[index] == null) {
                // The substring of string 'query' before the current character is the longest
                // prefix to be searched in 'dictionary'.
                String prefixQuery = query.substring(0, i);
                findSuggestions(root, prefixQuery, result);

                return result;
            }
            root = root.child[index];
        }

        // The string 'query' is present in the 'Trie'.
        if (root.isEnd == true) {
            return result;
        }

        // Find suggested correct spellings for 'query' with prefix as whole string.
        findSuggestions(root, query, result);
        return result;
    }
}