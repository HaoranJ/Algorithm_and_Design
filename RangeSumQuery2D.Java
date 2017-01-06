public class NumMatrix {
  int[][] dp;
  int ROW, COL;
  public NumMatrix(int[][] matrix) {
    ROW = matrix.length;
    COL = ROW == 0 ? 0 : matrix[0].length;
    dp = new int[ROW][COL];
    for (int r = 0; r < ROW; r++) {
      for (int c = 0; c < COL; c++) {
        if(r == 0 && c == 0) { dp[r][c] = matrix[r][c]; }
        else if(r == 0) { dp[r][c] = dp[r][c-1] + matrix[r][c]; }
        else if(c == 0) { dp[r][c] = dp[r-1][c] + matrix[r][c]; }
        else { dp[r][c] = dp[r][c-1] + dp[r-1][c] + matrix[r][c] - dp[r-1][c-1]; }
      }
    }
  }

  public void update(int row, int col, int val) {
    
  }

  public int sumRegion(int row1, int col1, int row2, int col2) {
    if (row1 == 0 && col1 == 0) { return dp[row2][col2]; }
    else if(row1 == 0) { return dp[row2][col2] - dp[row2][col1-1]; }
    else if(col1 == 0) { return dp[row2][col2] - dp[row1-1][col2]; }
    else { return dp[row2][col2] - dp[row2][col1-1] - dp[row1-1][col2] + dp[row1-1][col1-1]; }
  }
}


// Your NumMatrix object will be instantiated and called as such:
// NumMatrix numMatrix = new NumMatrix(matrix);
// numMatrix.sumRegion(0, 1, 2, 3);
// numMatrix.sumRegion(1, 2, 3, 4);
