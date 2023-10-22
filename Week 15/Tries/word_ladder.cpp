/*
    Time Complexity: O(N * |S| * 26)
    Space complexity: O(N)

    Where 'N' denotes the length of the DICT and |S| is the length of each string.
*/

#include <bits/stdc++.h>

int wordLadder(string &begin, string &end, vector<string> &dict)
{

    int depth = 1;
    unordered_set<string> set(dict.begin(), dict.end());

    // If the end word is not present then just return form here.
    if (set.count(end) == 0)
        return -1;

    unordered_set<string> beginSet, endSet;
    int len = beginSet.size();
    unordered_set<string> visited;
    beginSet.insert(begin);
    endSet.insert(end);

    // Start the BSF from both sides.
    while (!beginSet.empty() && !endSet.empty())
    {
        if (beginSet.size() > endSet.size())
        {
            unordered_set<string> temp = beginSet;
            beginSet = endSet;
            endSet = temp;
        }
        unordered_set<string> temp;

        // Check for each word in the begin set.
        for (string word : beginSet)
        {
            // For finding the adjacent words.
            for (int i = 0; i < word.size(); i++)
            {
                /*
                    Make a new word by adding chararcters and
                    check if it is present in dict.
                */
                for (char c = 'a'; c <= 'z'; c++)
                {
                    char old = word[i];
                    word[i] = c;
                    if (endSet.count(word) != 0)
                    {
                        return depth + 1;
                    }
                    if (visited.count(word) == 0 && set.count(word) != 0)
                    {
                        temp.insert(word);
                        visited.insert(word);
                    }
                    word[i] = old;
                }
            }
        }
        beginSet = temp;
        depth++;
    }
    return -1;
}