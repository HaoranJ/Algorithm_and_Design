import java.util.*;

public class SearchForARange {
  /*
  LeetCode - 34, Search for a range
  */
  //Iterative tweaked binary search - time = O(logn)
  public int[] searchRange(int[] nums, int target) {
    int[] ans = new int[2];
    int lo = 0, hi = nums.length-1;
    int mid = 0;
    while (lo <= hi) {
      mid = (lo + hi) / 2;
      if(target > nums[mid]) { lo = mid+1; }
      else { hi = mid-1; }
    }
    ans[0] = (lo < nums.length && nums[lo] == target) ? lo : -1;
    if(ans[0] == -1) {
      ans[1] == -1;
      return ans;
    }
    hi = nums.length-1;
    while (lo <= hi) {
      mid = (lo + hi) / 2;
      if(target < nums[mid]) { hi = mid - 1; }
      else { lo = mid + 1; }
    }
    ans[1] = (hi >= 0 && nums[hi] == target) ? hi : -1;
    return ans;
  }

  //Recursive tweaked binary search - time = O(logn)
  public int[] searchRange(int[] nums, int target) {
    int[] ans = new int[2];
    int left = searchLeft(nums, 0, nums.length-1, target);
    if(left == -1) {
      ans[0] = -1; ans[1] = -1;
    } else {
      int right = searchRight(nums, left, nums.length-1, target);
      ans[0] = left; ans[1] = right;
    }
    return ans;
  }

  private int searchLeft(int[] nums, int lo, int hi, int target) {
    if(lo > hi) {
      return (lo < nums.length && nums[lo] == target) ? lo : -1;
    }
    int mid = (hi + lo) / 2;
    if(target > nums[mid]) {
      return searchLeft(nums, mid+1, hi, target);
    } else {
      return searchLeft(nums, lo, mid-1, target);
    }
  }

  private int searchRight(int[] nums, int lo, int hi, int target) {
    if(lo > hi) {
      return (hi >= 0 && nums[hi] == target) ? hi : -1;
    }
    int mid = (hi + lo) / 2;
    if(target < nums[mid]) {
      return searchRight(nums, lo, mid-1, target);
    } else {
      return searchRight(nums, mid+1, hi, target);
    }
  }
}
