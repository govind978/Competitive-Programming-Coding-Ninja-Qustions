/*
    Time Complexity: O(N!)
    Space Complexity: O(N)

    Where 'N' is the given input.
*/

import java.util.ArrayList;

public class Solution {
    public static int[] findValidSequence(int[] answer, int[] remaining, int index) {
        if (index == answer.length) {
            return answer;
        }

        // If current position is already fixed try creating from the next position.
        if (answer[index] != 0) {
            return findValidSequence(answer, remaining, index + 1);
        }

        // Iterate from biggest element to smallest.
        for (int i = remaining.length - 1; i > 0; i--) {
            if (remaining[i] == 0) {
                continue;
            }

            if (i != 1 && index + i < answer.length && answer[index + i] == 0) {
                /*
                 * Mark 'ANSWER[INDEX]' and 'ANSWER[INDEX + i]' as 'i' and mark the current
                 * element as used and then try to create the remaining answer.
                 */
                answer[index] = i;
                answer[index + i] = i;
                remaining[i] = 0;

                int[] tempAnswer = findValidSequence(answer, remaining, index + 1);

                if (tempAnswer.length != 0) {
                    return tempAnswer;
                }

                // If the sequence could not be created then mark the current element as unused.
                remaining[i] = 1;
                answer[index] = 0;
                answer[index + i] = 0;
            }

            if (i == 1) {
                /*
                 * Mark 'ANSWER[INDEX]' as 'i' and mark the current element as used and
                 * then try to create the remaining answer.
                 */
                answer[index] = i;
                remaining[i] = 0;

                int[] tempAnswer = findValidSequence(answer, remaining, index + 1);

                if (tempAnswer.length != 0) {
                    return tempAnswer;
                }

                // If the sequence could not be created then mark the current element as unused.
                remaining[i] = 1;
                answer[index] = 0;
            }

        }

        // Return an empty vector.
        return (new int[0]);
    }

    public static ArrayList<Integer> validSequence(int n) {
        int[] answer = new int[2 * n - 1];
        int[] remaining = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            remaining[i] = 1;
        }

        int[] temp = findValidSequence(answer, remaining, 0);

        ArrayList<Integer> res = new ArrayList<Integer>();
        for (int i = 0; i < temp.length; i++) {
            res.add(temp[i]);
        }
        return res;
    }
}