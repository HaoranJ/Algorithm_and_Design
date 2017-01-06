import java.util.*;

public class SurroundedRegions {
  private static int[] id;
  private static int[] sz;
  private static int count;
  private static int ROW, COL;

  //DFS. Start with boundary 'O', use DFS or BFS to mark all connected points with 'B'
  //then go throught the board again to flip 'O' to 'X', flip 'B' to 'O'
  //time = O(MN)
  public void solve(char[][] board) {
    assert board != null;
    ROW = board.length;
    if(ROW == 0) { return; }
    COL = board[0].length;
    for (int r = 0; r < ROW; r++) {
      for (int c = 0; c < COL; c++) {
        if (r == 0 || r == ROW-1 || c == 0 || c == COL-1) {
          if (board[r][c] == 'O') {
            dfs(board, r, c);
          }
        }
      }
    }

    for (int r = 0; r < ROW; r++) {
      for (int c = 0; c < COL; c++) {
        if (board[r][c] == 'B') {
          board[r][c] = 'O';
        } else if (board[r][c] == 'O') {
          board[r][c] = 'X';
        }
      }
    }
  }

  private void dfs(char[][] board, int r, int c) {
    board[r][c] = 'B';
    if(r > 1 && board[r-1][c] == 'O') { dfs(board, r-1, c); }
    if(c > 1 && board[r][c-1] == 'O') { dfs(board, r, c-1); }
    if(r < ROW-2 && board[r+1][c] == 'O') { dfs(board, r+1, c); }
    if(c < COL-2 && board[r][c+1] == 'O') { dfs(board, r, c+1); }
  }

  //weighted union find with path compression
  //Set a dummy node for boundary, all the nodes exposed to the boundary will be connected
  //to the dummy node. Then iterate the whole board, by check if the node is connected with
  //the dummy, we can capture them.
  public void solve(char[][] board) {
    assert board != null;
    ROW = board.length;
    if(ROW == 0) { return; }
    COL = board[0].length;
    int N = ROW * COL;
    id = new int[N + 1];
    sz = new int[N + 1];
    id[N] = N;
    sz[N] = 1;
    for (int r = 0; r < ROW; r++) {
      for (int c = 0; c < COL; c++) {
        int idx = r * COL + c;
        char ch = board[r][c];
        if (ch == 'O') {
          //initialize id and size for 'O' nodes
          id[idx] = idx;
          sz[idx] = 1;
          //connect boundary 'O' to the dummy node
          if (r == 0 || r == ROW-1 || c == 0 || c == COL-1) {
            union(idx, N);
          }
          //even if a boundary 'O' has been connected to dummy, it also need to check
          //previous neighbors to connect qualified inside nodes to dummy.
          //only check the previous neighbors which is the top one and left one.
          if (r > 0 && board[r-1][c] == 'O') {
            int i = (r-1) * COL + c;
            union(idx, i);
          }
          if (c > 0 && board[r][c-1] == 'O') {
            int i = r * COL + c - 1;
            union(idx, i);
          }
        }
      }
    }

    for (int r = 0; r < ROW; r++) {
      for (int c = 0; c < COL; c++) {
        int idx = r * COL + c;
        char ch = board[r][c];
        if (ch == 'O' && !isConnected(idx, N)) {
          board[r][c] = 'X';
        }
      }
    }
  }

  private int find(int i) {
    while (i != id[i]) {
      //path compression
      id[i] = id[id[i]];
      i = id[i];
    }
    return i;
  }

  private boolean isConnected(int p, int q) {
    return find(p) == find(q);
  }

  private void union(int p, int q) {
    int rootP = find(p);
    int rootQ = find(q);
    if (rootP == rootQ) { return; }
    //weighted quick union
    if (sz[rootP] > sz[rootQ]) {
      id[rootQ] = rootP;
      sz[rootP] += sz[rootQ];
    } else {
      id[rootP] = rootQ;
      sz[rootQ] += sz[rootP];
    }
    count--;
  }
}
