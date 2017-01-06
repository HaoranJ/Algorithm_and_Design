import java.util.*;

public class GraphValidTree {
  Set<Integer> visited;
  boolean hasCycle;
  /*
  DFS and BFS, time = O(V+E), space = O(V+E)
  */
  public boolean validTree_DFS_BFS(int n, int[][] edges) {
    assert n > 0 && edges != null;
    hasCycle = false;
    visited = new HashSet<>();
    Map<Integer, List<Integer>> map = new HashMap<>();
    for(int[] e : edges) {
      int k = e[0], v = e[1];
      if(map.containsKey(k)) map.get(k).add(v);
      else {
        List<Integer> list = new ArrayList<>();
        list.add(v);
        map.put(k, list);
      }
      if(map.containsKey(v)) map.get(v).add(k);
      else {
        List<Integer> list = new ArrayList<>();
        list.add(k);
        map.put(v, list);
      }
    }
    /* Iterative DFS */
    // Deque<Integer> stk = new ArrayDeque<>();
    // stk.push(0);
    // while (!stk.isEmpty()) {
    //   int x = stk.pop();
    //   if(visited.contains(x)) return false;
    //   visited.add(x);
    //   if(!map.containsKey(x)) continue;
    //   for (int ng : map.get(x)) {
    //     stk.push(ng);
    //     map.get(ng).remove(new Integer(x));
    //   }
    // }
    // return visited.size() == n;

    /* BFS, use queue, time = O(V+E), space = O(V+E) */
    // Deque<Integer> queue = new ArrayDeque<>();
    // queue.push(0);
    // while (!queue.isEmpty()) {
    //   int x = queue.removeLast();
    //   if(visited.contains(x)) return false;
    //   visited.add(x);
    //   if(!map.containsKey(x)) continue;
    //   for (int y : map.get(x)) {
    //     if (map.containsKey(y)) {
    //       map.get(y).remove(new Integer(x));
    //     }
    //     queue.push(y);
    //   }
    // }
    dfs(map, 0, 0);
    return hasCycle ? false : visited.size() == n;
  }

  /*
  don't manipulate on map when do recursion. Otherwise, it tends to throw
  ConcurrentModificationException.
  */
  private void dfs(Map<Integer, List<Integer>> map, int x, int pa) {
    if(visited.contains(x)) { hasCycle = true; return; }
    visited.add(x);
    if(!map.containsKey(x)) return;
    List<Integer> list = map.get(x);
    for(int y : list) {
      if(hasCycle) return;
      //pa is parent, we skip the parent neighbor because it's undirected graph.
      if(y != pa) dfs(map, y, x);
    }
  }

  /*
  Union-Find method.
  Starting from an empty data structure, any sequence
  of M union and find operations on N objects takes O(N + M lg* N) time.
  lg* -- number of times needed to take the lg of a number until reaching 1
  */
  int[] id;
  int[] sz;
  int trees;
  public boolean validTree_UnionFind(int n, int[][] edges) {
    assert n >= 1 && edges != null;
    id = new int[n];
    sz = new int[n];
    trees = n;
    for (int i = 0; i < n; i++) {
      id[i] = i;
      sz[i] = 1;
    }
    for (int[] e: edges) {
      int a = e[0], b = e[1];
      if (find(a, b)) {
        return false;
      }
      unite(a, b);
    }
    return trees == 1;
  }

  private boolean find(int a, int b) {
    return root(a) == root(b);
  }

  private int root(int x) {
    while (id[x] != x) {
      //path compression
      id[x] = id[id[x]];
      x = id[x];
    }
    return x;
  }

  private void unite(int a, int b) {
    int ar = root(a), br = root(b);
    if(ar == br) { return; }
    //weighted quick-union.
    if (sz[ar] > sz[br]) {
      id[br] = ar;
      sz[ar] += sz[br];
    } else {
      id[ar] = br;
      sz[br] += sz[ar];
    }
    trees--;
  }
}
