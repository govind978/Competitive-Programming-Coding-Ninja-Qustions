
import java.util.Stack;
import java.util.*;

public class Solution {
    public static boolean checkOverlappingIntervals (long[] startTime, long[] endTime) {
        // Your code goes here.
        Stack<Long> stack = new Stack<>();

        // stack.push(endTime[0]);
        // Arrays.sort(startTime);
        // Arrays.sort(endTime);

        for (int i=1; i<startTime.length; i++) {
            if (startTime[i]<startTime[i-1] && endTime[i]<endTime[i-1]) {
                continue;
            }
            if (startTime[i] < endTime[i-1] || startTime[i-1] > endTime[i]) {
                return true;
            }
            // stack.push(endTime[i]);
        }

        return false;
    }
}