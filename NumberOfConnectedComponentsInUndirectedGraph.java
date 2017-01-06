//BFS with queue. time = O(V+E), space = O(V) (worst case)
public int countComponents(int n, int[][] edges) {
  List<List<Integer>> graph = new ArrayList<>();
  for (int i = 0; i < n; i++) {
    graph.add(new ArrayList<>());
  }
  for (int[] pair : edges) {
    graph.get(pair[0]).add(pair[1]);
    graph.get(pair[1]).add(pair[0]);
  }
  int count = 0;
  boolean[] visit = new boolean[n];
  Arrays.fill(visit, false);
  Deque<Integer> queue = new ArrayDeque<>();
  for (int i = 0; i < n; i++) {
    if (!visit[i]) {
      queue.push(i);
      while (!queue.isEmpty()) {
        int node = queue.removeLast();
        visit[node] = true;
        for (int neighbor : graph.get(node)) {
          if(!visit[neighbor]) { queue.push(neighbor); }
        }
      }
      count++;
    }
  }
  return count;
}

//Union Find
private int count;
private int[] id;
private int[] size;
public int countComponents(int n, int[][] edges) {
  id = new int[n];
  size = new int[n];
  count = n;
  for (int i = 0; i < n; i++) {
    id[i] = i;
    size[i] = 1;
  }

  for (int pair : edges) {
    union(pair[0], pair[1]);
  }

  return count;
}

private int find(int i) {
  while (i != id[i]) {
    //path compression
    id[i] = id[id[i]];
    i = id[i];
  }
  return i;
}

private void union(int p, int q) {
  int rootP = find(p);
  int rootQ = find(q);
  if(rootP == rootQ) { return; }
  //weighted quick union
  if (size[rootP] > size[rootQ]) {
    id[rootQ] = rootP;
    size[rootP] += size[rootQ];
  } else {
    id[rootP] = rootQ;
    size[rootQ] += size[rootP];
  }
  count--;
}
