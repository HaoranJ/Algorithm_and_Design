public class DFS_BFS {
  private boolean[] marked;
  private int[] edgeTo;
  private int[] distTo;
  private final int s;

  public DFS_BFS(Graph G, int s) {
    this.s = s;
    edgeTo = new int[G.V()];
    marked = new int[G.V()];
    dfs(G, s);
  }

  private void dfs(Graph G, int v) {
    marked[v] = true;
    for (int w : G.adj(v)) {
      if (!marked[w]) {
        edgeTo[w] = v;
        dfs(G, w);
      }
    }
  }

  private void bfs(Graph G, int v) {
    Deque<Integer> queue = new ArrayDeque<>();
    distTo[v] = 0;
    marked[v] = true;
    queue.push(v);

    while (!queue.isEmpty()) {
      int v = queue.removeLast();
      for (int w : G.adj(v)) {
        if (!marked[w]) {
          queue.push(w);
          edgeTo[w] = v;
          distTo[w] = distTo[v] + 1;
          marked[w] = true;
        }
      }
    }
  }

  public boolean hasPathTo(int v) {
    return marked[v];
  }

  public Iterable<Integer> pathTo(int v) {
    if (!hasPathTo[v]) { return null; }
    Stack<Integer> path = new Stack<>();
    for (int x = v; x != s; x = edgeTo[x]) {
      path.push(x);
    }
    path.push(s);
    return path;
  }
}
