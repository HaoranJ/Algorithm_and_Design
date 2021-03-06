public class Solution {
  /**Leetcode - 398
  Suppose we see a sequence of items, one at a time. We want to keep a single item in memory,
  and we want it to be selected at random from the sequence. If we know the total number of items (n),
  then the solution is easy: select an index i between 1 and n with equal probability, and keep the i-th element.
  The problem is that we do not always know n in advance. A possible solution is the following:
  Keep the first item in memory.
  When the i-th item arrives (for i>1):
  with probability 1/i, keep the new item instead of the current item; or equivalently
  with probability 1 - 1/i, keep the current item and discard the new item.
  So:
  when there is only one item, it is kept with probability 1;
  when there are 2 items, each of them is kept with probability 1/2;
  when there are 3 items, the third item is kept with probability 1/3, and each of the previous 2 items
  is also kept with probability (1/2)(1-1/3) = (1/2)(2/3) = 1/3;
  by induction, it is easy to prove that when there are n items, each item is kept with probability 1/n.
  */
  private int[] nums;
  private Random random;
  public Solution(int[] nums) {
    this.nums = nums;
    random = new Random();
  }

  public int pick(int target) {
    int result = -1;
    int count = 0;
    for (int i = 0; i < nums.length; i++) {
      int n = nums[i];
      if (n == target) {
        count++;
        if (random.nextInt(count) == 0) {
          result = i;
        }
      }
    }
    return result;
  }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */
