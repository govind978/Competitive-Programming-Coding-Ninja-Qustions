/*
Time Complexity: O( N )
Space Complexity: O( 1 )
where N denotes the length of the input string
*/
public class Solution {
    public static int oddPartitions(String s) {
        // Variable to store the length of the input string.
        int n = s.length();
        // Variable to store the current index.
        int idx = n - 1;
        // Check if the last digit of the input is even.
        if ((s.charAt(idx) - '0') % 2 == 0) {
            return -1;
        }
        // Variable to store the minimum odd partitions required.
        int ans = 0;
        // While loop
        while (true) {
            // Partition possible
            if (idx <= 8) {
                ans++;
                return ans;
            }
            // Variable to check if all the previous nine digits are even.
            int flg = 0;
            // For loop to check the previous nine digits.
            for (int i = idx - 9; i <= idx - 1; i++) {
                // Rightmost odd digit amongst the previous nine digits found.
                if ((s.charAt(i) - '0') % 2 != 0) {
                    ans++;
                    flg = 1;
                    idx = i;
                    break;
                }
            }
            // Return -1 if all the previous nine digits are even
            if (flg == 0) {
                return -1;
            }
        }
    }
}