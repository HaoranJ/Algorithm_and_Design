/*
LeetCode - 286.
Use BFS, don't start from INF but the SOURCE!! - gate.
time = O(mn)
*/
public void wallsAndGates(int[][] rooms) {
  assert rooms != null;
  int rowNum = rooms.length;
  int colNum = 0;
  if(rowNum > 0) { colNum = rooms[0].length; }
  Deque<Pair> queue = new ArrayDeque<>();
  if(rowNum > 0) { colNum = rooms[0].length; }
  for (int r = 0; r < rowNum; r++) {
    for (int c = 0; c < colNum; c++) {
      int num = rooms[r][c];
      if(num == 0) {
        queue.push(new Pair(r, c));
      }
    }
  }
  while (!queue.isEmpty()) {
    int curSize = queue.size(); //key! to keep track the initial queue size.
    for (int i = 0; i < curSize; i++) {
      Pair pair = queue.removeLast();
      int r = pair.ro, c = pair.cl;
      int curDis = rooms[r][c];
      //check == Integer.MAX_VALUE means to check visited.
      if(r > 0 && rooms[r-1][c] == Integer.MAX_VALUE) {
        rooms[r-1][c] = curDis + 1; //since we use the value of every room to keep track of visited, we need update the value ASAP.
        queue.push(new Pair(r-1, c));
      }
      if(r < rowNum-1 && rooms[r+1][c] == Integer.MAX_VALUE) {
        rooms[r+1][c] = curDis + 1;
        queue.push(new Pair(r+1, c));
      }
      if(c > 0 && rooms[r][c-1] == Integer.MAX_VALUE) {
        rooms[r][c-1] = curDis + 1;
        queue.push(new Pair(r, c-1));
      }
      if(c < colNum-1 && rooms[r][c+1] == Integer.MAX_VALUE) {
        rooms[r][c+1] = curDis + 1;
        queue.push(new Pair(r, c+1));
      }
    }
  }
}

private class Pair {
  int ro;
  int cl;
  Pair(int ro, int cl) {
    this.ro = ro;
    this.cl = cl;
  }
}
