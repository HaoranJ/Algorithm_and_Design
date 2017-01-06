//LeetCode - 452
//greedy.
public int findMinArrowShots(int[][] points) {
  //sort by start point
  Arrays.sort(points, (b1, b2) -> {
    return b1[0] - b2[0];
  });
  int arrows = 0;
  int i = 0;
  //greedy, for every arrow, we try to use it to busrt as many balloons as possible.
  //So we need to check overlaps of balloons.
  while (i < points.length) {
    int[] overlap = {points[i][0], points[i][1]};
    int j = i + 1;
    while (j < points.length && points[j][0] <= overlap[1]) {
      overlap[0] = points[j][0];
      overlap[1] = Math.min(overlap[1], points[j][1]);
      j++;
    }
    arrows++;
    i = j;
  }
  return arrows;
}
