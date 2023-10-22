public class Solution {

	public static String removeConsecutiveDuplicates(String s) {
		// Write your code here
		if (s.length() <= 1) {
        return s;
    }

    char currentChar = s.charAt(0);
    String restOfString = removeConsecutiveDuplicates(s.substring(1));

    if (currentChar == restOfString.charAt(0)) {
        return restOfString;
    } else {
        return currentChar + restOfString;
    }
	}

}