/*Leetcode - 403
*/
private Map<Integer, Boolean> dp = new HashMap<>();
public boolean canCross(int[] stones) {
  assert stones != null;
  return canCross(stone, 0, 0);
}

private boolean canCross(int[] stones, int pos, int lastJump) {
  int key = pos | (lastJump << 11);
  if (dp.containsKey(key)) {
    return dp.get(key);
  }

  for (int nxt = pos + 1; nxt < stones.length; nxt++) {
    int gap = stones[nxt] - stones[pos];
    if (gap > lastJump + 1) {
      dp.put(key, false);
      return false;
    } else if (gap <= lastJump + 1 && gap >= lastJump - 1) {
      //only need one of the three possible stones at k-1, k, k+1 away can cross.
      if (canCross(stone, nxt, gap)) {
        dp.put(key, true);
        return true;
      }
    } else {
      continue;
    }
  }
  //[0,1,3,6,7], sometimes we need smaller jump.
  return pos == stone.length - 1;
}
