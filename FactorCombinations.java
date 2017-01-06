import java.util.*;

/*
LeetCode 254, recursive 
*/
public class FactorCombinations {
  static List<List<Integer>> ans = new ArrayList<>();
  public List<List<Integer>> getFactors(int n) {
    assert n > 0;
    ans.clear();
    List<Integer> base = new ArrayList<>();
    recursive(n, base);
    return ans;
  }

  private void recursive(int n, List<Integer> base) {
    int p = base.size() == 0 ? 2 : base.get(base.size()-1);
    while (p < n) {
      if (n % p == 0 && p <= n/p) {
        int quo = n/p;
        if (p > quo) {
          return;
        }
        List<Integer> list = new ArrayList<>(base);
        list.add(p);
        list.add(quo);
        ans.add(list);
        base.add(p);
        recursive(quo, base);
        base.remove(base.size()-1);
      }
      p++;
    }
  }
}
