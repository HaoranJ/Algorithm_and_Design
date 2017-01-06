//LeetCode - 329
//time = O(row * col)
private int row, col, n;
private boolean[][] marked;
private int[][] mem;
// private boolean[][] onStack;
public int longestIncreasingPath(int[][] matrix) {
  assert matrix != null;
  row = matrix.length;
  if(row > 0) { col = matrix[0].length; }
  marked = new boolean[row][col];
  mem = new int[row][col];
  // onStack = new boolean[row][col];
  int ans = 0;
  for (int r = 0; r < row; r++) {
    for (int c = 0; c < col; c++) {
      if (!marked[r][c]) {
        int len = dfs(matrix, r, c);
        ans = Math.max(len, ans);
      }
    }
  }
  return ans;
}

private int dfs(int[][] matrix, int r, int c) {
  if (mem[r][c] > 0) {
    return mem[r][c];
  }
  int cur = matrix[r][c];
  marked[r][c] = true;
  int len = 0;
  if (r > 0 && matrix[r-1][c] > cur) {
    len = Math.max(dfs(matrix, r-1, c), len);
  }
  if (r < row-1 && matrix[r+1][c] > cur) {
    len = Math.max(dfs(matrix, r+1, c), len);
  }
  if (c > 0 && matrix[r][c-1] > cur) {
    len = Math.max(dfs(matrix, r, c-1), len);
  }
  if (c < col-1 && matrix[r][c+1] > cur) {
    len = Math.max(dfs(matrix, r, c+1), len);
  }
  mem[r][c] = len + 1;
  return len + 1;
}
