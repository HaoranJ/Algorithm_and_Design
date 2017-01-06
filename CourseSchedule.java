import java.util.*;

public class CourseSchedule {
  /*
  LeetCode 207 Course Schedule.
  */
  /*
  DFS to find whether the directed graph is acyclic.
  time = O(V+E), space = O(V+E)
  */
  Map<Integer, List<Integer>> graph;
  boolean[] visited;
  public boolean canFinish_DFS(int numCourses, int[][] prerequisites) {
    graph = new HashMap<>();
    for (int i = 0; i < numCourses; i++) {
      graph.put(i, new ArrayList<>());
    }
    for (int[] pair : prerequisites) {
      int key = pair[1], num = pair[0];
      List<Integer> list = graph.get(key);
      if(!list.contains(num)) { graph.get(key).add(num); }
    }
    visited = new boolean[numCourses];
    boolean[] path = new boolean[numCourses];
    for (int i = 0; i < numCourses; i++) {
      if (!visited[i] && graph.get(i).size() > 0) {
        Arrays.fill(path, false);
        boolean res = dfs(i, path);
        if(!res) { return false; }
      }
    }
    return true;
  }

  private boolean dfs(int i, boolean[] path) {
    visited[i] = true;
    if (path[i]) { return false; }
    path[i] = true;
    for (int x : graph.get(i)) {
      boolean res = dfs(x, path);
      if(!res) {
        path[i] = false;
        return false;
      }
    }
    path[i] = false;
    return true;
  }

  /*
  Kahn's algorithm based on BFS, use adjacent list to represent graph.
  time = O(V+E), space = O(V+E)
  */
  public boolean canFinish_BFS(int numCourses, int[][] prerequisites) {
    Map<Integer, List<Integer>> graph = new HashMap<>();
    for (int i = 0; i < numCourses; i++) {
      graph.put(i, new ArrayList<>());
    }
    for (int pair[] : prerequisites) {
      int key = pair[1], num = pair[0];
      List<Integer> list = graph.get(key);
      if(!list.contains(num)) { graph.get(key).add(num); }
    }
    Deque<Integer> sinkNodes = new ArrayDeque<>();
    Iterator<Map.Entry<Integer, List<Integer>>> it = graph.entrySet().iterator();
    while (it.hasNext()) {
      Map.Entry<Integer, List<Integer>> e = it.next();
      if (e.getValue().isEmpty()) {
        sinkNodes.push(e.getKey());
        it.remove();
      }
    }
    while (!sinkNodes.isEmpty()) {
      int node = sinkNodes.pop();
      Iterator<Map.Entry<Integer, List<Integer>>> itr = graph.entrySet().iterator();
      while (itr.hasNext()) {
        Map.Entry<Integer, List<Integer>> e = itr.next();
        List<Integer> list = e.getValue();
        if (list.contains(node)) {
          List<Integer> temp = new ArrayList<>(list);
          temp.remove(new Integer(node));
          e.setValue(temp);
          if(temp.isEmpty()) {
            sinkNodes.push(e.getKey());
            itr.remove();
          }
        }
      }
    }
    return graph.size() == 0;
  }

  /*
  Kahn's algorithm based on BFS, use matrix which is faster than adjacent list to represent graph.
  time = O(V+E), space = O(V+E)
  */
  public boolean canFinish(int numCourses, int[][] prerequisites) {
    int[][] matrix = new int[numCourses][numCourses];
    int[] indegree = new int[numCourses];
    for (int[] pair : prerequisites) {
      int from = pair[1], to = pair[0];
      if (matrix[from][to] == 0) {
        indegree[to]++;
      }
      matrix[from][to] = 1;
    }
    Deque<Integer> stack = new ArrayDeque<>();
    for (int i = 0; i < numCourses; i++) {
      if(indegree[i] == 0) { stack.push(i); }
    }
    int count = 0;
    while (!stack.isEmpty()) {
      int num = stack.pop();
      count++;
      for (int j = 0; j < numCourses; j++) {
        if(matrix[num][j] == 1) {
          indegree[j]--;
          if(indegree[j] == 0) { stack.push(j); }
        }
      }
    }
    return count == numCourses;
  }

  //LeetCode - 210, Course Schedule II
  //use matrix to represent graph, Kahn's algorithm
  //time = O(V+E), space = O(V)
  public int[] findOrder(int numCourses, int[][] prerequisites) {
    int[][] graph = new int[numCourses][numCourses];
    int[] indegree = new int[numCourses];
    Arrays.fill(indegree, 0);
    for (int[] pair : prerequisites) {
      int from = pair[1], to = pair[0];
      if (graph[from][to] == 0) {
        graph[from][to] = 1;
        indegree[to]++;
      }
    }
    int[] order = new int[numCourses];
    int ptr = 0;
    Deque<Integer> queue = new ArrayDeque<>();
    for (int i = 0; i < numCourses; i++) {
      int course = indegree[i];
      if(course == 0) { queue.add(i); }
    }
    while (!queue.isEmpty()) {
      int course = queue.poll();
      order[ptr++] = course;
      for (int to = 0; to < numCourses; to++) {
        if (graph[course][to] == 1) {
          indegree[to]--;
          if(indegree[to] == 0) {
            queue.add(to);
          }
        }
      }
    }
    return ptr == numCourses ? order : new int[0];
  }

  //Use depth-first search
  int[] order;
  int ptr;
  boolean[] visited;
  Map<Integer, List<Integer>> graph;
  public int[] findOrder(int numCourses, int[][] prerequisites) {
    order = new int[numCourses];
    ptr = numCourses-1;
    graph = new HashMap<>();
    for (int i = 0; i < numCourses; i++) {
      graph.put(i, new ArrayList<Integer>());
    }
    for (int[] pair : prerequisites) {
      int from = pair[1], to = pair[0];
      if (!graph.get(from).contains(to)) {
        graph.get(from).add(to);
      }
    }
    visited = new boolean[numCourses];
    boolean[] path = new boolean[numCourses];
    for (int i = 0; i < numCourses; i++) {
      if (!visited[i]) {
        Arrays.fill(path, false);
        boolean acyclic = isAcyclicFindOrder(i, path);
        if(!acyclic) { return new int[0]; }
      }
    }
    return order;
  }

  private boolean isAcyclicFindOrder(int from, boolean[] path) {
    List<Integer> nextCourses = graph.get(from);
    if (path[from]) {
      //not acyclic
      return false;
    } else {
      path[from] = true;
      for (int course : nextCourses) {
        boolean acyclic = isAcyclicFindOrder(course, path);
        if(!acyclic) {
          //clear footprint
          path[from] = false;
          return false;
        }
      }
      if (!visited[from]) {
        order[ptr--] = from;
        visited[from] = true;
      }
      //clear footprint
      path[from] = false;
      return true;
    }
  }
}
