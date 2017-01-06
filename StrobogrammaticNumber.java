import java.util.*;

public class StrobogrammaticNumber {
  //LeetCode 246
  //time = O(n)
  public boolean isStrobogrammatic(String num) {
    int len = num.length();
    for (int i = 0; i <= len/2; i++) {
      int pre = num.charAt(i) - '0';
      int post = num.charAt(len-i-1) - '0';
      if ((pre == 0 && post == 0) || (pre == 1 && post == 1)
          || (pre == 8 && post == 8) || (pre == 6 && post == 9)
          || (pre == 9 && post == 6)) {
        continue;
      } else {
        return false;
      }
    }
    return true;
  }

  /*
  LeetCode 247.
  From inner to outter with recursion, but it needs more overhead. However, it's more readiable and concise
  */
  static List<String> ans = new ArrayList<>();
  static String[][] pairs = {{"0", "0"}, {"1", "1"}, {"8", "8"}, {"6", "9"}, {"9", "6"}};
  public List<String> findStrobogrammatic(int n) {
    assert n >= 0;
    ans.clear();
    recur(n, n);
    return ans;
  }

  private void recur(int k, int n) {
    if (k == 0) {
      ans.add("");
      return;
    }
    if (k == 1) {
      ans.add("0");
      ans.add("1");
      ans.add("8");
      return;
    }
    //overhead here, need more time to run.
    recur(k-2, n);
    List<String> list = new ArrayList<>();
    for (int i = 0; i < ans.size(); i++) {
      String s = ans.get(i);
      for (int j = 0; j < pairs.length; j++) {
        String[] pair = pairs[j];
        if (j == 0 && k == n) {
          continue;
        }
        list.add(pair[0] + s + pair[1]);
      }
    }
    ans = list;
  }

  //from outter to inner, no overhead, faster.
  static List<String> ans = new ArrayList<>();
  static char[][] pairs = {{'0', '0'}, {'1', '1'}, {'8', '8'}, {'6', '9'}, {'9', '6'}};
  public List<String> findStrobogrammatic_WithoutOverhead(int n) {
    ans.clear();
    char[] base = new char[n];
    recur(0, n, base);
    return ans;
  }

  private void recur(int k, int n, char[] base) {
    if (n == 1) {
      ans.add("0");
      ans.add("1");
      ans.add("8");
      return;
    }
    if ((n%2 == 0 && k == n/2) || (n%2 == 1 && k == n/2 + 1)) {
      ans.add(new String(base));
      return;
    } else if (k == 0) {
      for (int i = 1; i < pairs.length; i++) {
        char[] tp = Arrays.copyOf(base, n);
        tp[k] = pairs[i][0];
        tp[n-k-1] = pairs[i][1];
        recur(k+1, n, tp);
      }
    } else if (n%2 == 1 && k == n/2) {
      for (int i = 0; i < 3; i++) {
        char[] tp = Arrays.copyOf(base, n);
        tp[k] = pairs[i][0];
        tp[n-k-1] = pairs[i][1];
        recur(k+1, n, tp);
      }
    } else {
      for (int i = 0; i < pairs.length; i++) {
        char[] tp = Arrays.copyOf(base, n);
        tp[k] = pairs[i][0];
        tp[n-k-1] = pairs[i][1];
        recur(k+1, n, tp);
      }
    }
  }
}
