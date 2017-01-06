// In the computer world, use restricted resource you have to generate maximum benefit is what we always want to pursue.
//
// For now, suppose you are a dominator of m 0s and n 1s respectively. On the other hand, there is an array with strings consisting of only 0s and 1s.
//
// Now your task is to find the maximum number of strings that you can form with given m 0s and n 1s. Each 0 and 1 can be used at most once.
//
// Note:
// The given numbers of 0s and 1s will both not exceed 100
// The size of given string array won't exceed 600.
// Example 1:
// Input: Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3
// Output: 4
//
// Explanation: This are totally 4 strings can be formed by the using of 5 0s and 3 1s, which are “10,”0001”,”1”,”0”
// Example 2:
// Input: Array = {"10", "0", "1"}, m = 1, n = 1
// Output: 2
//
// Explanation: You could form "10", but then you'd have nothing left. Better form "0" and "1".

//Similar to 0-1 knapsack problem

//time = O(len * average length of strs + len * m * n), space = O(len * mn)
public int findMaxForm(String[] strs, int m, int n) {
  int len = strs.length;
  int[][][] dp = new int[len][m+1][n+1];
  for (int s = 0; s < len; s++) {
    int[] res = getZO(strs[s]);
    for (int i = 0; i <= m; i++) {
      for (int j = 0; j <= n; j++) {
        if (res[0] <= i && res[1] <= j) {
          dp[s][i][j] = s == 0 ? 1 : Math.max(dp[s-1][i][j], 1 + dp[s-1][i-res[0]][j-res[1]]);
        } else {
          dp[s][i][j] = s == 0 ? 0 : dp[s-1][i][j];
        }
      }
    }
  }
  return dp[len-1][m][n];
}

//Get rid of "[len]" in dp. 
public int findMaxForm(String[] strs, int m, int n) {
  int len = strs.length;
  int[][] preDP = new int[m+1][n+1];
  for (int s = 0; s < len; s++) {
    int[] res = getZO(strs[s]);
    int[][] curDP = curDP = new int[m+1][n+1];
    for (int i = 0; i <= m; i++) {
      for (int j = 0; j <= n; j++) {
        if (res[0] <= i && res[1] <= j) {
          curDP[i][j] = Math.max(preDP[i][j], 1 + preDP[i-res[0]][j-res[1]]);
        } else {
          curDP[i][j] = preDP[i][j]
        }
      }
    }
    preDP = curDP;
  }
  return preDP[m][n];
}

private int[] getZO(String s) {
  int zr = 0, one = 0;
  for (char ch : s.toCharArray()) {
    if (ch == '1') {
      one++;
    }
    if (ch == '0') {
      zr++;
    }
  }
  return new int[] {zr, one};
}
