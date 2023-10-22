public class Solution {
    public static boolean isPalindrome(String s) {
        // Write your code here.

        s = s.replaceAll("\\s", "").toLowerCase();
        return isPalindromeRecursive(s, 0, s.length() - 1);
        
    }
    private static boolean isPalindromeRecursive(String s, int start, int end) {
        // Base case: If the start index crosses the end index, it's a palindrome
        if (start >= end) {
            return true;
        }
        // Compare the characters at start and end positions
        if (s.charAt(start) != s.charAt(end)) {
            return false;
        }
        // Recursively check the substring with one character removed from each end
        return isPalindromeRecursive(s, start + 1, end - 1);
    }

}
