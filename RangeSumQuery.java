public class NumArray {
  private int[] sumsToHere;
  public NumArray(int[] nums) {
    int len = nums.length;
    sumsToHere = new int[len];
    for (int i = 0; i < len; i++) {
      sumsToHere[i] = (i == 0 ? 0 : sumsToHere[i-1]) + nums[i];
    }
  }

  public int sumRange(int i, int j) {
    assert i <= j;
    return sumsToHere[j] - (i == 0 ? 0 : sumsToHere[i-1]);
  }
}
