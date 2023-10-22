/*
    Time complexity: O(1)
    Space complexity: O(1)

    We are using constant time and space.
*/

string gameOfStones(int num)
{

    // Condition when 'Bob' can win.
    if (num == 3)
    {
        return "Bob";
    }
    else
    {
        return "Ale";
    }
}