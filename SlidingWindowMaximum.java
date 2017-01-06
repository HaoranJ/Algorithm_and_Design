import java.util.*;

public class SlidingWindowMaximum {
  /*
  LeetCode - 239
  time = O(n), space = O(k)
  */
  public static int[] maxSlidingWindow(int[] nums, int k) {
    int len = nums.length;
    int[] ans = new int[len-k+1];
    Deque<Integer> deque = new ArrayDeque<>();
    //Use deque to store possible candidates. In a k-length window, if index x < i && nums[x] < nums[i],
    //it's impossible for x to be a max value in all related windows.
    for (int i = k-1; i >= 0; i--) {
      int num = nums[i];
      if (deque.isEmpty() || num >= nums[deque.peekLast()]) {
        deque.offer(i);
      }
    }
    int ptr = 0;
    ans[ptr++] = nums[deque.peekLast()];
    for (int i = k; i < len; i++) {
      int leftIdx = i-k+1, rightIdx = i;
      if (leftIdx-1 == deque.peekLast()) {
        deque.removeLast();
      }
      int newNum = nums[rightIdx];
      while (!deque.isEmpty() && newNum > nums[deque.peekFirst()]) {
        deque.removeFirst();
      }
      deque.push(rightIdx);
      ans[ptr++] = nums[deque.peekLast()];
    }
    return ans;
  }
}
