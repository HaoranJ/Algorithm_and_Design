import java.util.*;

public class SearchInRotatedSortedArray {
  /*
  LeetCode 33.
  One pass revised binary search. - recursive
  time = O(logn)
  */
  public int search(int[] nums, int target) {
    int n = nums.length;
    return search(nums, target, 0, n-1);
  }

  private int search(int[] nums, int target, int lo, int hi) {
    if(lo > hi) { return -1; }
    int mid = (lo + hi) / 2;
    if(target == nums[mid]) { return mid; }
    else if(nums[lo] > nums[mid]) {
      if(nums[mid] < target && target <= nums[hi]) { return search(nums, target, mid+1, hi); }
      else { return search(nums, target, lo, mid-1); }
    } else {
      if(nums[mid] > target && target >= nums[lo]) { return search(nums, target, lo, mid-1); }
      else { return search(nums, target, mid+1, hi); }
    }
  }

  /* One pass revised binary search - iteration. time = O(logn) */
  public int search(int[] nums, int target) {
    int n = nums.length;
    int lo = 0, hi = n - 1;
    while (lo <= hi) {
      int mid = (lo + hi) / 2;
      if(nums[mid] == target) { return mid; }
      if (nums[lo] > nums[mid]) {
        if(nums[mid] < target && target <= nums[hi]) { lo = mid+1; }
        else { hi = mid-1; }
      } else {
        if(target < nums[mid] && nums[lo] <= target) { hi = mid-1; }
        else { lo = mid+1; }
      }
    }
    return -1;
  }

  /*
  Find out the pivot first, and then do binary search with the two sorted parts.
  time = O(logn)
  */
  public  int search(int[] nums, int target) {
    int pivot = findPivot(nums, 0, nums.length-1);
    int ans = Arrays.binarySearch(nums, 0, pivot, target);
    if(ans < 0) {
      ans = Arrays.binarySearch(nums, pivot, nums.length, target);
    }
    return ans < 0 ? -1 : ans;
  }

  private  int findPivot(int[] nums, int lo, int hi) {
    if(lo == hi) { return lo; }
    int mid = (lo + hi) / 2;
    if(lo < mid && nums[mid-1] > nums[mid]) { return mid; }
    if(mid < hi && nums[mid] > nums[mid+1]) { return mid+1; }
    if(nums[lo]  > nums[mid]) { return findPivot(nums, lo, mid-1); }
    else { return findPivot(nums, mid+1, hi); }
  }
}
