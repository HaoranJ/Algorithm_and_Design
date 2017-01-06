import java.util.*;

public class MaxMinMatrix {
  public static void main(String[] args) {
    int[][] mat = {{5,10,4}, {7,2,6}, {3,8,9}};
    System.out.println(solve(mat));
  }
  static int maxNum = Integer.MIN_VALUE;
  public static int solve(int[][] matrix) {
    dfs(matrix, 0, 0, Integer.MAX_VALUE);
    return maxNum;
  }
  private static void dfs(int[][] matrix, int ro, int cl, int minNum) {
    int rowCount = matrix.length;
    int colCount = matrix[0].length;
    if (ro == rowCount || cl == colCount) {
      return;
    }
    minNum = Math.min(minNum, matrix[ro][cl]);
    if (ro == rowCount-1 && cl == colCount-1) {
      maxNum = Math.max(minNum, maxNum);
      return;
    }
    dfs(matrix, ro+1, cl, minNum);
    dfs(matrix, ro, cl+1, minNum);
  }
}
