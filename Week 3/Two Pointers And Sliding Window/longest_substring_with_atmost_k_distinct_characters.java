import java.util.*;
public class Solution {
	public static int getLengthofLongestSubstring(int k, String str) {
		// Write your code here.
		 int l = 0;

        int r = 0;

        int ans = 0;

        HashMap<Character, Integer> map = new HashMap<>();

        int n = str.length();

        while (r < n) {

            char ch = str.charAt(r);

            map.put(ch, map.getOrDefault(ch, 0) + 1);

            while (map.size() > k) {

                ch = str.charAt(l);

                if (map.get(ch) == 1) {

                    map.remove(ch);

                }else{

                    map.put(ch, map.get(ch)-1);

                }

                l++;

            }

            ans = Math.max(ans, r - l + 1);

            r++;

        }

        return ans;
	}
}
