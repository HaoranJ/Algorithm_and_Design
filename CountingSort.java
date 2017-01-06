import java.util.*;

public class CountingSort {
  public static int[] countingSort(Integer... nums) {
    assert nums != null;
    int max = 0;
    for (int x : nums) {
      assert x >= 0;
      max = Math.max(max, x);
    }
    int n = nums.length;
    int[] c = new int[max+1];
    int[] out = new int[n];
    for (int x : nums) {
      c[x]++;
    }
    for (int i = 1; i < c.length; i++) {
      c[i] = c[i] + c[i-1];
    }
    for (int j = n-1; j >= 0; j--) {
      int num = nums[j];
      out[c[num]-1] = num;
      c[num]--;
    }
    return out;
  }

  public static void main(String[] args) {
    int[] out = countingSort(3);
    for (int x : out) {
      System.out.print(x + ", ");
    }
  }
}
