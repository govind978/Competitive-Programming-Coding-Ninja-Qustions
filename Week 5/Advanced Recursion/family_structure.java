/*
    Time Complexity: O(min(N, log(K)))
    Space Complexity: O(N)

    Where N is the generation number and K is the position of the child in the Nth generation. 
*/

public class Solution {

    public static String kthChildNthGeneration(int n, long k) {

        // Base Condition
        if (n == 1 || k == 1) {
            return "Male";
        }

        // Parent of the Kth child of Nth generation
        long par = (k + 1) / 2;

        // Recursively find the gender of the parent
        String parGender = kthChildNthGeneration(n - 1, par);

        // If Kth child of Nth generation is the first child of its parent
        if (k == 2 * par - 1) {
            return parGender;
        }

        // If Kth child of Nth generation is the second child of its parent
        else {
            if (parGender == "Male") {
                return "Female";
            } else {
                return "Male";
            }
        }
    }
}