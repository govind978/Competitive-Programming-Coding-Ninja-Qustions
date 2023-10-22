/*
    Time Complexity: O(N+M)
    Space Complexity: O(1),

    Where N is the size of the string A and M is the size of the string B.
*/

#include <string>

bool isOneAway(string a, string b)
{

    int lenA = a.size(), lenB = b.size();

    // Initialize a variable to store the number of mismatched characters.
    int mismatch = 0;

    // Initialize one pointer for each string.
    int ptrA = 0, ptrB = 0;

    while (ptrA < lenA and ptrB < lenB)
    {

        if (a[ptrA] == b[ptrB])
        {
            ptrA++;
            ptrB++;
        }

        else
        {

            // If the characters do not match, increment the count.
            mismatch++;

            // If the mismatched characters become more than one, return False.
            if (mismatch > 1)
            {
                return false;
            }

            // If the lengths of both the strings are equal, increment both pointers by 1.
            if (lenA == lenB)
            {
                ptrA++;
                ptrB++;
            }

            // If the length of string a is more than string b, increment pointer of string a.
            else if (lenA > lenB)
            {
                ptrA++;
            }

            // If the length of string a is less than string b, increment pointer of string b.
            else if (lenA < lenB)
            {
                ptrB++;
            }
        }
    }

    // If there is any extra character at the end of any of the two strings, increment the count.
    if (ptrA < lenA or ptrB < lenB)
    {
        mismatch++;
    }

    // If the number of mismatched characters in less than or equal to one, return True.
    if (mismatch <= 1)
    {
        return 1;
    }

    else
    {
        return 0;
    }
}