/*
    Time Complexity: O( 1 ).
    Space Complexity: O( 1 ).


*/

string winnerOfCandies(int X, int Y)
{

    // Calculate turnA and turnB
    int turnA = 2 * X - 1;
    int turnB = 2 * Y;

    // If turnA is less return Lose else return Win
    if (turnA < turnB)
    {
        return "Lose";
    }
    return "Win";
}