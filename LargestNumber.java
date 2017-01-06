import java.util.*;

public class LargestNumber {
  //LeetCode 179.
  //time = O(nlogn)
  public String largestNumber(int[] nums) {
    int len = nums.length;
    String[] numStrs = new String[len];
    for (int i = 0; i < len; i++) {
      numStrs[i] = String.valueOf(nums[i]);
    }
    Arrays.sort(numStrs, new cmpr());
    if(numStrs[0].charAt(0) == '0') return "0";
    StringBuilder sb = new StringBuilder();
    for (String str : numStrs) {
      sb.append(str);
    }
    return sb.toString();
  }

  private static class cmpr implements Comparator<String> {
    @Override
    public int compare(String s1, String s2) {
      String comb1 = s1 + s2;
      String comb2 = s2 + s1;
      //descending order.
      return comb2.compareTo(comb1);
    }
  }
}
