public class Solution {

	public static String removeConsecutiveDuplicates(String s) {
		// Write your code here
		if (s.length() <= 1) {
            return s;
        }

        // Check if the first character is the same as the next character
        if (s.charAt(0) == s.charAt(1)) {
            // Skip the current character and recursively call the function on the rest of the string
            return removeConsecutiveDuplicates(s.substring(1));
        } else {
            // Keep the current character and recursively call the function on the rest of the string
            return s.charAt(0) + removeConsecutiveDuplicates(s.substring(1));
        }
	}

}