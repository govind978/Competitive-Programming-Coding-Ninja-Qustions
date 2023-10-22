
public class solution {

	public static int convertStringToInt(String s){
		// Write your code here
		if (s.isEmpty()) {
            return 0;
        } else {
            // Get the last character of the string
            char lastChar = s.charAt(s.length() - 1);

            // Convert the character to an integer and recursively convert the remaining string
            String remainingString = s.substring(0, s.length() - 1);
            int lastDigit = Character.getNumericValue(lastChar);

            return convertStringToInt(remainingString) * 10 + lastDigit;
        }
	}
}
