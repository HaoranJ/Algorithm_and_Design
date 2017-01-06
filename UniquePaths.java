import java.util.*;

public class UniquePaths {
  public int uniquePaths(int m, int n) {
    assert m > 0 && n > 0;
    int[][] dp = new int[m][n];
    dp[0][0] = 1;
    for (int row = 0; row < m; row++) {
      for (int col = 0; col < n; col++) {
        int x1 = row, y1 = col - 1;
        int x2 = row - 1, y2 = col;
        int res1 = 0, res2 = 0;
        if (0 <= x1 && x1 < m && 0 <= y1 && y1 < n) {
          res1 = dp[x1][y1];
        }
        if (0 <= x2 && x2 < m && 0 <= y2 && y2 < n) {
          res2 = dp[x2][y2];
        }
        dp[row][col] += (res1 + res2);
      }
    }
    return dp[m-1][n-1];
  }

  public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    assert obstacleGrid != null;
    int m = obstacleGrid.length;
    assert m > 0;
    int n = obstacleGrid[0].length;
    int[][] dp = new int[m][n];
    dp[0][0] = 1;
    for (int r = 0; r < m; r++) {
      for (int c = 0; c < n; c++) {
        if (obstacleGrid[r][c] == 1) {
          dp[r][c] = 0;
        } else {
          int x1 = r, y1 = c - 1;
          int x2 = r - 1, y2 = c;
          int res1 = 0, res2 = 0;
          if (0 <= x1 && x1 < m && 0 <= y1 && y1 < n) {
            res1 = dp[x1][y1];
          }
          if (0 <= x2 && x2 < m && 0 <= y2 && y2 < n) {
            res2 = dp[x2][y2];
          }
          dp[r][c] += (res1 + res2);
        }
      }
    }
    return dp[m-1][n-1];
  }
}
