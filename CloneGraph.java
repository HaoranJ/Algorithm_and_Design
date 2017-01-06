import java.util.*;
/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class CloneGraph {
  /*
  LeetCode - 133.
  Depth First Search - time = O(V+E), great practice - Don't let return be void for easier unit test.
  */
  private static Map<Integer, UndirectedGraphNode> map;
  public static UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
    if(node == null) { return null; }
    map = new HashMap<>();
    return cloneNode(node);
  }

  private static UndirectedGraphNode cloneNode(UndirectedGraphNode origin) {
    //detect cycles
    if (map.containsKey(origin.label)) {
      return map.get(origin.label);
    }
    UndirectedGraphNode copy = new UndirectedGraphNode(origin.label);
    map.put(copy.label, copy);
    for (UndirectedGraphNode neighbor : origin.neighbors) {
      copy.neighbors.add(cloneNode(neighbor));
    }
    return copy;
  }

  /*
  Breadth First Search - time = O(V+E)
  */
  public UndirectedGraphNode cloneGraph_BFS(UndirectedGraphNode node) {
    if(node == null) { return null; }
    Deque<UndirectedGraphNode> queue = new ArrayDeque<>();
    Map<UndirectedGraphNode, UndirectedGraphNode> nodeMap = new HashMap<>();
    UndirectedGraphNode copyNode = new UndirectedGraphNode(node.label);
    nodeMap.put(node, copyNode);
    queue.add(node);
    while(!queue.isEmpty()) {
      UndirectedGraphNode orig = queue.poll();
      UndirectedGraphNode copy = nodeMap.get(orig);
      for(UndirectedGraphNode neighbor : orig.neighbors) {
        if (nodeMap.containsKey(neighbor)) {
          copy.neighbors.add(nodeMap.get(neighbor));
        } else {
          UndirectedGraphNode copyNgb = new UndirectedGraphNode(neighbor.label);
          copy.neighbors.add(copyNgb);
          nodeMap.put(neighbor, copyNgb);
          queue.add(neighbor);
        }
      }
    }
    return copyNode;
  }
}
