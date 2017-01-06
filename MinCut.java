import java.util.*;

public class MinCut {
  private static class Edge {
    int src, dest;
    Edge(int s, int d) {
      src = s;
      dest = d;
    }
  }

  private static class Graph {
    int V, E;
    List<Edge> edgeList = new ArrayList<>();
  }

  private static class Subset {
    int parent, rank;
    Subset(int p, int r) {
      parent = p;
      rank = r;
    }
  }

  public int kargerMinCut(Graph graph) {

  }
}
