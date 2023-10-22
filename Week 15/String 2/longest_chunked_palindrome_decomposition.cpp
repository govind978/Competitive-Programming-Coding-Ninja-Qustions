/*
    Time Complexity: O(N^2)
    Space Complexity: O(N),

    where N is the size of the input string.
*/

int longestChunkedPal(string &s)
{

    // Declare a variable to store the answer.
    int ans = 0;
    int n = s.size();

    // Create two strings to store the substrings from left and right.
    string left = "", right = "";

    for (int i = 0; i < n; i++)
    {

        // Update both the strings.
        left = left + s[i];
        right = s[n - i - 1] + right;

        // If substring from the both end equals, then do the following.
        if (left == right)
        {
            ans++;
            left = "";
            right = "";
        }
    }

    return ans;
}