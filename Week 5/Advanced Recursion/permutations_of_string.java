/*
    Time Complexity: O(N * N!)
    Space complexity: O(N * N!)
    
    Where N is the length of the given string.
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;

public class Solution {
    public static String tempStr;

    public static void sort(String str) {
        char tempArray[] = str.toCharArray();
        Arrays.sort(tempArray);
        tempStr = new String(tempArray);
    }

    public static void swap(String str, int i, int j) {
        StringBuilder sb = new StringBuilder(str);
        sb.setCharAt(i, str.charAt(j));
        sb.setCharAt(j, str.charAt(i));
        tempStr = sb.toString();
    }

    public static ArrayList<String> generatePermutations(String str) {
        // Stores the permutations of the string
        ArrayList<String> ans = new ArrayList<>();

        sort(str);
        str = tempStr;

        while (true) {
            ans.add(str);

            int first = -1;
            for (int i = str.length() - 2; i >= 0; i--) {
                if (str.charAt(i) < str.charAt(i + 1)) {
                    first = i;
                    break;
                }
            }

            if (first == -1) {
                // we have found all the permutations
                break;
            }

            int second = first + 1;
            for (int i = first + 2; i < str.length(); i++) {
                if ((str.charAt(i) > str.charAt(first)) && (str.charAt(i) < str.charAt(second))) {
                    second = i;
                }
            }

            swap(str, first, second);
            str = tempStr;

            // reverse(sort) the substring after index first
            ArrayList<Character> temp = new ArrayList<Character>();
            for (int k = first + 1; k < str.length(); k++) {
                temp.add(str.charAt(k));
            }
            Collections.reverse(temp);
            String builder = "";
            for (int k = 0; k <= first; k++) {
                builder += (str.charAt(k));
            }
            for (Character c : temp) {
                builder += (c);
            }
            str = builder;
        }

        return ans;
    }
}