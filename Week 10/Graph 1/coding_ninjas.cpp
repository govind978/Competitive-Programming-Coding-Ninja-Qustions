#include <iostream>
#include <vector>
#include <string>

using namespace std;

bool dfs(vector<string> &matrix, int n, int m, int row, int col, string word, int index)
{
    if (index == word.length())
    {
        return true; // We found the entire word
    }

    if (row < 0 || col < 0 || row >= n || col >= m || matrix[row][col] != word[index])
    {
        return false; // Out of bounds or character doesn't match
    }

    char originalChar = matrix[row][col]; // Save the original character
    matrix[row][col] = '*';               // Mark the cell as visited

    // Explore adjacent cells in all eight directions
    bool found = dfs(matrix, n, m, row - 1, col, word, index + 1) ||     // Up
                 dfs(matrix, n, m, row + 1, col, word, index + 1) ||     // Down
                 dfs(matrix, n, m, row, col - 1, word, index + 1) ||     // Left
                 dfs(matrix, n, m, row, col + 1, word, index + 1) ||     // Right
                 dfs(matrix, n, m, row - 1, col - 1, word, index + 1) || // Upper left
                 dfs(matrix, n, m, row - 1, col + 1, word, index + 1) || // Upper right
                 dfs(matrix, n, m, row + 1, col - 1, word, index + 1) || // Lower left
                 dfs(matrix, n, m, row + 1, col + 1, word, index + 1);   // Lower right

    matrix[row][col] = originalChar; // Restore the original character

    return found;
}

bool findPath(vector<string> &matrix, int n, int m)
{
    string targetWord = "CODINGNINJA";

    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            if (matrix[i][j] == 'C' && dfs(matrix, n, m, i, j, targetWord, 0))
            {
                return true; // Found a path starting from 'C'
            }
        }
    }

    return false;
}

int main()
{
    int T;
    cin >> T;

    while (T--)
    {
        int n, m;
        cin >> n >> m;
        vector<string> matrix(n);

        for (int i = 0; i < n; i++)
        {
            cin >> matrix[i];
        }

        if (findPath(matrix, n, m))
        {
            cout << "1" << endl;
        }
        else
        {
            cout << "0" << endl;
        }
    }

    return 0;
}
