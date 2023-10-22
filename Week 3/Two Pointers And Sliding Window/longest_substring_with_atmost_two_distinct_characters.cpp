#include <bits/stdc++.h>
int lengthOfLongestSubstring(string s)
{
    unordered_map<char, int> counts; // Frequencies of chars in the window
    int res = 0;
    int numDistinct = 0;
    int i = 0; // Left pointer
    for (int j = 0; j < s.length(); j++)
    {
        if (counts[s[j]] == 0)
            numDistinct++;
        counts[s[j]]++; // Add right pointer to window
        while (numDistinct > 2)
        {
            counts[s[i]]--;
            if (counts[s[i]] == 0)
                numDistinct--;
            i++;
        } // Now the condition is valid
        res = max(res, j - i + 1);
    }
    return res;
}