import java.util.*;

public class GrayCode {
  /*
  LeetCode - 89
  Wiki Gray Code
  time = O(n * 2^n), space = O(1)
  */
  public List<Integer> grayCode(int n) {
    List<Integer> ans = new ArrayList<>();
    ans.add(0);
    for (int i = 0; i < n; i++) {
      for (int j = ans.size()-1; j >= 0; j--) {
        int num = ans.get(j);
        ans.add(num | (1 << i));
      }
    }
    return ans;
  }

  List<Integer> ans = new ArrayList<>();
  public List<Integer> grayCode(int n) {
    assert n >= 0;
    ans.clear();
    ans.add(0);
    change(n, n, 0);
    return ans;
  }

  private void change(int n, int preChangePos, int num) {
    int limit = 1 << n;
    for (int p = 0; p < n; p++) {
      if (p != preChangePos) {
        if(limit == ans.size()) { return; }
        int codeNum = (1 << p) ^ num;
        if(!ans.contains(codeNum)) {
          ans.add(codeNum);
          change(n, p, codeNum);
        }
      }
    }
  }
}
