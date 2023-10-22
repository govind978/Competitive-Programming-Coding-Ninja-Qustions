
/*
Time complexity: O(N*log(N))
Space complexity: O(1) (no extra space other than input)
where N is the Number of elements
*/
import java.io.*;
import java.util.*;

class Item {
    double value, weight;

    Item(double val, double wt) {
        this.value = val;
        this.weight = wt;
    }
}

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static double fKnapsnack(ArrayList<Item> arr, double W, int n) {
        Collections.sort(arr, new Comparator<Item>() {
            public int compare(Item a, Item b) {
                return (a.value / a.weight > b.value / b.weight) ? -1 : 1;
            }
        });
        double curW = 0;
        double fValue = 0;
        for (int i = 0; i < n; i++) {
            if (curW + arr.get(i).weight <= W) {
                curW += arr.get(i).weight;
                fValue += arr.get(i).value;
            } else {
                fValue += (arr.get(i).value * ((W - curW) / arr.get(i).weight));
                break;
            }
        }
        return fValue;
    }

    public static void main(String[] args) throws IOException {
        int n;
        double W;
        String[] strNums = br.readLine().split(" ");
        n = Integer.parseInt(strNums[0]);
        W = Double.parseDouble(strNums[1]);
        ArrayList<Item> arr = new ArrayList<Item>(n);
        for (int i = 0; i < n; i++)

        {
            Item x = new Item(0, 0);
            arr.add(x);
        }
        for (int i = 0; i < n; i++) {
            double val, wt;
            String[] strNums1 = br.readLine().split(" ");
            val = Double.parseDouble(strNums1[0]);
            wt = Double.parseDouble(strNums1[1]);
            arr.get(i).value = val;
            arr.get(i).weight = wt;
        }
        System.out.println(String.format("%.6f", fKnapsnack(arr, W, n)));
    }
}