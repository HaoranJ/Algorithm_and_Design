import java.util.*;

public class PaintFence {
  //LeetCode 276.
  //DP, for n = j, it depends on j-1, j-2. we have two cases - same color for j-1 and j - 2; diff color.
  //time = O(n), space = O(1)
  public int numWays(int n, int k){
    assert n >= 0;
    if(n==0) { return 0; }
    int diff = k, same = 0;
    for(int i = 2; i <= n; i++) {
      int sameOld = same;
      same = diff;
      diff = (k-1)*diff + (k-1)*sameOld;
    }
    return diff+same;
  }
}
