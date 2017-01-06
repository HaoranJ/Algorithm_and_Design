//LeetCode - 269, Alien Dictionary
//DFS for topological sort: time = O(V+E)
private Set<Integer>[] adj;
private boolean[] marked;
private boolean[] onStack;
private boolean[] appeared;
private StringBuilder sb;
private boolean hasCycle;

public String alienOrder(String[] words) {
  if(words.length == 1) { return words[0]; }
  //initialize
  adj = (HashSet<Integer>[]) new HashSet[26];
  for (int i = 0; i < 26; i++) {
    adj[i] = new HashSet<Integer>();
  }
  marked = new boolean[26];
  onStack = new boolean[26];
  appeared = new boolean[26];
  sb = new StringBuilder();
  //build the graph
  for (int i = 1; i < words.length; i++) {
    String pre = words[i-1], cur = words[i];
    String pair = getOrderPair(pre, cur);
    if (!pair.equals("")) {
      addEdge(pair.charAt(0), pair.charAt(1));
    }
  }

  for (int i = 0; i < 26; i++) {
    if(hasCycle) { break; }
    if (appeared[i] && !marked[i]) {
      dfs(i);
    }
  }
  return hasCycle ? "" : sb.reverse().toString();
}

private void dfs(int v) {
  onStack[v] = true;
  marked[v] = true;
  for (int w : adj[v]) {
    if (hasCycle) { return; }
    else if (onStack[w]) {
      hasCycle = true;
      return;
    } else if (!marked[w]) {
      dfs(w);
    }
  }
  onStack[v] = false;
  sb.append((char)('a' + v));
}

private String getOrderPair(String s1, String s2) {
  String pair = "";
  int i = 0;
  while (i < Math.min(s1.length(), s2.length())) {
    char ch1 = s1.charAt(i), ch2 = s2.charAt(i);
    appeared[ch1-'a'] = true;
    appeared[ch2-'a'] = true;
    if (ch1 != ch2) {
      char[] carr = {ch1, ch2};
      if(pair.equals("")) { pair = new String(carr); }
    }
    i++;
  }
  String s = s1.length() > s2.length() ? s1 : s2;
  while (i < s.length()) {
    appeared[s.charAt(i) - 'a'] = true;
    i++;
  }
  return pair;
}

private void addEdge(char v, char w) {
  int from = v - 'a', to = w - 'a';
  adj[from].add(to);
}

//BFS (Kahn's Algorithm) for topo sort
final private int N = 26;
private int[][] graph; //use matrix representation to easier check in/out degree
private int[] inDegree;

public String alienOrder(String[] words) {
  assert words != null;
  graph = new int[N][N];
  inDegree = new int[N];
  Arrays.fill(inDegree, -1);
  if(words.length == 1) { return words[0]; }
  for (int i = 0; i < words.length; i++) {
    for (char c : words[i].toCharArray()) {
      //denote appeared characters
      if(inDegree[c - 'a'] == -1) {
        inDegree[c - 'a'] = 0;
      }
    }
    if (i > 0) {
      String w1 = words[i-1], w2 = words[i];
      for (int j = 0; j < Math.min(w1.length(), w2.length()); j++) {
        char c1 = w1.charAt(j), c2 = w2.charAt(j);
        //avoid parallel edges (duplicate)
        if(c1 != c2 && graph[c1-'a'][c2-'a'] != 1) {
          inDegree[c2-'a']++;
          graph[c1-'a'][c2-'a'] = 1;
          break;
        }
      }
    }
  }
  StringBuilder sb = new StringBuilder();
    Deque<Integer> queue = new ArrayDeque<>();
    for (int i = 0; i < N; i++) {
      if(inDegree[i] == 0) { queue.push(i); }
    }
    //even for cyclic graph, it could have no-incoming degree nodes.
    while (!queue.isEmpty()) {
      int from = queue.removeLast();
      sb.append((char)('a' + from));
      for (int to = 0; to < N; to++) {
        if (graph[from][to] == 1) {
          if(--inDegree[to] == 0) { queue.push(to); }
        }
      }
    }
    boolean hasCycle = false;
    for (int in : inDegree) {
      if(in > 0) {
        hasCycle = true;
        break;
      }
    }
    return hasCycle ? "" : sb.toString();
}
