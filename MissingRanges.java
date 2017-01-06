import java.util.*;

public class MissingRanges {
  /*
  LeetCode 163 - Missing Ranges
  time = O(n)
  */
  public List<String> findMissingRanges(int[] nums, int lower, int upper) {
    int n = nums.length, i = 0;
    List<String> ans = new ArrayList<>();
    int[] rg = {0, 0};
    while(i < n) {
      rg[0] = nums[i];
      int p = i+1;
      while(p < n && nums[p] - nums[p-1] == 1) {
        p++;
      }
      rg[1] = nums[p-1];
      //add range
      if(rg[0] == lower+1) ans.add(String.valueOf(lower));
      else if(rg[0] > lower+1) ans.add(String.valueOf(lower) + "->" + String.valueOf(rg[0]-1));
      lower = rg[1]+1;
      i = p;
    }
    if(lower == upper) ans.add(String.valueOf(lower));
    else if(lower < upper) ans.add(String.valueOf(lower) + "->" + String.valueOf(uppper));
    return ans;
  }
}
