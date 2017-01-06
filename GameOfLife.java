import java.util.*;
/*
LeetCode 289, game of life
we need the initial state of every cell, and cannot update some cells based on the updated neighbors.
So we have to store the intial image of the board. a integer has 32 bits, so we can use the leftmost bit
to store initial state, and second leftmost bit to store the updated state. In this way, we can solve the problem
in place.
time = O(mn), in place.
*/
public class GameOfLife {
  public void gameOfLife(int[][] board){
    assert board != null;
    int m = board.length;
    if(m == 0) return;
    int n = board[0].length;
    for(int i = 0; i < m; i++){
      for(int j = 0; j < n; j++) {
        int x = board[i][j] & 1;
        int c = lives(i, j, m, n, board);
        if(x == 1) {
          if(c < 2 || c > 3) board[i][j] = 1;
          else board[i][j] = 3;
        } else {
          if(c == 3) board[i][j] = 2;
          else board[i][j] = 0;
        }
      }
    }

    for(int i = 0; i < m; i++){
      for(int j = 0; j < n; j++) {
        board[i][j] = board[i][j] >> 1;
      }
    }

  }

  private int lives(int i, int j, int m, int n, int[][] board) {
    int[][] ne = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1},{1, -1},{1, 0},{1, 1}};
    int c = 0;
    for(int[] d : ne) {
      int x = i + d[0], y = j + d[1];
      if(0 <= x && x < m && 0 <= y && y < n) {
        c = (board[x][y] & 1) == 1 ? c + 1 : c;
      }
    }
    return c;
  }

}
