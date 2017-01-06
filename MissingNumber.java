/*
xor: b^a^a = b
for n distinct numbers and the indexes 0,1,....n-1 plus n
we "xor" through them, the left one is the missing number.
*/
public int missingNumber(int[] nums) {
  int res = nums.length;
  for (int i = 0; i < nums.length; i++) {
    res ^= i;
    res ^= nums[i];
  }
  return res;
}
