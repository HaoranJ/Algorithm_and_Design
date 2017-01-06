public class RegularExpressionMatching {
  //LeetCode 10
  public boolean isMatch(String s, String p) {
    //base case
    if (s.isEmpty() && p.isEmpty()) { return true; }
    if(p.isEmpty()) { return s.isEmpty(); }
    if(p.length() == 1) { return p != "*" && s.length() == 1 && (s.equals(p) || p.equals(".")); }

    if ('*' == p.charAt(1)) {
      return isMatch(s, p.substring(2)) ||
            !s.isEmpty() && ('.' == p.charAt(0) || s.charAt(0) == p.charAt(0)) && isMatch(s.substring(1), p);
    } else {
      return !s.isEmpty() && (s.charAt(0) == p.charAt(0) || '.' == p.charAt(0)) && isMatch(s.substring(1), p.substring(1));
    }
  }

  //DP, time = O(n^2), space = O(n^2)
  public boolean isMatch(String s, String p) {
    int sLen = s.length(), pLen = p.length();
    char[] sa = s.toCharArray(), pa = p.toCharArray();
    boolean[][] dp = new boolean[sLen+1][pLen+1];
    //corner case
    dp[0][0] = true;
    for (int i = 1; i <= sLen; i++) {
      dp[i][0] = false;
    }
    for (int j = 1; j <= pLen; j++) {
      dp[0][j] = pa[j-1] == '*' && dp[0][j-2];
    }
    for (int i = 1; i <= sLen; i++) {
      for (int j = 1; j <= pLen; j++) {
        if (pa[j-1] != '*') {
          dp[i][j] = dp[i-1][j-1] && (sa[i-1] == pa[j-1] || pa[j-1] == '.');
        } else {
          dp[i][j] = dp[i][j-2] || (dp[i-1][j] && (sa[i-1] == pa[j-2] || pa[j-2] == '.'));
        }
      }
    }
    return dp[sLen][pLen];
  }
}
