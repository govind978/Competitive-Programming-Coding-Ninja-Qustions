/*
    Time Complexity : O(N)
    Space Complexity : O(1)

    Where 'N' is the length of string 'Str'
*/

string findLastSubstring(string &str)
{
    // Length of given string.
    int n = str.length();

    int i = 0, j = 1, k = 0;

    while (j + k < n)
    {
        if (str[i + k] == str[j + k])
        {
            // If str[i...k] equal to str[j...k].
            k++;
        }
        else if (str[i + k] > str[j + k])
        {
            // If str[i...k] is lexicographically greater than str[j...k].
            j = j + k + 1;
            k = 0;
        }
        else
        {
            // If str[i...k] is lexicographically smaller than str[j...k].
            i = max(i + k + 1, j);
            j = i + 1;
            k = 0;
        }
    }

    return str.substr(i);
}