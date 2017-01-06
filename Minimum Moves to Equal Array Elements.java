//LeetCode - 453
//a move = incrementing n-1 elements by 1 = decrement 1 element by 1
//let's make all elements equal to the min of the array.
public int minMoves(int[] nums) {
  return IntStream.of(nums).sum() - nums.length * IntStream.of(nums).min().getAsInt();
}

//DP
public int minMoves(int[] nums) {
  Arrays.sort(nums);
  int[] moves = new int[nums.length];
  int[] ceilings = new int[nums.length];
  moves[0] = 0;
  ceilings[0] = nums[0];
  for (int i = 1; i < nums.length; i++) {
    ceilings[i] = nums[i] + moves[i-1];
    moves[i] = moves[i-1] + (ceilings[i] - ceilings[i-1]);
  }
  return moves[nums.length - 1];
}

//TLE
public int minMoves(int[] nums) {
  int n = nums.length;
  if (n == 1) {
    return 0;
  }
  int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
  for (int i = 0; i < n; i++) {
    int num = nums[i];
    min = num < min ? num : min;
    max = num > max ? num : max;
  }
  long diffSum = 0;
  for (int x : nums) {
    diffSum += max - x;
  }
  int step = 0;
  while (true) {
    while (diffSum % (n-1) != 0) {
      diffSum += n;
      step++;
    }
    int moves = (int)(diffSum / (n-1));
    if (max + step - min <= moves) {
      return moves;
    }
    diffSum += n;
    step++;
  }
}
