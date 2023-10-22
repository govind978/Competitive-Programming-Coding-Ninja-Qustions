/*
    Time Complexity : O(M*K)
    Space Complexity : O(N*K)

    Where N is the number of cities, M is the number of roads and K is the number of days Mr. X will stay at Ninjaland
*/

#define INT_MAX 1000000

vector<int> findOptimalOrder(vector<string> &arr, vector<vector<int>> &roads, string &s)
{
    int n = arr.size();
    int m = roads.size();
    int k = (s.size()) / 3;

    // Declaring the array to store DP states
    vector<vector<int>> minDifference(n, vector<int>(k, INT_MAX));
    vector<vector<int>> optimalOrder(n, vector<int>(k, -1));

    // Assigning values to the first column of the matrix
    for (int i = 0; i < n; i++)
    {
        minDifference[i][0] = 0;
        for (int l = 0; l < 3; l++)
        {
            minDifference[i][0] += (arr[i][l] != s[l]);
        }
    }

    // Iterating for each day
    for (int i = 1; i < k; i++)
    {
        // Iterating through all the roads
        for (vector<int> road : roads)
        {
            int firstCity = road[0], secondCity = road[1];
            int firstCost = 0, secondCost = 0;

            // Finding the number of places current city differs from the lucky string
            for (int l = 0; l < 3; l++)
            {
                firstCost += (arr[firstCity][l] != s[3 * i + l]);
                secondCost += (arr[secondCity][l] != s[3 * i + l]);
            }

            // Checking if we are able to found a smaller answer and updating values accordingly
            if (minDifference[firstCity][i] > minDifference[secondCity][i - 1] + firstCost)
            {
                minDifference[firstCity][i] = minDifference[secondCity][i - 1] + firstCost;
                optimalOrder[firstCity][i] = secondCity;
            }
            if (minDifference[secondCity][i] > minDifference[firstCity][i - 1] + secondCost)
            {
                minDifference[secondCity][i] = minDifference[firstCity][i - 1] + secondCost;
                optimalOrder[secondCity][i] = firstCity;
            }
        }
    }

    int minTotalCost = INT_MAX;
    int start = -1;

    // Finding the optimal city in which Mr. X will end his journey
    for (int i = 0; i < n; i++)
    {
        if (minTotalCost > minDifference[i][k - 1])
        {
            start = i;
            minTotalCost = minDifference[i][k - 1];
        }
    }

    vector<int> ans(k);
    int dayCount = k - 1;

    // Generating the optimal order of cities by traversing backwards
    while (dayCount >= 0)
    {
        ans[dayCount] = start;
        start = optimalOrder[start][dayCount];
        dayCount--;
    }

    return ans;
}