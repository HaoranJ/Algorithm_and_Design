import java.util.*;

public class LongestPalindromicSubstring {
  /*
  LeetCode - 5
  two pointers - time = O(n)(best case), O(n^2)(worst case);
  space = O(1)
  */
  public String longestPalindrome_TwoPointers(String s) {
    assert s != null;
    int n = s.length();
    int start = 0, maxLen = 1;
    int i = 0;
    while (i < n) {
      if(2*(n-i) < maxLen) { break; }
      int lo = i, hi = i;
      //skip duplicates
      while(hi < n-1 && s.charAt(hi) == s.charAt(hi+1)) { hi++; }
      i = hi + 1;
      //expand palindrome
      while (lo > 0 && hi < n-1 && s.charAt(lo-1) == s.charAt(hi+1)) {
        lo--;
        hi++;
      }
      int curLen = hi - lo + 1;
      if (curLen > maxLen) {
        start = lo;
        maxLen = curLen;
      }
    }
    return s.substring(start, start+maxLen);
  }

  //DP, time = O(n^2), space = O(n^2)
  public String longestPalindrome_DP(String s) {
    int n = s.length();
    boolean[][] isPa = new boolean[n][n];
    int lo = 0, hi = 0, maxLen = 1;
    for(int l = 1; l <= n; l++) {
      for(int i = 0; i <= n-l; i++) {
        int j = i + l - 1;
        if(i == j) {
          isPa[i][j] = true;
        } else {
          if(s.charAt(i) == s.charAt(j)) {
            if(j-i == 1 || isPa[i+1][j-1]) {
              isPa[i][j] = true;
              int len = j - i + 1;
              if(len > maxLen) {
                lo = i;
                hi = j;
                maxLen = len;
              }
            } else {
              isPa[i][j] = false;
            }
          } else {
            isPa[i][j] = false;
          }
        }
      }
    }
    return s.substring(lo, hi+1);
  }
}
