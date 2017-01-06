import java.util.*;

public class SuperUgly {
  //Leetcode - 313 - Super Ugly Number
  //time = O(nlogk), space = O(n+k)
  public int nthSuperUglyNumber(int n, int[] primes) {
    int k = primes.length;
    int[] uglys = new int[n];
    uglys[0] = 1;
    PriorityQueue<Num> minHeap = new PriorityQueue<>();
    for (int prm : primes) {
      minHeap.add(new Num(prm, 1, prm));
    }
    for (int i = 1; i < n; i++) {
      Num min = minHeap.poll();
      uglys[i] = (int)min.val;
      //in PriorityQueue, #add/offer/remove/poll - O(logn); #remove(Obejct)/contains(Object) - O(n)
      while (!minHeap.isEmpty() && min.val == minHeap.peek().val) {
        Num top = minHeap.poll();
        minHeap.add(new Num((long)(uglys[top.uglyIdx])*(long)top.prime, top.uglyIdx+1, top.prime));
      }
      minHeap.add(new Num((long)uglys[min.uglyIdx]*(long)min.prime, min.uglyIdx+1, min.prime));
    }
    return uglys[n-1];
  }

  private static class Num implements Comparable<Num> {
    long val;
    int uglyIdx;
    int prime;
    Num(long v, int ui, int pr) {
      this.val = v;
      this.uglyIdx = ui;
      this.prime = pr;
    }

    @Override
    public int compareTo(Num that) {
      return (int)(this.val - that.val);
    }
  }
}
