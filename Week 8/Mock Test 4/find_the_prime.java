/*
	Time Complexity: O(N)
	Space Complexity: O(N)
	
	Where N is the position of Prime number to be determined.
*/

import java.util.ArrayList;

public class Solution {
    static int maxSize = 1000005;
    static ArrayList<Boolean> isPrime = new ArrayList<>(maxSize);
    static ArrayList<Integer> prime = new ArrayList<>();
    static ArrayList<Integer> spf = new ArrayList<>(maxSize);

    public static int kthPrime(int n) {
        if (prime.size() < 10000) {

            // Iterate i from 0 to maxSize
            for (int i = 0; i < maxSize; i++) {
                isPrime.add(true);
                spf.add(2);
            }

            // Update 0 and 1 indices of isPrime as false
            isPrime.set(0, false);
            isPrime.set(1, false);

            // Iterate i from 2 to maxSize
            for (int i = 2; i < maxSize; i++) {

                // Check if isPrime[i] is true
                if (isPrime.get(i)) {
                    prime.add(i);
                    spf.set(i, i);
                }

                for (int j = 0; j < prime.size() && i * prime.get(j) < maxSize && prime.get(j) <= spf.get(i); j++) {
                    isPrime.set(i * prime.get(j), false);
                    spf.set(i * prime.get(j), prime.get(j));
                }
            }
        }

        // Return the element prime[n - 1]
        return prime.get(n - 1);
    }
}
