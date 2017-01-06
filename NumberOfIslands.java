import java.util.*;

//LeetCode 200, Number of Islands
//use DFS or BFS to search the Islands
//time = O(rowNum * colNum), space = constant
//if we are allowed to change grid, then we can change '1' -> '0' to denote "visited"
boolean[][] visited;
int rowNum;
int colNum;
public int numIslands(char[][] grid) {
  int islands = 0;
  rowNum = grid.length;
  if(rowNum == 0) { return islands; }
  colNum = grid[0].length;
  visited = new boolean[rowNum][colNum];
  for(int r = 0; r < rowNum; r++) {
    for(int c = 0; c < colNum; c++) {
      if(grid[r][c] == '1' && !visited[r][c]) {
        dfs(grid, r, c);
        islands++;
      }
    }
  }
  return islands;
}

private void dfs(char[][] grid, int r, int c) {
  visited[r][c] = true;
  if(r > 0 && grid[r-1][c] == '1' && !visited[r-1][c]) {
    dfs(grid, r-1, c);
  }
  if(r < rowNum-1 && grid[r+1][c] == '1' && !visited[r+1][c]) {
    dfs(grid, r+1, c);
  }
  if(c > 0 && grid[r][c-1] == '1' && !visited[r][c-1]) {
    dfs(grid, r, c-1);
  }
  if(c < colNum-1 && grid[r][c+1] == '1' && !visited[r][c+1]) {
    dfs(grid, r, c+1);
  }
}
