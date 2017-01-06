/*
LeetCode - 334.
Start with increasing double seq, then for this question, we could maintain two
minimum previous number for being the first and second number in the triplet seq.
Scan the array, whenever the current num > second, then return true.
time = O(n), space = O(1).
ex. [1, 2, -100, -5, -1000, -2]
*/
public boolean increasingTriplet(int[] nums) {
  if(nums.length < 3) { return false; }
  int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE;
  for (int i = 0; i < nums.length; i++) {
    int cur = nums[i];
    if (cur > second) { return true; }
    else if (cur <= first) { first = cur; }
    else { second = cur; }
  }
  return false;
}
