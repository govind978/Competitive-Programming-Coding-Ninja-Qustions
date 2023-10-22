
public class solution {

	public static int sumOfDigits(int n){
		// Write your code here
	 if (n < 10) {
            return n;
        } else {
            // Recursive case: Sum the last digit and the sum of the rest of the digits
            int lastDigit = n % 10;
            int remainingDigits = n / 10;
            return lastDigit + sumOfDigits(remainingDigits);
        }
	}
}
