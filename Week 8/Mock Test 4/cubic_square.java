import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        /*
         * Your class should be named Solution.
         * Read input as specified in the question.
         * Print output as specified in the question.
         */

        // Write your code here
        Scanner sc = new Scanner(System.in);
        int test = sc.nextInt();
        while (test-- > 0) {
            int a = sc.nextInt();
            String b = sc.next();
            int m = sc.nextInt();
            System.out.println(cubicSquare(m, a, b));
        }
    }

    public static int cubicSquare(int m, int a, String b) {
        long ans = 1;

        // Converting base and mod to long long to avoid overflow.
        long base = a, mod = m;

        int n = b.length();

        // Traverse 'b' from 'n'-1 to 0 as first we need to go to the least significant
        // bit.
        for (int i = n - 1; i >= 0; i--) {
            // If current bit is equal to 2, that means 2 times 'a' needs to be multiplied
            // to the answer.
            if (b.charAt(i) == '2') {
                ans = (((ans * base) % mod) * base) % mod;
            }

            // If the current bit is '1', that means 1 times 'a' needs to be multiplied to
            // the answer.
            else if (b.charAt(i) == '1') {
                ans = (ans * base) % mod;
            }

            // Every increase in bit position is significant of rise by 3 in a's power.
            base = (((base * base) % mod) * base) % mod;
        }

        // Return the final answer.
        return (int) ans;
    }
}
