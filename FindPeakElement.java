import java.util.*;

public class FindPeakElement {
  /*
  LeetCode 162 - Find Peak Element.
  */
  /*
  Naive one, worst case time = O(n)
  */
  public int findPeakElement_Naive(int[] nums) {
    int n = nums.length;
    assert n > 0;
    for (int i = 0; i < n-1; i++) {
      if (nums[i] > nums[i+1]) {
        return i;
      }
    }
    return n-1;
  }

  /*
  Binary Search, time = O(logn)
  */
  public int findPeakElement_BinarySearch(int[] nums) {
    assert num != null;
    int n = nums.length;
    assert n > 0;
    return bs(nums, 0, n-1);
  }

  private int bs(int[] nums, int lo, int hi){
    int n = nums.length;
    int mid = (lo+hi)/2;
    //have to use long to cover the Integer.MIN_VALUE-is-x corner case.
    long x = (long)nums[mid];
    long left = mid == 0 ? Long.MIN_VALUE : (long)nums[mid-1];
    long right = mid == n-1 ? Long.MIN_VALUE : (long)nums[mid+1];
    if(left < x && right < x) return mid;
    else if(left < x) return bs(nums, mid+1, hi);
    else return bs(nums, lo, mid-1);
  }

  /*
  Simpler Binary Search.
  time = O(logn)
  */
  public int findPeakElement_Simple(int[] nums) {
    return bSearch(nums, 0, nums.length-1);
  }
  private int bSearch(int[] nums, int lo, int hi) {
    if (lo == hi) {
      return lo;
    } else {
      int mid = (lo + hi) / 2;
      if (nums[mid] > nums[mid+1]) {
        return bSearch(nums, lo, mid);
      } else {
        return bSearch(nums, mid+1, hi);
      }
    }
  }

  public int findPeakElement_Simple_Iterative(int[] nums) {
    int lo = 0, hi = nums.length-1;
    while (lo < hi) {
      int mid = (lo + hi) / 2;
      if (nums[mid] > nums[mid+1]) {
        hi = mid;
      } else {
        lo = mid+1;
      }
    }
    return lo;
  }
}
