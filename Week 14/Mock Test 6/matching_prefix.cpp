/*
    Time Complexity: O( N * L )
    Space Complexity: O( N * L )

    where N and L denotes the number of strings and the maximum length of string
*/

string matchingPrefix(int n, vector<string> arr)
{

    // Variable to store the cost of input array
    int cost = 0;

    // Hash-map to store frequency of each prefix string
    unordered_map<string, int> mp;

    // Iterate array strings
    for (int i = 0; i < n; i++)
    {

        // Adding string length to cost
        cost += arr[i].size();

        // Variable to store the current prefix string
        string curPrefix;

        // Generate all prefix strings
        for (auto u : arr[i])
        {

            // Genrate new prefix string by adding the next charachter
            // to the previous prefix string
            curPrefix += u;

            // Updating frequency of each prefix string generated
            mp[curPrefix]++;
        }
    }

    // Variable to store minimum cost
    int minCost = INT_MAX;

    // Variable to store the string to be returned
    string ans;

    // Iterating the hash-map generated
    for (auto u : mp)
    {

        // Prefix length of the current prefix string
        int prefixLen = (u.first).size();

        // Frequenct of the current prefix string
        int freq = u.second;

        // Cost if the current prefix string is used
        int curCost = cost - prefixLen * freq;

        // Updating minCost if a lower cost is found
        if (curCost < minCost)
        {
            minCost = curCost;
            ans = u.first;
        }

        // Updating 'ans' if a lexicographically smaller string is found
        if (curCost == minCost)
        {
            if (u.first < ans)
            {
                ans = u.first;
            }
        }
    }

    return ans;
}