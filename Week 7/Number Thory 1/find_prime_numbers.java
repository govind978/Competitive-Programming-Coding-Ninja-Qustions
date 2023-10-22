
/*
Time Complexity: O(N * log(log(N)))
Space Complexity: O(N)
Where N is the given integer.
 */
import java.util.ArrayList;

public class Solution {

    public static ArrayList<Integer> primeNumbersTillN(int N) {
        ArrayList<Integer> primeNumbers = new ArrayList<>();
        // Boolean array to store true at index i if i is prime
        boolean isPrime[] = new boolean[N + 1];
        for (int i = 0; i < isPrime.length; i++) {
            isPrime[i] = true;
        }

        isPrime[0] = false;
        isPrime[1] = false;

        // Type casting int i to long long to prevent overflow
        for (int num = 2; (long) num * num <= N; num++) {
            if (!isPrime[num]) {
                continue;
            }

            // If num is prime, we set multiples of num in is_prime false
            for (int i = num * num; i <= N; i += num) {
                isPrime[i] = false;
            }

        }

        for (int i = 2; i <= N; i++) {
            if (isPrime[i]) {
                primeNumbers.add(i);
            }
        }

        return primeNumbers;
    }
}
