/*
    Time complexity: O(N)
    Space complexity: O(1)

    Where 'N' is the size of the string.
*/

char repeatedCharacter(string &str)
{
    // Create a boolean array to store weather the character is visited or not.
    bool visited[26];

    // Traverse through the boolean array and mark all as not visited.
    for (int i = 0; i < 26; i++)
    {
        // 'false' means the character has not been visited.
        visited[i] = false;
    }

    // Initialise index to store the index of the first repeating character.
    int index = -1;

    // Traverse from right and update the index as soon as we see a visited character.
    for (int i = str.size() - 1; i >= 0; i--)
    {
        /*
           If you come through a character for the first time, mark it as visited.
           So that once you visit it again it will be already visited and you can store its index.
        */
        if (visited[str[i] - 'a'] == false)
        {
            visited[str[i] - 'a'] = true;
        }
        else
        {
            index = i;
        }
    }
    // If index found before reaching the end of the string, then return the character at that index.
    if (index >= 0)
    {
        return str[index];
    }
    // Else return '%' as asked in the problem statement.
    else
    {
        return '%';
    }
}