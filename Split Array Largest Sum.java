//LeetCode - 410
//1. Binary Search - Because all elements are non-negative, we can have a range for possible results.
//time = O(nlog(hi-lo) + n)
public int splitArray(int[] nums, int m) {
  long sum = 0;
  int max = -1;
  for(int n : nums) {
    max = Math.max(max, n);
    sum += n;
  }
  //The possible range of largest sum of subarrays are lo and hi
  long lo = max, hi = sum;
  //use binary search to find the minimum largest sum in [lo, hi]
  while(lo <= hi) {
    long mid = lo + (hi - lo) / 2;
    if(isValid(nums, m, mid)) {
      hi = mid - 1;
    } else {
      lo = mid + 1;
    }
  }
  return (int)lo;
}
//Because all elements are non-negative, we can use Greedy approach
//return true - if m or less parts can be split, and the sum of each part
//is less than or equals to the ceiling.
//Note, like if m-2 parts cut, and the sum of them is <= ceiling, we can easily cut two more parts into
//four parts to meet the m parts condition.
private boolean isValid(int[] nums, int m, long ceiling) {
  long partSum = 0;
  int partCount = 1;
  for(int n : nums) {
    partSum += n;
    if(partSum > ceiling) {
      partSum = n;
      if(++partCount > m) {
        return false;
      }
    }
  }
  return true;
}

//DP to solve the case that elements can be negative, zero and positive.
//Similar to Knapsack problem,
//For the first (idx+1) numbers in array nums, the part number = ptNum,
//dp[ptNum][idx] = min{ max(dp[ptNum-1][i], sums[idx]-sums[i]) }, Math.max(0, ptNum-2) <= i < idx.
//time = O(m * n^2), space = O(nm)
public int splitArray(int[] nums, int m) {
  int n = nums.length;
  long[] sums = new long[n];
  for(int i = 0; i < n; i++) {
    sums[i] = i == 0 ? nums[0] : sums[i-1] + nums[i];
  }
  //long[part number][element number(idx)]
  long[][] dp = new long[m+1][n];
  //base case
  for(int i = 0; i < n; i++) {
    dp[1][i] = sums[i];
  }
  for(int ptNum = 2; ptNum <= m; ptNum++) {
    for(int idx = ptNum-1; idx < n; idx++) {
      long min = Long.MAX_VALUE;
      for(int i = Math.max(0, ptNum-2); i < idx; i++) {
        long res = Math.max(sums[idx]-sums[i], dp[ptNum-1][i]);
        //because all elements are non-negative, we can stop when res > min.
        if(res <= min) { min = res; }
        else { break; }
      }
      dp[ptNum][idx] = min;
    }
  }
  return (int)dp[m][n-1];
}

//Only need to dp rows to store info.
////time = O(m * n^2), space = O(n)
public int splitArray(int[] nums, int m) {
  int n = nums.length;
  long[] sums = new long[n];
  for(int i = 0; i < n; i++) {
    sums[i] = i == 0 ? nums[0] : sums[i-1] + nums[i];
  }
  long[] dp = new long[n];
  //base case
  for(int i = 0; i < n; i++) {
    dp[i] = sums[i];
  }
  for(int ptNum = 2; ptNum <= m; ptNum++) {
    long[] curDp = new long[n];
    for(int idx = ptNum-1; idx < n; idx++) {
      long min = Long.MAX_VALUE;
      for(int i = Math.max(0, ptNum-2); i < idx; i++) {
        long res = Math.max(sums[idx]-sums[i], dp[i]);
        if(res <= min) { min = res; }
        else { break; }
      }
      curDp[idx] = min;
    }
    dp = curDp;
  }
  return (int)dp[n-1];
}
