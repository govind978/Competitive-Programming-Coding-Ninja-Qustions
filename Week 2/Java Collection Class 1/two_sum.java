import java.util.*;

import java.util.ArrayList;

public class Solution {

    static ArrayList<Pair<Integer, Integer>> twoSum(ArrayList<Integer> arr, int target, int n) {

        // Write your code here.

        

        ArrayList<Pair<Integer,Integer>> ans = new ArrayList<>();

        Map<Integer,Integer>mpp = new HashMap<>();

 

        for(int i=0; i<n; i++){

            int moreNeeded = target-arr.get(i);

            if(mpp.getOrDefault(moreNeeded,-1)!=-1){

                ans.add(new Pair(moreNeeded,arr.get(i)));

                mpp.put(arr.get(i),-1);

            }

            else{

                mpp.put(arr.get(i),i);

            }

        }

        if(ans.isEmpty()){

            ans.add(new Pair(-1,-1));

        }

        return ans;

    }

}