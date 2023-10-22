public class Solution {
	public static int maxArea(int[] height) {
	    // Write your code here.
		int l=0,r=height.length-1,ans=0;
      while(l<r){
      int a=Math.min(height[l],height[r]);
      int b=r-l;
      ans=Math.max(ans,a*b);
      if(height[l]<height[r]) l++;
      else if(height[l]>height[r]) r--;
      else {
          l++;
          r--;
        }
      }
      return ans;
	}
}