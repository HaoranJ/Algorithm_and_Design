/* LeetCode 361 - Bomb Enemy
Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero), return the maximum enemies you can kill using one bomb.
The bomb kills all the enemies in the same row and column from the planted point until it hits the wall since the wall is too strong to be destroyed.
Note that you can only put the bomb at an empty cell.

Example:
For the given grid

0 E 0 0
E 0 W E
0 E 0 0

return 3. (Placing a bomb at (1,1) kills 3 enemies)
*/
public static class Wall {
  int wall1;
  int wall2;
}

public static class Sum {
  int rowSum;
  int colSum;
}

/* 1.
Use DP to memorize the left/right/up/down walls for '0', and calculate the total amount = kill in row + kill in col.
space = O(rowNum * colNum), space = O(rowNum * colNum)
*/
public int maxKilledEnemies(char[][] grid) {
  int rowNum = grid.length;
  if (rowNum == 0) {
    return 0;
  }
  int colNum = grid[0].length;
  Wall[][] rowWalls = new Wall[rowNum][colNum];
  Wall[][] colWalls = new Wall[rowNum][colNum];
  Sum[][] enemySums = new Sum[rowNum + 1][colNum + 1];
  for (int r = 0; r < rowNum; r++) {
    int enemyCount = 0, lo = -1, hi = 0;
    while (hi < colNum) {
      while (hi < colNum && grid[r][hi] != 'W') {
        if(grid[r][hi] == 'E') { enemyCount++; }
        hi++;
      }
      enemySums[r][hi].rowSum = enemyCount;
      for (int i = lo + 1; i < hi; i++) {
        if (grid[r][i] == '0') {
          rowWalls[r][i].wall1 = lo;
          rowWalls[r][i].wall2 = hi;
        }
      }
      lo = hi;
      hi++;
    }
  }

  for (int c = 0; c < colNum; c++) {
    int enemyCount = 0, lo = -1, hi = 0;
    while (hi < rowNum) {
      while (hi < rowNum && grid[hi][c] != 'W') {
        if(grid[hi][c] == 'E') { enemyCount++; }
        hi++;
      }
      enemySums[hi][c].colSum = enemyCount;
      for (int i = lo + 1; i < hi; i++) {
        if (grid[i][c] == '0') {
          colWalls[i][c].wall1 = lo;
          colWalls[i][c].wall2 = hi;
        }
      }
      lo = hi;
      hi++;
    }
  }

  int ans = -1;
  for (int r = 0; r < rowNum; r++) {
    for (int c = 0; c < colNum; c++) {
      if (grid[r][c] == '0') {
        int leftWall = rowWalls[r][c].wall1;
        int rightWall = rowWalls[r][c].wall2;
        int upWall = colWalls[r][c].wall1;
        int downWall = colWalls[r][c].wall2;
        int rowKill = enemySums[r][rightWall].rowSum - (leftWall == -1 ? 0 : enemySums[r][leftWall].rowSum);
        int colKill = enemySums[r][downWall].colSum - (upWall == -1 ? 0 : enemySums[r][upWall].colSum);
        ans = Math.max(ans, rowKill + colKill);
      }
    }
  }
  return ans;
}

/*2.
Observe: for every planted point '0', we only need the number of enemies to kill between left / right walls and
up/down walls.
time = O(rowNum * colNum), space = O(colNum)
*/
public int maxKilledEnemies(char[][] grid) {
  int rowNum = grid.length;
  int colNum = rowNum == 0 ? 0 : grid[0].length;
  int ans = 0, rowKill = 0;
  int[] colKills = new int[colNum];
  for (int r = 0; r < rowNum; r++) {
    for (int c = 0; c < colNum; c++) {
      if (c == 0 || grid[r][c-1] == 'W') {
        rowKill = 0;
        for (int i = c; i < colNum && grid[r][i] != 'W'; i++) {
          if (grid[r][i] == 'E') { rowKill++; }
        }
      }

      if (r == 0 || grid[r-1][c] == 'W') {
        colKills[c] = 0;
        for (int i = r; i < rowNum && grid[i][c] != 'W'; i++) {
          if (grid[i][c] == 'E') { colKills[c]++; }
        }
      }

      if (grid[r][c] == '0') {
        ans = Math.max(ans, rowKill + colKills[c]);
      }
    }
  }
  return ans;
}
