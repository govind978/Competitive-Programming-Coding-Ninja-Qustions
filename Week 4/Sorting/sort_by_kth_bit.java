/*

    Time Complexity : O(N)
    Space Complexity : O(N)

    Where N is the number of elements in the array.
   

*/

public class Solution {
    public static int[] sortArrayByKBit(int n, int k, int arr[]) {

        int j = 0;
        int p = 0;

        int temparr[] = new int[n];

        // Loop to through arr.
        for (int i = 0; i < n; i++) {
            // If Kth bit of current element is 0.
            if ((arr[i] & (1 << (k - 1))) == 0) {
                arr[p++] = arr[i];
            }
            // If Kth bit of current element is 1.
            else {
                temparr[j++] = arr[i];
            }
        }

        j = 0;
        // Copy elements from temparr back to arr.
        for (int i = p; i < n; i++) {
            arr[i] = temparr[j++];
        }
        // Return modified array.
        return arr;
    }
}
