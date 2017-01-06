public class WordBreak {
  //LeetCode 139 - Word Break
  //bottom-up DP, time = O(n^2)
  public boolean wordBreak(String s, Set<String> wordDict) {
    int len = s.length();
    boolean[] dp = new boolean[len+1];
    dp[0] = true;
    for (int i = 1; i <= len; i++) {
      for (int j = i-1; j >= 0; j--) {
        if (dp[j] && wordDict.contains(s.substring(j, i))) {
          dp[i] = true;
          break;
        }
      }
    }
    return dp[len];
  }

  //LeetCode 140 - WordBreak II
  //bottom-up DP and backtracking.
  List<String> ans = new ArrayList<>();
  Set<String> wordDict;
  public List<String> wordBreak(String s, Set<String> wordDict) {
    this.wordDict = wordDict;
    List<List<Integer>> dp = new ArrayList<>();
    for (int i = 0; i < s.length(); i++) {
      dp.add(new ArrayList<>());
    }
    for (int i = 0; i < s.length(); i++) {
      if (wordDict.contains(s.substring(0, i+1))) {
        dp.get(i).add(-1); // -1 means the whole substring(0, i+1) is a word in dict.
      }
      for (int p = 0; p < i; p++) {
        if (dp.get(p).size() > 0 && wordDict.contains(s.substring(p+1, i+1))) {
          dp.get(i).add(p);
        }
      }
    }

    dfs(s, dp, s.length()-1, new ArrayList<String>());
    return ans;
  }

  private void dfs(String s, List<List<Integer>> dp, int endIdx, List<String> stc) {
    for (int i : dp.get(endIdx)) {
      stc.add(s.substring(i+1, endIdx+1));
      if (i == -1) {
        //base case
        StringBuilder sb = new StringBuilder();
        for (int j = stc.size()-1; j >= 0; j--) {
          sb.append(stc.get(j) + " ");
        }
        ans.add(sb.toString().trim());
      } else {
        dfs(s, dp, i, stc);
      }
      stc.remove(stc.size()-1); //erase the step
    }
  }
}
