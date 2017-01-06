import java.util.*;

/*
LeetCode 256, Paint House
The idea is similar to the problem Paint House I, for each house and each color, the minimum cost of painting the house with that color should be the minimum cost of painting previous houses, and make sure the previous house doesn't paint with the same color.

We can use min1 and min2 to track the indices of the 1st and 2nd smallest cost till previous house, if the current color's index is same as min1, then we have to go with min2, otherwise we can safely go with min1.

The code below modifies the value of costs[][] so we don't need extra space.
DP
*/
public class PaintHouse {
  public int minCost(int[][] costs) {
    assert costs != null;
    int n = costs.length;
    if(n == 0) { return 0; }
    int[][] mat = new int[n][3];
    for (int i = 0; i < n; i++) {
      if (i == 0) {
        mat[i][0] = costs[i][0];
        mat[i][1] = costs[i][1];
        mat[i][2] = costs[i][2];
        continue;
      }
      mat[i][0] = Math.min(mat[i-1][1]+costs[i][0], mat[i-1][2]+costs[i][0]);
      mat[i][1] = Math.min(mat[i-1][0]+costs[i][1], mat[i-1][2]+costs[i][1]);
      mat[i][2] = Math.min(mat[i-1][0]+costs[i][2], mat[i-1][1]+costs[i][2]);
    }
    return Math.min(Math.min(mat[n-1][0], mat[n-1][1]), mat[n-1][2]);
  }

  /*
  LeetCode 265, Paint House II
  time = O(nk), space = O(k)
  */
  public int minCostII(int[][] costs) {
    assert costs != null;
    int n = costs.length;
    if (n == 0) {
      return 0;
    }
    int k = costs[0].length;
    if (k == 0) {
      return 0;
    }
    //color[x] means the min cost when the last house with color x
    int[] color = new int[k];
    Arrays.fill(color, 0);
    int min = 0;
    int secMin = 0;
    for (int i = 0; i < n; i++) {
      int minOld = min;
      int secMinOld = secMin;
      min = Integer.MAX_VALUE;
      secMin = Integer.MAX_VALUE;
      for (int j = 0; j < k; j++) {
        //if the min cost of the first i houses needs the house (i-1) with color j,
        //we have to choose the second min cost of the first i houses.
        if (color[j] == minOld && minOld < secMin) {
          color[j] = secMinOld + costs[i][j];
        } else {
          color[j] = minOld + costs[i][j];
        }
        int curCost = color[j];
        if (curCost < min) {
          secMin = min;
          min = curCost;
        } else if (curCost < secMin) {
          secMin = curCost;
        }
      }
    }
    return min;
  }

  //time = O(nk), space = constant
  public int minCostII_ConstantSpace(int[][] costs) {
    assert costs != null;
    int n = costs.length;
    if (n == 0) {
      return 0;
    }
    int k = costs[0].length;
    if (k == 0) {
      return 0;
    }
    int minCost = 0;
    int secMinCost = 0;
    int minColor = -1;
    int secMinColor = -1;
    for (int i = 0; i < n; i++) {
      int minCostOld = minCost;
      int secMinCostOld = secMinCost;
      int minColorOld = minColor;
      int secMinColorOld = secMinColor;
      minCost = Integer.MAX_VALUE;
      secMinCost = Integer.MAX_VALUE;
      for (int j = 0; j < k; j++) {
        int curCost = 0;
        if (j == minColorOld && minCostOld < secMinCostOld) {
          curCost = costs[i][j] + secMinCostOld;
        } else {
          curCost = costs[i][j] + minCostOld;
        }
        if (curCost < minCost) {
          secMinCost = minCost;
          secMinColor = minColor;
          minCost = curCost;
          minColor = j;
        } else if (curCost < secMinCost) {
          secMinCost = curCost;
          secMinColor = j;
        }
      }
    }
    return minCost;
  }
}
