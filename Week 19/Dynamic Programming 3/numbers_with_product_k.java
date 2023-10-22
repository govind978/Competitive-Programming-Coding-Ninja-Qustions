/*
    Time Complexity : O(log(R) * 10 * K)
    Space Complexity : O(K)

    Where 'K' & 'R' the number given to us.
*/

public class Solution {

    private static int maxProduct = 10000;

    private static int numsWithProductKhelper(String num, int idx, int product, int k, int leadingZeros, int tight,
            int dp[][][][]) {

        if (idx >= num.length() || product > k) {
            if (product == k) {
                return 1;
            } else {
                return 0;
            }
        }

        if (dp[product][idx][tight][leadingZeros] != -1) {
            return dp[product][idx][tight][leadingZeros];
        }

        int res = 0;

        int end;
        if (tight != 0) {
            end = num.charAt(idx) - '0';
        } else {
            end = 9;
        }

        for (int dig = 0; dig <= end; dig++) {

            if (dig == 0 && leadingZeros == 0) {
                res += numsWithProductKhelper(num, idx + 1, product, k, 0, (tight & ((dig == end) ? 1 : 0)), dp);
            } else {
                res += numsWithProductKhelper(num, idx + 1, product * dig, k, 1, (tight & ((dig == end) ? 1 : 0)), dp);
            }
        }

        dp[product][idx][tight][leadingZeros] = res;
        return res;
    }

    public static int numsWithProductK(int l, int r, int k) {
        String str = Integer.toString(r);

        int[][][][] dp = new int[maxProduct][10][2][2];

        for (int i = 0; i < maxProduct; i++) {
            for (int j = 0; j < 10; j++) {
                for (int a = 0; a < 2; a++) {
                    for (int b = 0; b < 2; b++) {
                        dp[i][j][a][b] = -1;
                    }
                }
            }
        }

        int cntR = numsWithProductKhelper(str, 0, 1, k, 0, 1, dp);

        str = Integer.toString(l - 1);

        for (int i = 0; i < maxProduct; i++) {
            for (int j = 0; j < 10; j++) {
                for (int a = 0; a < 2; a++) {
                    for (int b = 0; b < 2; b++) {
                        dp[i][j][a][b] = -1;
                    }
                }
            }
        }
        int cntL = numsWithProductKhelper(str, 0, 1, k, 0, 1, dp);

        int answer = cntR - cntL;

        return answer;
    }
}
