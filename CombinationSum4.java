import java.util.*;

public class CombinationSum4 {
  /*
  LeetCode - 377. - similar to Coin Change.
  */
  //Recursion. time = Exponential
  public int combinationSum4(int[] nums, int target) {
    int ans = 0;
    for (int i = 0; i < nums.length; i++) {
      int num = nums[i];
      if (num == target) {
        ans++;
      } else if (num < target) {
        ans += combinationSum4(nums, target - num);
      }
    }
    return ans;
  }

  //DP. time = O(target * n) (worst case)
  public int combinationSum4_DP(int[] nums, int target) {
    int[] dp = new int[target+1];
    Arrays.sort(nums);
    for (int t = 1; t <= target; t++) {
      for (int num : nums) {
        if(num > t) { break; }
        else if (num < t) {
          int remain = t - num;
          dp[t] += dp[remain];
        } else if (num == t) {
          dp[t]++;
        }
      }
    }
    return dp[target];
  }

  //What if negative numbers are allowed in the given array?
  //How does it change the problem?
  //What limitation we need to add to the question to allow negative numbers?
  //{-1, 1}, target = 1, it will have infinite solutions as long as -1 is one less
  //than 1. Thus, if every num in nums can only be used once, we could have finite
  //solutions.
  //similar to 0-1 knapsack problem, can use DP.
  public int combinationSum4_NegativeNum(int[] nums, int target) {
    Arrays.sort(nums);
    return combinationSum4_NegativeNum(nums, target, 0);
  }

  private int combinationSum4_NegativeNum(int[] nums, int target, int startIdx) {
    int ans = 0;
    for (int i = startIdx; i < nums.length; i++) {
      int num = nums[i];
      if (num == target) {
        ans++;
      }
      ans += combinationSum4_NegativeNum(nums, target - num, i+1) + combinationSum4_NegativeNum(nums, target, i+1);
    }
    return ans;
  }
  public static void main(String[] args) {
    CombinationSum4 c = new CombinationSum4();
    int[] nums = {-2,-1,1,2,3,4};
    System.out.println(c.combinationSum4_NegativeNum(nums, 2));
  }
}
