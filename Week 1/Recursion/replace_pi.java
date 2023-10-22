public class solution {

	// Return the changed string
	public static String replace(String s){
		// Write your code here
		if (s.length() < 2) {
            return s;
        }

        if (s.substring(0, 2).equals("pi")) {
            return "3.14" + replace(s.substring(2));
        } else {
            return s.charAt(0) + replace(s.substring(1));
        }
	}
}
