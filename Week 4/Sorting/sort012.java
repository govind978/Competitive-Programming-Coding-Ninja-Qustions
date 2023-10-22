/*
    Time complexity : O(N)
    Space complexity : O(1)

    Where N is the size of input array
*/

public class Solution {

    public static void sort012(int[] arr) {
        int nextZero = 0;
        int nextTwo = arr.length - 1;
        int i = 0;

        while (i <= nextTwo) {

            // If current element is 0, place it at the 'nextZero' pointer.
            if (arr[i] == 0) {

                int temp = arr[nextZero];
                arr[nextZero] = arr[i];
                arr[i] = temp;
                i++;
                nextZero++;

            }
            // Else if element = 2, place it at the 'nextTwo' pointer.
            else if (arr[i] == 2) {

                int temp = arr[nextTwo];
                arr[nextTwo] = arr[i];
                arr[i] = temp;
                nextTwo--;

            }
            // Else, if element = 1, let it remain in mid position.
            else {
                i++;
            }

        }
    }
}