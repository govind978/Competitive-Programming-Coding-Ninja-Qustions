/*
    Time Complexity : O(1) 
    Space Complexity : O(1)
*/

import java.util.ArrayList;
import java.lang.Math;

public class Solution {
    public static Boolean find24(ArrayList<Double> vec) {

        // When only one element is left then check if it is 24.
        if (vec.size() == 1) {
            if (Math.abs(vec.get(0) - 24.0) <= 0.0000001) {
                return true;
            } else {
                return false;
            }
        }

        // '()' have no significance as we are checking all combinations.
        ArrayList<Character> operations = new ArrayList<Character>();
        operations.add('+');
        operations.add('-');
        operations.add('*');
        operations.add('/');

        for (int i = 0; i < vec.size(); ++i) {
            // Each time we pick up two number for computation.
            for (int j = 0; j < vec.size(); ++j) {
                if (i == j) {
                    continue;
                }

                ArrayList<Double> res = new ArrayList<Double>();
                for (int k = 0; k < vec.size(); ++k) {
                    if (k != i && k != j) {
                        res.add(vec.get(k));
                    }
                }

                for (char op : operations) {
                    if ((op == '+' || op == '*') && i > j) {
                        // No need to re-calculate.
                        continue;
                    }
                    if (op == '/' && vec.get(j) == 0.0) {
                        continue;
                    }

                    switch (op) {
                        case '+':
                            res.add(vec.get(i) + vec.get(j));
                            break;
                        case '-':
                            res.add(vec.get(i) - vec.get(j));
                            break;
                        case '*':
                            res.add(vec.get(i) * vec.get(j));
                            break;
                        case '/':
                            res.add(vec.get(i) / vec.get(j));
                            break;
                    }

                    if (find24(res)) {
                        return true;
                    }

                    // Result of these operation will be used further, So, pop every time.
                    res.remove(res.size() - 1);
                }
            }
        }
        return false;
    }

    public static Boolean judge(int[] nums) {
        ArrayList<Double> vec = new ArrayList<Double>();
        for (int i = 0; i < nums.length; i++) {
            // To change nums in double.
            vec.add(nums[i] * 1.0);
        }

        return find24(vec);
    }
}