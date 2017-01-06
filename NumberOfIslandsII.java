import java.util.*;

//UnionFind
public class NumberOfIslandsII {
  static int[] id;
  static int[] size;
  static int[][] probes = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
  static int count = 0;
  static int m = 0;
  static int n = 0;
  public List<Integer> numIslands2(int m, int n, int[][] positions) {
    assert m > 0 && n > 0;
    this.count = 0;
    this.m = m;
    this.n = n;
    id = new int[m*n + 1];
    size = new int[m*n + 1];
    List<Integer> ans = new ArrayList<>();
    for (int[] pos: positions) {
      int row = pos[0];
      int col = pos[1];
      int i = addLand(row, col);
      for (int[] step : probes) {
        int r = row + step[0];
        int c = col + step[1];
        if (isValid(r, c)) {
          int adj = id[index(r, c)];
          if (adj > 0 && !connected(i, adj)) {
            unite(i, adj);
          }
        }
      }
      ans.add(count);
    }
    return ans;
  }

  private boolean isValid(int r, int c) {
    return r >= 0 && c >= 0 && r < m && c < n;
  }
  private void unite(int a, int b) {
    int i = root(a);
    int j = root(b);
    if (i == j) {
      return;
    }
    if (size[i] < size[j]) {
      id[i] = j;
      size[j] += size[i];
    } else {
      id[j] = i;
      size[i] += size[j];
    }
    count--;
    System.out.println(count);
  }

  private int root(int i) {
    while (i != id[i]) {
      id[i] = id[id[i]];
      i = id[i];
    }
    return i;
  }

  private boolean connected(int a, int b) {
    return root(a) == root(b);
  }

  private int index(int row, int col) {
    return row*n + col + 1;
  }

  private int addLand(int row, int col) {
    int idx = index(row, col);
    id[idx] = idx;
    size[idx] = 1;
    count++;
    return idx;
  }
}
