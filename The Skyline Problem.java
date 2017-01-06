//LeetCode - 218
//https://briangordon.github.io/2014/08/the-skyline-problem.html
//Every key point is the start point of a key segment, first we need to get all the key segments.
//then for every segment we pick up the highest height using max heap.
//time = O(nlogn), space = O(n)
public List<int[]> getSkyline(int[][] buildings) {
  List<int[]> res = new ArrayList<>();
  List<int[]> edges = new ArrayList<>(); //left and right edges for every building.
  for(int[] bd : buildings) {
    edges.add(new int[] {bd[0], -bd[2]}); //negative sign for left edge of a building
    edges.add(new int[] {bd[1], bd[2]}); //positive sign for right edge of a building
  }
  //sort by position, break tie by height.
  Collections.sort(edges, (a, b) -> {
    if(a[0] != b[0]) { return a[0] - b[0]; }
    else { return a[1] - b[1]; }
  });
  int preHt = 0;
  //use max heap to store valid building heights in each key segment.
  PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> { return b - a; });
  maxHeap.add(0); //ground
  for(int i = 0; i < edges.size(); i++) {
    int[] edge = edges.get(i);
    if(edge[1] > 0) {
      maxHeap.remove(edge[1]);
    } else {
      maxHeap.add(-edge[1]);
    }
    int curHt = maxHeap.peek();
    if(curHt != preHt) {
      res.add(new int[]{edge[0], curHt});
    }
    preHt = curHt;
  }
  return res;
}
