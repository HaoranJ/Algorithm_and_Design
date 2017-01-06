//LeetCode - 444
//Topological Sort(Directed Graph)
//To check uniqueness of superseq, we can only use Kahn's algo instead of DFS.
public boolean sequenceReconstruction(int[] org, int[][] seqs) {
  //Compared with matrix, adjacent list would save more space to represent a graph.
  Map<Integer, Set<Integer>> graph = new HashMap<>();
  Map<Integer, Integer> indeg = new HashMap<>();
  //build a graph and indegree
  for(int[] seq : seqs) {
    for(int i = 0; i < seq.length; i++) {
      //don't forget the first and last element in every seq.
      if(i == 0) {
        int node = seq[i];
        graph.putIfAbsent(node, new HashSet<>());
        indeg.putIfAbsent(node, 0);
        continue;
      }
      int from = seq[i-1];
      int to = seq[i];
      graph.putIfAbsent(from, new HashSet<>());
      graph.putIfAbsent(to, new HashSet<>());
      indeg.putIfAbsent(from, 0);
      indeg.putIfAbsent(to, 0);
      if(!graph.get(from).contains(to)) {
        graph.get(from).add(to);
        indeg.put(to, indeg.getOrDefault(to, 0) + 1);
      }
    }
  }
  if(graph.size() != org.length) {
    return false;
  }
  Deque<Integer> queue = new ArrayDeque<>();
  for(Map.Entry<Integer, Integer> e : indeg.entrySet()) {
    if(e.getValue() == 0) {
      queue.add(e.getKey());
    }
  }
  int ptr = 0;
  //Kahn's algorithm
  while(!queue.isEmpty()) {
    if(queue.size() > 1) { return false; }
    int cur = queue.poll();
    if(ptr == org.length || org[ptr++] != cur) {
      return false;
    }
    for(int to : graph.get(cur)) {
      indeg.put(to, indeg.get(to) - 1); //remember to update indegree for 'to'.
      if(indeg.get(to) == 0) {
        queue.add(to);
      }
    }
  }
  return ptr == org.length;
}
