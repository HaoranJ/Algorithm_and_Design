/*LeetCode 473 - Matchsticks to square
Remember the story of Little Match Girl? By now, you know exactly what matchsticks the little match girl has, please find out a way you can make one square by using up all those matchsticks. You should not break any stick, but you can link them up, and each matchstick must be used exactly one time.

Your input will be several matchsticks the girl has, represented with their stick length. Your output will either be true or false, to represent whether you could make one square using all the matchsticks the little match girl has.

Example 1:
Input: [1,1,2,2,2]
Output: true

Explanation: You can form a square with length 2, one side of the square came two sticks with length 1.
Example 2:
Input: [3,3,3,3,4]
Output: false

Explanation: You cannot find a way to form a square with all the matchsticks.
Note:
The length sum of the given matchsticks is in the range of 0 to 10^9.
The length of the given matchstick array will not exceed 15.
*/

/* DFS.
n = nums.length, time in worst case = O(4^n)
*/
public boolean makesquare(int[] nums) {
  int n = nums.length;
  if(n < 4) { return false; }
  //sort nums by descending order for a quicker DFS
  Arrays.sort(nums);
  nums = IntStream.of(nums).boxed().sorted(Collections.reverseOrder()).mapToInt(i -> i).toArray();

  int sum = Arrays.stream(nums).sum();
  //cut off redundancy DFS branches.
  if (sum % 4 != 0 || nums[0] > sum / 4) {
    return false;
  }
  int[] sides = new int[4];
  return dfs(nums, 0, sides, sum / 4);
}

private boolean dfs(int[] nums, int startIdx, int[] sides, int target) {
  //base case
  if (startIdx == nums.length) {
    return sides[0] == sides[1] && sides[1] == sides[2] && sides[2] == sides[3] && sides[3] == target;
  }

  //recursive step
  int stick = nums[startIdx];
  for (int i = 0; i < 4; i++) {
    //if one of the side is already larger than the target, skip.
    if (sides[i] + stick > target) {
      continue;
    }
    sides[i] += stick;
    boolean res = dfs(nums, startIdx + 1, sides, target);
    if (res) {
      return true;
    }
    sides[i] -= stick;
  }
  return false;
}

/* Partition Problem - https://en.wikipedia.org/wiki/Partition_problem
DP, time = O(n * sum^3), space = O(n * sum^3)
Will have memory limit exceed.
*/
public boolean makesquare(int[] nums) {
  int n = nums.length;
  if(n < 4) { return false; }
  int sum = Arrays.stream(nums).sum();
  int maxStick = Arrays.stream(nums).max().getAsInt();
  if (sum % 4 != 0 || maxStick > sum / 4) {
    return false;
  }
  int side = sum / 4;
  boolean[][][][] dp = new boolean[n+1][side + 1][side + 1][side + 1];

  for (int i = 0; i <= n; i++) {
    dp[i][0][0][0] = true;
  }

  for (int i = 1; i <= n; i++) {
    for (int s1 = 0; s1 <= side; s1++) {
      for (int s2 = 0; s2 <= side; s2++) {
        for (int s3 = 0; s3 <= side; s3++) {
          boolean res = dp[i-1][s1][s2][s3];
          int stick = nums[i-1];
          if (s1 >= stick) { res = res || dp[i-1][s1 - stick][s2][s3]; }
          if (s2 >= stick) { res = res || dp[i-1][s1][s2 - stick][s3]; }
          if (s3 >= stick) { res = res || dp[i-1][s1][s2][s3 - stick]; }
          dp[i][s1][s2][s3] = res;
        }
      }
    }
  }
  return dp[n][side][side][side];
}
