/*
    Time Complexity : O(N + P)
    Space Complexity : O(P)

    where N is the number of words in 'word1' and P is the number of elements in the array 'pairs'
*/

#include <unordered_map>

class DisjointSet
{ // Disjoint-set (or Union-find) data structure
    int count;
    vector<int> parent, rank;

public:
    DisjointSet(int n)
    {
        count = n;
        for (int i = 0; i < n; i++)
        { // Create 'n' new sets
            parent.push_back(i);
            rank.push_back(0);
        }
    }

    int findSet(int v)
    { // Returns the representative of the set containing the element 'v'
        if (parent[v] == v)
            return v;
        return parent[v] = findSet(parent[v]);
    }

    void unionSets(int a, int b)
    { // Merge the sets containing element 'a' and 'b'
        int pa = findSet(a);
        int pb = findSet(b);
        if (pa != pb)
        { // If 'a' and 'b' belong to different sets
            if (rank[pa] < rank[pb])
                swap(pa, pb);

            parent[pb] = pa;
            if (rank[pb] == rank[pa])
                rank[pa]++;
            count--; // Merging two different sets will decrease the number of sets by 1
        }
    }

    int getCount()
    { // Returns the number of sets
        return count;
    }
};

bool identicalSentences(vector<string> word1, vector<string> word2, vector<vector<string>> pairs, int n, int m, int p)
{
    if (n != m)
    { // Sentences cannot be identical if they don't contain same number of words
        return false;
    }

    DisjointSet ds(2 * p + 1);

    unordered_map<string, int> words;
    int id = 1;

    for (int i = 0; i < p; i++)
    {
        if (!words[pairs[i][0]])
            words[pairs[i][0]] = id++;
        if (!words[pairs[i][1]])
            words[pairs[i][1]] = id++;

        int u = words[pairs[i][0]], v = words[pairs[i][1]];
        ds.unionSets(u, v); // As 'u' and 'v' are connected, merge their sets
    }

    for (int i = 0; i < n; i++)
    {
        string u = word1[i], v = word2[i];
        if (u == v)
        { // Strings are identical
            continue;
        }

        // If ('u' or 'v' is not present in 'words') or (they don't belong to the same set),
        // then they are not identical
        if (!words[u] || !words[v] || ds.findSet(words[u]) != ds.findSet(words[v]))
        {
            return false;
        }
    }
    return true;
}