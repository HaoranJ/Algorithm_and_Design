/* LeetCode 469 Convex Polygon
Given a list of points that form a polygon when joined sequentially, find if this polygon is convex (Convex polygon definition).

Note:

There are at least 3 and at most 10,000 points.
Coordinates are in the range -10,000 to 10,000.
You may assume the polygon formed by given points is always a simple polygon (Simple polygon definition). In other words, we ensure that exactly two edges intersect at each vertex, and that edges otherwise don't intersect each other

Example 1:

[[0,0],[0,1],[1,1],[1,0]]

Answer: True

Example 2:

[[0,0],[0,10],[10,10],[10,0],[5,5]]

Answer: False
*/

//notice flat angle that is 180 degrees
public boolean isConvex(List<List<Integer>> points) {
  int n = points.size();
  List<Integer> p1 = points.get(0), p2 = points.get(1), p3 = points.get(2);
  int flag = 0;
  for(int i = 0; i < n; i++) {
    p1 = points.get(i % n);
    p2 = points.get((i + 1) % n);
    p3 = points.get((i + 2) % n);
    res = crossPro(p1, p2, p3);
    if(flag == 0) {
      flag = res == 0 ? 0 : res;
    } else {
      if(res == 0) { continue; }
      else {
        if((res > 0) != (flag > 0)) {
          return false;
        }
      }
    }
  }
  return true;
}

private int crossPro(List<Integer> p1, List<Integer> p2, List<Integer> p3) {
  List<Integer> v1 = new ArrayList<Integer>() {{
    add(p1.get(0) - p2.get(0));
    add(p1.get(1) - p2.get(1));
  }};
  List<Integer> v2 = new ArrayList<Integer>() {{
    add(p3.get(0) - p2.get(0));
    add(p3.get(1) - p2.get(1));
  }};
  return v1.get(0) * v2.get(1) - v1.get(1) * v2.get(0);
}
