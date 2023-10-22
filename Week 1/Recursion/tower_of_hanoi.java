import java.util.ArrayList;

public class Solution 
{
        public static ArrayList<ArrayList<Integer>> towerOfHanoi(int n, int sourceRod, int auxiliaryRod, int destinationRod) {
        ArrayList<ArrayList<Integer>> moves = new ArrayList<>();

        if (n == 1) {
            ArrayList<Integer> move = new ArrayList<>();
            move.add(sourceRod);
            move.add(destinationRod);
            moves.add(move);
        } else {
            moves.addAll(towerOfHanoi(n - 1, sourceRod, destinationRod, auxiliaryRod));
            ArrayList<Integer> move = new ArrayList<>();
            move.add(sourceRod);
            move.add(destinationRod);
            moves.add(move);
            moves.addAll(towerOfHanoi(n - 1, auxiliaryRod, sourceRod, destinationRod));
        }

        return moves;

    }
    public static ArrayList < ArrayList<Integer>> towerOfHanoi(int n) 
    {
        //    Write your code here.
        return towerOfHanoi(n, 1,2,3);
    }
}