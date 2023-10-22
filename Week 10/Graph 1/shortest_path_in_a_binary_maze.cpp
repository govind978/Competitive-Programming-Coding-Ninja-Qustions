/*
    Time complexity: O(M * N)
    Space complexity: O(M * N)

    Where M is number of rows and N is number of columns in the matrix
*/

#include <queue>

// Function to find the shortest path between a given source cell to a destination cell
int shortestPathBinaryMatrix(vector<vector<int>> &matrix, pair<int, int> src, pair<int, int> dest)
{
    int n = matrix.size();
    int m = matrix[0].size();

    // Check source cell of the matrix have value 1
    if (!matrix[src.first][src.second])
    {
        return -1;
    }

    int ans = 0;

    vector<vector<bool>> visited(n, vector<bool>(m, 0));

    // Mark the source cell as visited
    visited[src.first][src.second] = 1;

    // Create a queue for BFS
    queue<pair<int, int>> q;
    q.push(src);

    // Array details of all 4 possible movements from a cell
    int dx[] = {-1, 0, 0, 1};
    int dy[] = {0, -1, 1, 0};

    // Do a BFS starting from source cell
    while (q.size())
    {
        int sz = q.size();

        // Traverse the current level
        for (int i = 0; i < sz; i++)
        {
            pair<int, int> node = q.front();
            q.pop();

            // If we have reached the destination cell, return current distance
            if (node.first == dest.first && node.second == dest.second)
            {
                return ans;
            }

            for (int j = 0; j < 4; j++)
            {
                int newX = dx[j] + node.first;
                int newY = dy[j] + node.second;

                // If adjacent cell is valid, has path and not visited yet, enqueue it
                if (newX >= 0 && newY >= 0 && newX < n && newY < m && !visited[newX][newY] && matrix[newX][newY])
                {
                    visited[newX][newY] = 1;
                    q.emplace(newX, newY);
                }
            }
        }
        // Increment distance by 1, as destination cell is not present in current level
        ans++;
    }
    // Return -1, if destination cannot be reached
    return -1;
}