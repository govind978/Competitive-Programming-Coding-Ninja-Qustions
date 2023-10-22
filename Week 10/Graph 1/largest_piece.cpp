#include <iostream>
#include <vector>
using namespace std;

int dfs(vector<string> &cake, int x, int y)
{
    if (x < 0 || x >= cake.size() || y < 0 || y >= cake[0].size() || cake[x][y] == '0')
    {
        return 0;
    }

    cake[x][y] = '0'; // Mark the current cell as visited.

    int size = 1;

    // Explore neighboring cells in all four directions.
    size += dfs(cake, x + 1, y);
    size += dfs(cake, x - 1, y);
    size += dfs(cake, x, y + 1);
    size += dfs(cake, x, y - 1);

    return size;
}

int findLargestPiece(vector<string> &cake)
{
    int largestPieceSize = 0;

    for (int i = 0; i < cake.size(); i++)
    {
        for (int j = 0; j < cake[0].size(); j++)
        {
            if (cake[i][j] == '1')
            {
                largestPieceSize = max(largestPieceSize, dfs(cake, i, j));
            }
        }
    }

    return largestPieceSize;
}

int main()
{
    int T;
    cin >> T;

    while (T--)
    {
        int N;
        cin >> N;

        vector<string> cake(N);
        for (int i = 0; i < N; i++)
        {
            cin >> cake[i];
        }

        int result = findLargestPiece(cake);
        cout << result << endl;
    }

    return 0;
}
