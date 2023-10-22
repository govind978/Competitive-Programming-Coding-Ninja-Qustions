import java.util.*;

public class Solution {

	public static ArrayList<Integer> kLargest(int n, int[] arr, int k) {
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int i = 0; i < k; i++) {
            minHeap.offer(arr[i]);
        }

        for (int i = k; i < arr.length; i++) {
            if (arr[i] > minHeap.peek()) {
                minHeap.poll();
                minHeap.offer(arr[i]);
            }
        }

        ArrayList<Integer> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            result.add(minHeap.poll());
        }

        return result;
    }
}