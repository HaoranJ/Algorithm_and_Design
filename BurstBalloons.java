import java.util.*;

public class BurstBalloons {
  /*
  LeetCode - 312 Burst Balloons
  */
  /*
  Naive recursion, choose one from n balloons to burst => n-1 left => continue to pick
  one to burst. in this process, use map to memorize.
  time = O(n!), space = O(nC(n,n) + (n-1)C(n,n-1) + ... + 2C(n,2) + 1C(n,1))
  */
  Map<List<Integer>, Integer> map;
  public int maxCoins(int[] nums) {
    if(nums == null || nums.length == 0) return 0;
    map = new HashMap<>();
    List<Integer> list = new ArrayList<>();
    for(int i = 0; i < nums.length; i++) {
      list.add(i);
      List<Integer> key = new ArrayList<>();
      key.add(i);
      map.put(key, nums[i]);
    }
    return dfs(list, nums);
  }

  private int dfs(List<Integer> list, int[] nums) {
    int n = list.size();
    int ret = 0;
    if (map.containsKey(list)) {
      return map.get(list);
    }
    for(int i = 0; i < n; i++) {
      int coins =(i == 0 ? 1 : nums[list.get(i-1)]) * nums[list.get(i)] * (i == n-1 ? 1 : nums[list.get(i+1)]);
      List<Integer> temp = new ArrayList<>(list);
      temp.remove(i);
      ret = Math.max(ret, coins + dfs(temp, nums));
    }
    map.put(list, ret);
    return ret;
  }

  /*
  Bottom-up recursion, start with small-number-balloons-left cases until n balloons,
  time = O(C(n,1) + 2C(n,2) + ... + nC(n,n)), space = max(kC(n,k)), k = 1,2,...,n
  */
  Map<List<Integer>, Integer> map;
  Map<List<Integer>, Integer> map2;
  public int maxCoins(int[] nums) {
    if(nums == null || nums.length == 0) return 0;
    map = new HashMap<>();
    map2 = new HashMap<>();
    List<Integer> list = new ArrayList<>();
    for(int i = 0; i < nums.length; i++) {
      list.add(i);
      List<Integer> key = new ArrayList<>();
      key.add(i);
      map.put(key, nums[i]);
    }
    int k = 2;
    while (k <= nums.length) {
      for (Map.Entry<List<Integer>, Integer> e : map.entrySet() ) {
        List<Integer> key = e.getKey();
        int tail = key.get(key.size()-1);
        for (int i = tail + 1; i < nums.length; i++) {
          List<Integer> pre = new ArrayList<>(key);
          pre.add(i);
          compute(pre, nums);
        }
      }
      map = map2;
      map2 = new HashMap<>();
      k++;
    }
    return map.get(list);
  }

  private void compute(List<Integer> list, int[] nums) {
    int ret = 0;
    int n = list.size();
    for (int i = 0; i < n; i++) {
      int coins = (i == 0 ? 1 : nums[list.get(i-1)]) * nums[list.get(i)] * (i == n-1 ? 1 : nums[list.get(i+1)]);
      List<Integer> temp = new ArrayList<>(list);
      temp.remove(i);
      ret = Math.max(ret, coins + map.get(temp));
    }
    map2.put(list, ret);
  }

  /*
  Reverse Thinking!! use the last balloons to burst as the separate, and conquer
  the sub left and right section.
  */
  int[][] memo;
  public int maxCoins(int[] nums) {
    int n = nums.length;
    //Based on the initial array nums, add two fake balloons with 1 coin at the head and end.
    int[] arr = new int[n+2];
    for (int i = 0; i < n; i++) {
      arr[i+1] = nums[i];
    }
    arr[0] = 1; arr[n+1] = 1;
    //memo[lo][hi] = balloons at lo and hi are boundaries, the max coins after bursting all
    //balloons between lo and hi, both exclusive.
    memo = new int[n+2][n+2];
    return dfs(arr, 0, n+1);
  }

  private int dfs(int[] arr, int lo, int hi) {
    if(hi-lo == 1) {
      return 0;
    }
    if (memo[lo][hi] > 0) {
      return memo[lo][hi];
    }
    int ret = 0;
    for (int i = lo+1; i < hi; i++) {
      ret = Math.max(ret, dfs(arr, lo, i) + arr[lo] * arr[i] * arr[hi] + dfs(arr, i, hi));
    }
    memo[lo][hi] = ret;
    return ret;
  }

  /*
  Optimal solution - DP with bottom-up fashion
  time = O(n^3), space = O(n^2)
  */
  public int maxCoins(int[] nums) {
    List<Integer> list = new ArrayList<>();
    //fake balloon at head
    list.add(1);
    for (int x : nums) {
      //busrt 0-coin balloons first.
      if(x > 0) { list.add(x); }
    }
    //fake balloon at end
    list.add(1);
    int n = list.size();
    int[][] dp = new int[n][n];
    //l is the length, bottom-up
    for (int l = 2; l <= n; l++) {
      for (int lo = 0; lo <= n-l; lo++) {
        int hi = lo + l - 1;
        if (l == 2) {
          dp[lo][hi] = 0;
        } else {
          int res = 0;
          for (int j = lo+1; j < hi; j++) {
            res = Math.max(res, list.get(lo)*list.get(j)*list.get(hi) + dp[lo][j] + dp[j][hi]);
          }
          dp[lo][hi] = res;
        }
      }
    }
    return dp[0][n-1];
  }
}
