//LeetCode 51. N-Queens.
// The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
//
// Given an integer n, return all distinct solutions to the n-queens puzzle.
//
// Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.
//
// For example,
// There exist two distinct solutions to the 4-queens puzzle:
//
// [
//  [".Q..",  // Solution 1
//   "...Q",
//   "Q...",
//   "..Q."],
//
//  ["..Q.",  // Solution 2
//   "Q...",
//   "...Q",

//   ".Q.."]
// ]

//DFS, time = (rowNum * column * length of diagonal)
char[][] board;
List<List<String>> ans;
public List<List<String>> solveNQueens(int n) {
  board = new char[n][n];
  for(char[] row : board) {
    Arrays.fill(row, '.');
  }
  ans = new ArrayList<>();
  dfs(0, 0, 1, n);
  return ans;
}

//(row, col) is position for potential 'Q', and cur = the cur(th) queen. cur = 2, the 2nd queen to put.
private void dfs(int row, int col, int cur, int n) {
  //if cur queen is not last but row is the last, it's impossble.
  if(cur < n && row == n) { return; }
  //We have to put exact one queen in one row. So does one column..
  for(int c = col; c < n; c++) {
    if(isValid(row, c, n)) {
      board[row][c] = 'Q';
      if(cur == n) {
        ans.add(getSolution(n));
      } else {
        if(row < n - 1) {
          dfs(row+1, 0, cur+1, n);
        }
      }
      board[row][c] = '.'; //backtracking.
    }
  }
}

//time = O(rowNum + colNum + length of diagonal.)
private boolean isValid(int row, int col, int n) {
  for(int c = 0; c < n; c++) {
    if(board[row][c] == 'Q') { return false; }
  }
  for(int r = 0; r < n; r++) {
    if(board[r][col] == 'Q') { return false; }
  }
  int[][] diagonals = {{1,1}, {1,-1}, {-1,1}, {-1,-1}};
  for(int[] pair : diagonals) {
    int r = row, c = col;
    while(0 <= r && r < n && 0 <= c && c < n) {
      if(board[r][c] == 'Q') { return false; }
      r += pair[0];
      c += pair[1];
    }
  }
  return true;
}

private List<String> getSolution(int n) {
  List<String> sol = new ArrayList<>();
  for(char[] row : board) {
    sol.add(new String(row));
  }
  return sol;
}
