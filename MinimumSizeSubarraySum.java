import java.util.*;

public class MinimumSizeSubarraySum {
  /*
  LeetCode - 209
  Sliding window.. similar to MinimumWindowSubstring
  Two pointers - time = O(n), space = O(1)
  */
  public int minSubArrayLen(int s, int[] nums) {
    assert s > 0;
    int len = nums.length;
    int i = 0, j = 0;
    int subArrSum = 0;
    int ans = len + 1;
    while (j < len) {
      while (j < len && subArrSum < s) {
        subArrSum += nums[j++];
      }
      if (subArrSum < s) {
        break;
      }
      while (i < j && subArrSum >= s) {
        subArrSum -= nums[i++];
      }
      int subLen = j - i + 1;
      ans = subLen < ans ? subLen : ans;
    }
    return ans == len + 1 ? 0 : ans;
  }

  /*
  Convert the array to sum array that is increasing, then use binary search.
  time = O(nlogn), space = O(1)
  */
  public int minSubArrayLen_BinarySearch(int s, int[] nums) {
    int len = nums.length;
    for (int i = 1; i < len; i++) {
      nums[i] += nums[i-1];
    }
    int ans = len + 1;
    for (int lo = 0; lo < len; lo++) {
      int num = lo == 0 ? 0 : nums[lo-1];
      int hi = binarySearch(lo, len-1, s + num, nums);
      if (hi != len) {
        int subLen = hi - lo + 1;
        ans = subLen < ans ? subLen : ans;
      } else {
        break;
      }
    }
    return ans == len + 1 ? 0 : ans;
  }

  //** find the first number which meet the requirement.
  private int binarySearch(int lo, int hi, int target, int[] nums) {
    while (lo <= hi) {
      int mid = (lo + hi) / 2;
      if (nums[mid] >= target) {
        hi = mid - 1;
      } else {
        lo = mid + 1;
      }
    }
    return lo;
  }
}
