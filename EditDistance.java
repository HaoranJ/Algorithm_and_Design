import java.util.*;

public class EditDistance {
  //LeetCode 72, https://en.wikipedia.org/wiki/Edit_distance
  //time = O(mn), space = O(min(m,n)) = linear space
  public int minDistance(String word1, String word2) {
    if(word1.length() < word2.length()) {
      return minDistance(word2, word1);
    }
    char[] ch1 = word1.toCharArray(), ch2 = word2.toCharArray();
    int len1 = ch1.length, len2 = ch2.length;
    int[] dp = new int[len2+1];
    //corner case
    for (int i = 0; i < len2+1; i++) {
      dp[i] = i;
    }

    for (int i = 1; i < len1+1; i++) {
      int pre = i, cur = 0;
      for (int j = 1; j < len2+1; j++) {
        if (ch1[i-1] == ch2[j-1]) {
          cur = dp[j-1];
        } else {
          int replace = dp[j-1] + 1;
          int add = dp[j] + 1;
          int remove = pre + 1;
          cur = Math.min(replace, Math.min(add, remove));
        }
        dp[j-1] = pre;
        pre = cur;
      }
      dp[len2] = pre;
    }
    return dp[len2];
  }

  public int minDistance(String word1, String word2) {
    char[] ch1 = word1.toCharArray(), ch2 = word2.toCharArray();
    int len1 = ch1.length, len2 = ch2.length;
    int[][] dp = new int[len1+1][len2+1];
    //corner case
    for (int i = 0; i < len2+1; i++) {
      dp[0][i] = i;
    }
    for (int i = 0; i < len1+1; i++) {
      dp[i][0] = i;
    }
    for (int i = 1; i < len1+1; i++) {
      for (int j = 1; j < len2+1; j++) {
        if (ch1[i-1] == ch2[j-1]) {
          dp[i][j] = dp[i-1][j-1];
        } else {
          int replace = dp[i-1][j-1] + 1;
          int add = dp[i-1][j] + 1;
          int remove = dp[i][j-1] + 1;
          dp[i][j] = Math.min(replace, Math.min(add, remove));
        }
      }
    }
    return dp[len1][len2];
  }
}
