import java.util.*;
//LeetCode 264
public class UglyNumber {
<<<<<<< 80c257f2999f994806b99e0950c89320b1fb05fb
	//time = O(n), space = O(n)
	//Never forget duplicates, then ugly == more than one of the candidates, related idx should move forward.
    public int nthUglyNumber(int n) {
        assert n > 0;
        int[] nums = new int[n];
        nums[0] = 1;
        int idx2 = 0;
        int idx3 = 0;
        int idx5 = 0;
        PriorityQueue<Integer> minPQ = new PriorityQueue<>();
        for (int j = 1; j < n; j++) {
            minPQ.add(nums[idx2]*2);
            minPQ.add(nums[idx3]*3);
            minPQ.add(nums[idx5]*5);
            int ugly = minPQ.poll();
            if (ugly == nums[idx2]*2) {
              idx2++;
            }
            if (ugly == nums[idx3]*3) {
            	idx3++;
            }
            if (ugly == nums[idx5]*5) {
            	idx5++;
            }
            nums[j] = ugly;
            minPQ.clear();
        }
        return nums[n-1];
    }
}
=======
  //time = O(n), space = O(n)
  //Never forget duplicates, then ugly == more than one of the candidates, related idx should move forward.
  public int nthUglyNumber(int n) {
    long[] uglys = new long[n];
    uglys[0] = 1;
    int idx2 = 0, idx3 = 0, idx5 = 0;
    PriorityQueue<Long> minHeap = new PriorityQueue<>();
    for(int i = 1; i < n; i++) {
      long n2 = uglys[idx2] * 2, n3 = uglys[idx3] * 3, n5 = uglys[idx5] * 5;
      minHeap.offer(n2);
      minHeap.offer(n3);
      minHeap.offer(n5);
      long min = minHeap.poll();
      if(min == n2) { idx2++; }
      if(min == n3) { idx3++; }
      if(min == n5) { idx5++; }
      uglys[i] = min;
      minHeap.clear();
    }
    return (int)uglys[n-1];
  }

  public int nthUglyNumber(int n) {
    if(n == 1) { return 1; }
    PriorityQueue<Long> h2 = new PriorityQueue<>();
    PriorityQueue<Long> h3 = new PriorityQueue<>();
    PriorityQueue<Long> h5 = new PriorityQueue<>();
    h2.offer((long)2);
    h3.offer((long)3);
    h5.offer((long)5);
    for(int i = 2; i < n; i++) {
      PriorityQueue<Long> heap = findMin(h2, h3, h5);
      long top = heap.poll();
      if(!isExist(top*2, h2, h3, h5)) { heap.offer(top * 2); }
      if(!isExist(top*3, h2, h3, h5)) { heap.offer(top * 3); }
      if(!isExist(top*5, h2, h3, h5)) { heap.offer(top * 5); }
    }
    return (int)(long)findMin(h2, h3, h5).poll();
  }

  private boolean isExist(long num, PriorityQueue<Long> h2, PriorityQueue<Long> h3, PriorityQueue<Long> h5) {
    return h2.contains(num) || h3.contains(num) || h5.contains(num);
  }

  private PriorityQueue<Long> findMin(PriorityQueue<Long> h1, PriorityQueue<Long> h2, PriorityQueue<Long> h3) {
    if(h1.peek() <= h2.peek() && h1.peek() <= h3.peek()) { return h1; }
    if(h2.peek() <= h1.peek() && h2.peek() <= h3.peek()) { return h2; }
    return h3;
  }
}
>>>>>>> 8/14/16
