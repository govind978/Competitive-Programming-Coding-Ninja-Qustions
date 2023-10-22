/*
    Time Complexity : O(N*M + X)
    Space Complexity : O(max(N,M) + X)

    Where N and M are the number of rows and columns.
    X is the length of the given word.
*/

// Function to compute the LPS array for the given word.
int computeLPS(string word, int *lps)
{

    int m = word.length();
    lps[0] = 0;

    for (int i = 1; i < m; i++)
    {
        int j = lps[i - 1];
        while (j > 0 && word[i] != word[j])
        {
            j = lps[j - 1];
        }

        if (word[i] == word[j])
        {
            j++;
        }

        lps[i] = j;
    }
}

int KMP(string s, string word, int *lps)
{
    int n = s.length();
    int m = word.length();
    word.push_back('#');
    /*
        Why do we add this hash to the end of the word? If you remember, we define lps as the longest 'proper' prefix of the array.
        Proper prefix excludes the empty string and the whole string itself.
        As the character '#' will never occur in the string s, it ensures that the longest such prefix that we obtain is always 'proper'.
    */

    int occurrences = 0;

    for (int i = 0, j = 0; i < n; i++)
    {
        while (j > 0 && s[i] != word[j])
        {
            j = lps[j - 1];
        }

        if (s[i] == word[j])
        {
            j++;
        }

        if (j == m)
        {

            // We have reached the end of the given word successfully, which means that we have found an occurrence.
            occurrences++;
        }
    }

    return occurrences;
}

int wordSearch(string grid[], int n, int m, string word)
{

    /*
        LPS stands for 'Longest (Proper) Prefix which is also a Suffix'.
        lps[i] : Longest (proper) prefix of string word[0:i] (0-based) which is also a suffix.
        lps[0] = 0.
    */
    int *lps = new int[word.length()];
    computeLPS(word, lps);

    int occurrences = 0;

    // Rows.
    for (int i = 0; i < n; i++)
    {
        occurrences += KMP(grid[i], word, lps);
        occurrences += KMP(string(grid[i].rbegin(), grid[i].rend()), word, lps);
    }

    // Columns.
    for (int j = 0; j < m; j++)
    {
        string str;
        for (int i = 0; i < n; i++)
        {
            str.push_back(grid[i][j]);
        }

        occurrences += KMP(str, word, lps);
        occurrences += KMP(string(str.rbegin(), str.rend()), word, lps);
    }

    // Upper Diagonals.
    for (int i = 0; i < n; i++)
    {
        string str;
        for (int j = 0; i + j < n && j < m; j++)
        {
            str.push_back(grid[i + j][j]);
        }

        occurrences += KMP(str, word, lps);
        occurrences += KMP(string(str.rbegin(), str.rend()), word, lps);
    }

    for (int j = 1; j < m; j++)
    {
        string str;
        for (int i = 0; i < n && j + i < m; i++)
        {
            str.push_back(grid[i][j + i]);
        }

        occurrences += KMP(str, word, lps);
        occurrences += KMP(string(str.rbegin(), str.rend()), word, lps);
    }

    // Lower Diagonals.
    for (int i = 0; i < n; i++)
    {
        string str;
        for (int j = 0; i - j >= 0 && j < m; j++)
        {
            str.push_back(grid[i - j][j]);
        }

        occurrences += KMP(str, word, lps);
        occurrences += KMP(string(str.rbegin(), str.rend()), word, lps);
    }

    for (int j = 1; j < m; j++)
    {
        string str;
        for (int i = n - 1; i >= 0 && j + (n - 1) - i < m; i--)
        {
            str.push_back(grid[i][j + (n - 1) - i]);
        }

        occurrences += KMP(str, word, lps);
        occurrences += KMP(string(str.rbegin(), str.rend()), word, lps);
    }

    return occurrences;
}