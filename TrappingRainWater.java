public class TrappingRainWater {
  /*
  Compute the water by width = 1 bins from left to right, two pointers to follow left and right
  edges.
  time = O(n), space  = O(1)
  */
  public int trap(int[] height) {
    int len = height.length;
    int lIdx = 0, rIdx = len-1;
    int maxLeftHt = 0, maxRightHt = 0;
    int water = 0;
    while (lIdx < rIdx) {
      int lHt = height[lIdx], rHt = height[rIdx];
      if (lHt <= rHt) {
        if (lHt > maxLeftHt) { maxLeftHt = lHt; }
        else { water += maxLeftHt - lHt; }
        lIdx++;
      } else {
        if (rHt > maxRightHt) { maxRightHt = rHt; }
        else { water += maxRightHt - rHt; }
        rIdx--;
      }
    }
    return water;
  }
  /*Observe: if the heights is descending, no water can be trapped. When we meet the first height
  which break the descending, we compute the water. We use stack to store the heights.
  */
  public int trap(int[] heights) {
    Stack<Integer> stack = new Stack<>();
    int i = 0, water = 0;
    while(i < heights.length) {
      int ht = heights[i];
      if (stack.isEmpty() || ht <= heights[stack.peek()]) {
        stack.push(i++);
      } else {
        int bottomHt = heights[stack.pop()];
        if(stack.isEmpty()) { continue; }
        int leftEdge = heights[stack.peek()];
        int rightEdge = ht;
        int width = i - stack.peek() - 1;
        water += (Math.min(leftEdge, rightEdge) - bottomHt) * width;
      }
    }
    return water;
  }


  //compute level by level, time = O(maxHeight * n), space = O(n)
  public int trap(int[] hgts) {
    int len = hgts.length;
    int highest = getMaxVal(hgts);
    int sum = 0;
    for (int ht = 1; ht <= highest; ht++) {
      int[] level = scrapLevel(hgts, ht);
      sum += trapWaterByLevel(level);
    }
    return sum;
  }

  private int trapWaterByLevel(int[] level) {
    int water = 0, left = 0, right = 0;
    while (right < level.length) {
      while (left < level.length && level[left] == 0) {
        left++;
      }
      right = left + 1;
      while (right < level.length && level[right] == 0) {
        right++;
      }
      if (right < level.length) {
        water += right - left - 1;
      }
      left = right;
    }
    return water;
  }

  private int[] scrapLevel(int[] hgts, int ht) {
    assert ht >= 1;
    int[] level = new int[hgts.length];
    for (int i = 0; i < hgts.length; i++) {
      level[i] = hgts[i] >= ht ? 1 : 0;
    }
    return level;
  }

  private int getMaxVal(int[] hgts) {
    int ret = -1;
    for (int num : hgts) {
      if(num > ret) { ret = num; }
    }
    return ret;
  }
}
