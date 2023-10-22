import java.util.*;

public class Main {
 public static int[] AllIndexesRecursive(int input[],
                                int x, int start)
    {
        // If the start index reaches the
        // length of the array, then
        // return empty array
        if (start == input.length) {
            int[] ans = new int[0]; // empty array
            return ans;
        }
 
        // Getting the recursive answer in
        // smallIndex array
        int[] smallIndex = AllIndexesRecursive(input, x,
                                              start + 1);
 
        // If the element at start index is equal
        // to x then
        // (which is the answer of recursion) and then
        // (which came through recursion)
        if (input[start] == x) {
            int[] myAns = new int[smallIndex.length + 1];
 
            // Put the start index in front
            // of the array
            myAns[0] = start;
            for (int i = 0; i < smallIndex.length; i++) {
                 
                // Shift the elements of the array
                // one step to the right
                // and putting them in
                // myAns array
                myAns[i + 1] = smallIndex[i];
            }
            return myAns;
        }
        else {
             
            // If the element at start index is not
            // equal to x then just simply return the
            // answer which came from recursion.
            return smallIndex;
        }
    }
 
    public static  int[] AllIndexes(int input[], int x)
    {
 
        return AllIndexesRecursive(input, x, 0);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int[] input = new int[N];

        for (int i = 0; i < N; i++) {
            input[i] = scanner.nextInt();
        }

        int x = scanner.nextInt();
        int[] indexes = AllIndexes(input, x);

        for (int i = 0; i < indexes.length; i++) {
            System.out.print(indexes[i] + " ");
        }
    }

}