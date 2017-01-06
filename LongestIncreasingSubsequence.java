import java.util.*;

public class LongestIncreasingSubsequence {
  public int lengthOfLIS(int[] nums) {
    int n = nums.length;
    int[] dp = new int[n];
    int ans = 0;
    for (int i = 0; i < n; i++) {
      int res = 1;
      for (int j = 0; j < i; j++) {
        if (nums[j] < nums[i] && res < dp[j]+1) {
          res = dp[j] + 1;
        }
      }
      dp[i] = res;
      ans = res > ans ? res : ans;
    }
    return ans;
  }

  public static void main(String[] args) {
    int[] nums = {9, 10, 2, 5, 3, 7, 100, -1, 18, 20};
    List<Integer> ans = getLIS(nums);
    for (int num : ans) {
      System.out.print(num + ", ");
    }
  }
  public static List<Integer> getLIS(int[] nums) {
    List<Integer> ans = new ArrayList<>();
    int nLen = nums.length;
    int[] lasts = new int[nLen];
    int[] path = new int[nLen];
    int[] vals = new int[nLen];
    Arrays.fill(path, -1);
    int lenLIS = 0;
    for (int i = 0; i < nLen; i++) {
      int num = nums[i];
      int insert = Arrays.binarySearch(vals, 0, lenLIS, num);
      if(insert < 0) { insert = -(insert + 1); }
      vals[insert] = num;
      lasts[insert] = i;
      path[i] = insert > 0 ? lasts[insert-1] : -1;
      if(insert == lenLIS) { lenLIS++; }
    }
    int idx = lasts[lenLIS-1];
    ans.add(nums[idx]);
    while (path[idx] != -1) {
      idx = path[idx];
      ans.add(nums[idx]);
    }
    Collections.reverse(ans);
    return ans;
  }
}
