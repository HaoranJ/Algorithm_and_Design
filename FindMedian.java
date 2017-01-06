import java.util.*;
import java.io.*;
while	
/*
Given a stream of stock prices for a particular company, find the average within the last 5 minutes.
*/
//time = O(n), space = O(n)
public class FindMedian {
	public static double findMedianFromDynamicInput(Integer... nums) {
		if (nums.length == 0) {
			throw new IllegalArgumentException();
		}
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();

		for (int x : nums) {
			if (maxHeap.size() == minHeap.size()) {
				if (minHeap.peek() == null || x <= minHeap.peek()) {
					maxHeap.add(x);
				} else {
					minHeap.add(x);
				}
			} else if (maxHeap.size() == minHeap.size()+1) {
				if (x >= maxHeap.peek()) {
					minHeap.add(x);
				} else {
					minHeap.add(maxHeap.poll());
					maxHeap.add(x);
				}
			} else {
				if (x <= minHeap.peek()) {
					maxHeap.add(x);
				} else {
					maxHeap.add(minHeap.poll());
					minHeap.add(x);
				}
			}
		}
		assert Math.abs(maxHeap.size() - minHeap.size()) <= 1;
		if (maxHeap.size() == minHeap.size()) {
			return ((double)maxHeap.peek() + (double)minHeap.peek())/2;
		} else if (maxHeap.size() > minHeap.size()) {
			return maxHeap.peek();
		} else {
			return minHeap.peek();
		}
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		ArrayList<Integer> input = new ArrayList<>();
		while (sc.hasNext()) {
			input.add(sc.nextInt());
		}
		double median = findMedianFromDynamicInput(input.toArray(new Integer[input.size()]));
		System.out.println(median);
	}
}
