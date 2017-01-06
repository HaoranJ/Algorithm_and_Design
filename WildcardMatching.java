//LeetCode 44. WildCard Matching.
// Implement wildcard pattern matching with support for '?' and '*'.
//
// '?' Matches any single character.
// '*' Matches any sequence of characters (including the empty sequence).
//
// The matching should cover the entire input string (not partial).
//
// The function prototype should be:
// bool isMatch(const char *s, const char *p)
//
// Some examples:
// isMatch("aa","a") → false
// isMatch("aa","aa") → true
// isMatch("aaa","aa") → false
// isMatch("aa", "*") → true
// isMatch("aa", "a*") → true
// isMatch("ab", "?*") → true
// isMatch("aab", "c*a*b") → false

//DP, time = O(mn), space = O(mn)
public boolean isMatch(String s, String p) {
  boolean[][] dp = new boolean[s.length()+1][p.length()+1];
  //corner case
  for(int sLen = 1; sLen <= s.length(); sLen++) {
    dp[sLen][0] = false;
  }
  dp[0][0] = true;
  for(int pLen = 1; pLen <= p.length(); pLen++) {
    dp[0][pLen] = dp[0][pLen-1] && p.charAt(pLen-1) == '*' ? true : false;
  }
  for(int i = 0; i < s.length(); i++) {
    for(int j = 0; j < p.length(); j++) {
      //equals
      if(s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') {
        dp[i+1][j+1] = dp[i][j];
      } else if(p.charAt(j) == '*') {
        dp[i+1][j+1] = dp[i][j+1] || dp[i+1][j]; // * is not empty || * is empty
      } else {
        dp[i+1][j+1] = false;
      }
    }
  }
  return dp[s.length()][p.length()];
}

//"hi", "*?"
//"abexyef", "ab*ef"
//two pointers for str and pattern, save the star index and possible first matched char in s,
//do a DFS.
//time is not necessarily linear, but space in constant.
//in worst case, time = O(str.length() * pattern.length())
//str = "aaaaaaaaaaaaaaaa"
//pattern = "*aaa"
public boolean isMatch(String str, String pattern) {
  int sIdx = 0, pIdx = 0, starIdx = -1, match = 0;
  while(sIdx < str.length()) {
    if(pIdx < pattern.length() && (str.charAt(sIdx) == pattern.charAt(pIdx) || pattern.charAt(pIdx) == '?')) {
      sIdx++;
      pIdx++;
    } else if (pIdx < pattern.length() && pattern.charAt(pIdx) == '*') {
      //save info for *
      starIdx = pIdx;
      match = sIdx; //use match to keep track of chars covered by *
      pIdx++;
    } else if (starIdx != -1) {
      //check if the last star * can cover s.charAt(sIdx)
      pIdx = starIdx + 1;
      sIdx = ++match;
    } else {
      return false;
    }
  }
  for(int i = pIdx; i < pattern.length(); i++) {
    if(pattern.charAt(i) != '*') { return false; }
  }
  return true;
}
