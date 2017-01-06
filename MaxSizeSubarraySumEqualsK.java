/*
LeetCode - 325.
1. convert to sum array, sums[i] = sum from 0 to i inclusively.
2. use hashmap to check the index of target in constant time.

time = O(n), space = O(n)
*/

public int maxSubArrayLen(int[] nums, int k) {
  Map<Integer, Integer> map = new HashMap<>();
  int[] sums = new int[nums.length];
  int ans = 0;
  for (int i = 0; i < nums.length; i++) {
    sums[i] = i == 0 ? nums[0] : sums[i-1] + nums[i];
    if(sums[i] == k) { ans = i+1; }
    else {
      int target = sums[i] - k;
      if (map.containsKey(target)) {
        ans = Math.max(ans, i - map.get(target));
      }
    }
    if (!map.containsKey(sums[i])) {
      map.put(sums[i], i);
    }
  }
  return ans;
}

//same method.
public int maxSubArrayLen(int[] nums, int k) {
  int N = nums.length;
  int[] sums = new int[N+1];
  int ans = 0;
  Map<Integer, Integer> map = HashMap<>();
  int sum = 0;
  for(int i = 0; i < N; i++) {
    sum += nums[i];
    sums[i+1] = sum;
    map.put(sum, i+1);
  }
  for(int i = 0; i <= N; i++) {
    int lo = sums[i];
    int hi = lo + k;
    if(map.contains(hi)) {
      ans = Math.max(ans, map.get(hi) - i);
    }
  }
  return ans;
}
