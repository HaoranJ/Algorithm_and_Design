import java.util.*;

public class MinimumHeightTrees {
  /*
  LeetCode - 310, Minimum Height Trees
  Naive method, use DFS to find the height for every node as root.
  Then pick up the MHTs.
  time = O(V(V+E)) = O(n^2), space = O(n)
  */
  Map<Integer, List<Integer>> graph;
  Map<String, Integer> hts;
  public List<Integer> findMinHeightTrees(int n, int[][] edges) {
    graph = new HashMap<>();
    hts = new HashMap<>();
    for(int i = 0; i < n; i++)
      graph.put(i, new ArrayList<>());
    for(int[] e : edges) {
      int p = e[0], q = e[1];
      graph.get(p).add(q);
      graph.get(q).add(p);
    }
    /* DFS solution, time = O(V(V+E)) */
    int min = Integer.MAX_VALUE;
    for(int i = 0; i < n; i++) {
      int ht = dfs(i, i);
      if(ht == min) ans.add(i);
      else if(ht < min) { ans.clear(); ans.add(i); min = ht; }
    }
    return min;
  }

  private int dfs(int r, int pa) {
    List<Integer> list = graph.get(r);
    int ht = 0;
    for(int x : list){
      if(x != pa) {
        String key = x + "+" + r;
        if(hts.containsKey(key)) {
          int t = hts.get(x);
          ht = Math.max(ht, 1 + t);

        } else { ht = Math.max(ht, 1 + dfs(x, r)); }
      }
    }
    String key = r + "," + pa;
    hts.put(key, ht);
    return ht;
  }

  /*
  Optimal Solution!
  @@ For a graph as a valid tree, there are two MHTs at most.
  Use BFS strategy, start with all the leaves, and move one-edge forward, and
  then cut off all the previous leaves -> find out the new leaves from the cut tree
  until only one or two nodes left.
  time = O(V+E) = O(n), space = O(n)
  */
  Map<Integer, List<Integer>> graph;
  Map<String, Integer> hts;
  public List<Integer> findMinHeightTrees(int n, int[][] edges) {
    graph = new HashMap<>();
    for(int i = 0; i < n; i++)
      graph.put(i, new ArrayList<>());
    for(int[] e : edges) {
      int p = e[0], q = e[1];
      graph.get(p).add(q);
      graph.get(q).add(p);
    }
    /* straight way to get answer */
    // List<Integer> leaves = getLeaves();
    // List<Integer> ans = new ArrayList<>();
    // for (int i = 0; i < n; i++) {
    //   ans.add(i);
    // }
    // while (ans.size() > 2) {
    //   //cut leaves
    //   for (int x : leaves) {
    //     ans.remove(new Integer(x));
    //     int y = graph.get(x).get(0);
    //     graph.get(x).clear();
    //     graph.get(y).remove(new Integer(x));
    //   }
    //   leaves = getLeaves();
    // }
    // return ans;

    List<Integer> leaves = getLeaves();
    if (n == 1) {
      return  Collections.singletonList(n-1);
    }
    while (n > 2) {
      List<Integer> newLeaves = new ArrayList<>();
      for (int x : leaves) {
        int y = graph.get(x).get(0);
        graph.get(y).remove(new Integer(x));
        // 0 -- 1 -- 2, it will add 1 into newLeaves.
        if (graph.get(y).size() == 1) {
          newLeaves.add(y);
        }
      }
      n -= leaves.size();
      leaves = newLeaves;
    }
    return leaves;
  }

  private List<Integer> getLeaves() {
    List<Integer> ret = new ArrayList<>();
    for(Map.Entry<Integer, List<Integer>> e : graph.entrySet()) {
      if (e.getValue().size() == 1) {
        ret.add(e.getKey());
      }
    }
    return ret;
  }
}
