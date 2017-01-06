import java.util.*;

public class ContainerWithMostWater {
  /*
  LeetCode - 11
  Like greedy, starting with the longest bottom side, then use the difference
  between two heights to give the direction to move for the two pointers.
  time = O(n), space = O(1)
  */
  public int maxArea(int[] height) {
    int len = height.length;
    int lf = 0, rg = len-1;
    int leftHt = 0, rightHt = 0, curArea = 0;
    int maxArea = Integer.MIN_VALUE;
    while(lf < rg) {
        leftHt = height[lf];
        rightHt = height[rg];
        curArea = (rg-lf) * Math.min(leftHt, rightHt);
        maxArea = curArea > maxArea ? curArea : maxArea;
        if(leftHt > rightHt) {
            rg--;
        } else {
            lf++;
        }
    }
    return maxArea;
  }
}
