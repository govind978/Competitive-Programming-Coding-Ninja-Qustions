
public class Solution {

    public static int xorSum(int[] arr1, int[] arr2) {
        // Write your code here
        int xorResult = 0;
        for (int num1 : arr1) {
            for (int num2 : arr2) {
                xorResult ^= (num1 & num2);
            }
        }
        return xorResult;
    }

}
