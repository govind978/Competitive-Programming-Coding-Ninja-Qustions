public class Solution 
{
	public static String reverseString(String str) 
	{
		//Write your code here
		 String[] s=str.split("\\s+");

        String str1="";

        for(int i=s.length-1;i>=0;i--)

        {c

            str1=str1+s[i]+" ";

        }

        return str1.trim();
	}
}
