//LeetCode - 435
//Exact opposite - find the maximum set of non-overlapping intervals.
// ==> activity-selection problem --> Greedy.
public int eraseOverlapIntervals(Interval[] intervals) {
  //sort by finish time
  Arrays.sort(intervals, (a, b) -> {
    return a.end - b.end;
  });
  int count = 0;
  int i = 0;
  while (i < intervals.length) {
    Interval itv = intervals[i];
    while (i + 1 < intervals.length && intervals[i+1].start < itv.end) {
      i++;
      count++;
    }
    i++;
  }
  return count;
}
