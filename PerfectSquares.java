import java.util.*;

public class PerfectSquares {
  /*
  LeetCode - 279 Perfect Squares.
  DP - use solved overlapping subproblems to handle with current problem.
  time = O(n)
  */
  static List<Integer> dp = new ArrayList<>();
  static List<Integer> sqNums = new ArrayList<Integer>(){{ add(1) }};
  int sq = 2;
  public int numSquares(int n) {
    assert n >= 1;
    if(dp.size()-1 >= n) return dp.get(n);
    int k = dp.size();
    while(k <= n) {
      while(sq*sq <= k){
        sqNums.add(sq*sq);
        sq++;
      }
      int res = k;
      for(int i = sqNums.size()-1; i > 0; i--) {
        int sn = sqNums.get(i);
        res = Math.min(res, 1+dp.get(k-sn));
      }
      dp.add(k, res);
      k++;
    }
    return dp.get(n);
  }
}
