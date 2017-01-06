import java.util.*;

public class UnionFind {
  private int[] id;
  private int[] sz;
  private int count;

  public UnionFind(int N) {
    count = N;
    id = new int[N];
    sz = new int[N];
    for (int i = 0; i < N; i++) {
      id[i] = i;
      sz[i] = 1;
    }
  }

  public int find(int i) {
    while (i != id[i]) {
      //path compression
      id[i] = id[id[i]];
      i = id[i];
    }
    return i;
  }

  public int find_standardPathCompression(int i) {
    while (i != id[i]) {
      i = id[i];
    }
    int root = i;
    while (id[i] != root) {
      int parent = id[i];
      id[i] = root;
      i = parent;
    }
    return root;
  }

  public boolean connected(int p, int q) {
    return find(p) == find(q);
  }

  public void union(int p, int q) {
    int rootP = find(p);
    int rootQ = find(q);
    if(rootP == rootQ) { return; }
    //weighted quick union
    if (sz[p] > sz[q]) {
      id[q] = rootP;
      sz[rootP] += sz[rootQ];
    } else {
      id[p] = rootQ;
      sz[rootQ] += sz[rootP];
    }
    count--;
  }
}
