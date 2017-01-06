import java.util.*;

public class DifferentWaysToAddParentheses {
  public List<Integer> diffWaysToCompute(String input) {
    List<Integer> nums = new ArrayList<>();
    List<Character> signs = new ArrayList<>();
    int i = 0;
    int len = input.length();
    while (i < len) {
      int j = i+1;
      while (j < len && input.charAt(j) != '+' && input.charAt(j) != '-' && input.charAt(j) != '*') {
        j++;
      }
      nums.add(Integer.parseInt(input.substring(i, j)));
      if(j < len) { signs.add(input.charAt(j)); }
      i = j + 1;
    }
    int n = nums.size();
    Map<String, List<Integer>> dp = new HashMap<>();
    for (int l = 1; l <= n; l++) {
      for (int p = 0; p <= n-l; p++) {
        int q = p + l - 1;
        List<Integer> res = new ArrayList<>();
        for (int s = p; s < q; s++) {
          String key1 = p + "," + s;
          int s2 = s + 1;
          String key2 = s2 + "," + q;
          compute(dp.get(key1), dp.get(key2), signs.get(s), res);
        }
        String key = p + "," + q;
        if(res.isEmpty()) { res.add(nums.get(p)); }
        dp.put(key, res);
      }
    }
    int nt = n - 1;
    String out = "0," + nt;
    return dp.get(out);
  }

  private void compute(List<Integer> l1, List<Integer> l2, char sign, List<Integer> res) {
    for (int x : l1) {
      for (int y : l2) {
        switch(sign) {
          case '+':
          res.add(x+y);
          break;
          case '-':
          res.add(x-y);
          break;
          case '*':
          res.add(x*y);
          break;
          default:
          break;
        }
      }
    }
  }
}
