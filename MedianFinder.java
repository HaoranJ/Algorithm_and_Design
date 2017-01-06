import java.util.*;

public class MedianFinder {
  PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
  PriorityQueue<Integer> minHeap = new PriorityQueue<>();

  public void addNum(int num) {
    if (maxHeap.isEmpty()) {
      maxHeap.add(num);
      return;
    }
    int top1 = maxHeap.peek();
    int sz1 = maxHeap.size();
    int sz2 = minHeap.size();
    if (num > top1) {
      minHeap.add(num);
      if (sz1 == sz2) {
        maxHeap.add(minHeap.poll());
      }
    } else {
      if (sz1 == sz2 + 1) {
        minHeap.add(maxHeap.poll());
      }
      maxHeap.add(num);
    }
  }

  public double findMedian() {
    int sz1 = maxHeap.size();
    int sz2 = minHeap.size();
    if (sz1 == sz2) {
      return ((double)maxHeap.peek() + (double)minHeap.peek()) / 2.0;
    } else {
      return (double)maxHeap.peek();
    }
  }
}
