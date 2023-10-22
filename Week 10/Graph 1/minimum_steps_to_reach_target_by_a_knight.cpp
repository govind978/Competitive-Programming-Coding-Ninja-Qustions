/*
    Time complexity: O( abs( KX - TX ) * ( KY - TY ) )
    Space complexity: O( abs( KX - TX ) * ( KY - TY ) )

    where 'KX' denotes x-coordinate of knight, 'TX' denotes x-coordinate of the target,
    'KY' denotes y-coordinate of the knight and 'TY' denotes y-coordinate of the target.
*/

int minStepsHelper(pair<int, int> knightPosition, pair<int, int> targetPosition, vector<vector<int>> &dp)
{

    // If knight is on the target position return 0.
    if (knightPosition.first == targetPosition.first && knightPosition.second == targetPosition.second)
    {
        return 0;
    }
    else
    {

        // If already calculated then return that value.
        if (dp[abs(knightPosition.first - targetPosition.first)][abs(knightPosition.second - targetPosition.second)])
        {
            return dp[abs(knightPosition.first - targetPosition.first)][abs(knightPosition.second - targetPosition.second)];
        }
        else
        {
            int x1, y1, x2, y2;

            // (x1, y1) and (x2, y2) are the two positions which will be considered.
            if (knightPosition.first <= targetPosition.first)
            {
                if (knightPosition.second <= targetPosition.second)
                {
                    x1 = knightPosition.first + 2;
                    y1 = knightPosition.second + 1;
                    x2 = knightPosition.first + 1;
                    y2 = knightPosition.second + 2;
                }
                else
                {
                    x1 = knightPosition.first + 2;
                    y1 = knightPosition.second - 1;
                    x2 = knightPosition.first + 1;
                    y2 = knightPosition.second - 2;
                }
            }
            else
            {
                if (knightPosition.second <= targetPosition.second)
                {
                    x1 = knightPosition.first - 2;
                    y1 = knightPosition.second + 1;
                    x2 = knightPosition.first - 1;
                    y2 = knightPosition.second + 2;
                }
                else
                {
                    x1 = knightPosition.first - 2;
                    y1 = knightPosition.second - 1;
                    x2 = knightPosition.first - 1;
                    y2 = knightPosition.second - 2;
                }
            }

            dp[abs(knightPosition.first - targetPosition.first)][abs(knightPosition.second - targetPosition.second)] =
                min(minStepsHelper({x1, y1}, targetPosition, dp), minStepsHelper({x2, y2}, targetPosition, dp)) + 1;

            // Exchanging the coordinates x with y of both knight and target will result in same ans.
            dp[abs(knightPosition.second - targetPosition.second)][abs(knightPosition.first - targetPosition.first)] =
                dp[abs(knightPosition.first - targetPosition.first)][abs(knightPosition.second - targetPosition.second)];
            return dp[abs(knightPosition.first - targetPosition.first)][abs(knightPosition.second - targetPosition.second)];
        }
    }
}
int minSteps(pair<int, int> knightPosition, pair<int, int> targetPosition, int size)
{
    vector<vector<int>> dp(size + 2, vector<int>(size + 2, 0));

    // There are 4 corner cases for which the minimum steps is 4.
    if ((knightPosition.first == 1 && knightPosition.second == 1 && targetPosition.first == 2 && targetPosition.second == 2) ||
        (knightPosition.first == 2 && knightPosition.second == 2 && targetPosition.first == 1 && targetPosition.second == 1))
    {
        return 4;
    }
    else if ((knightPosition.first == 1 && knightPosition.second == size && targetPosition.first == 2 && targetPosition.second == size - 1) ||
             (knightPosition.first == 2 && knightPosition.second == size - 1 && targetPosition.first == 1 && targetPosition.second == size))
    {
        return 4;
    }
    else if ((knightPosition.first == size && knightPosition.second == 1 && targetPosition.first == size - 1 && targetPosition.second == 2) ||
             (knightPosition.first == size - 1 && knightPosition.second == 2 && targetPosition.first == size && targetPosition.second == 1))
    {
        return 4;
    }
    else if ((knightPosition.first == size && knightPosition.second == size && targetPosition.first == size - 1 && targetPosition.second == size - 1) ||
             (knightPosition.first == size - 1 && knightPosition.second == size - 1 && targetPosition.first == size && targetPosition.second == size))
    {
        return 4;
    }
    else
    {
        dp[1][0] = 3;
        dp[0][1] = 3;
        dp[1][1] = 2;
        dp[2][0] = 2;
        dp[0][2] = 2;
        dp[2][1] = 1;
        dp[1][2] = 1;
        return minStepsHelper(knightPosition, targetPosition, dp);
    }
}
