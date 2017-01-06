/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
 //LeetCode - 436
//TreeMap - time = O(nlogn)
public int[] findRightInterval(Interval[] intervals) {
 int[] ans = new int[intervals.length];
 TreeMap<Integer, Integer> trMap = new TreeMap<>();
 for(int i = 0; i < intervals.length; i++) {
   trMap.put(intervals[i].start, i);
 }
 for(int i = 0; i < intervals.length; i++) {
   Map.Entry<Integer, Integer> entry = trMap.ceilingEntry(intervals[i].end); //if only use Map<> to declare trMap,
   //we can't find symbol ceilingEntry(int).
   ans[i] = entry == null ? -1 : entry.getValue();
 }
 return ans;
}
 //time = O(nlogn)
public int[] findRightInterval(Interval[] intervals) {
  int[] ans = new int[intervals.length];
  Arrays.fill(ans, -1);
  Map<Integer, Integer> map = new HashMap<>();
  //mapping start time -> idx
  for (int i = 0; i < intervals.length; i++) {
    map.put(intervals[i].start, i);
  }
  //sort by start time.
  Arrays.sort(intervals, (a, b) -> {
    return a.start - b.start;
  });
  int i = 0;
  while (i < intervals.length) {
    Interval itv = intervals[i];
    //Binary search - O(logn)
    Interval rtItv = binarySearch(intervals, i+1, intervals.length-1, itv.end);
    ans[map.get(itv.start)] = rtItv == null ? -1 : map.get(rtItv.start);
    i++;
  }
  return ans;
}

private Interval binarySearch(Interval[] intervals, int lo, int hi, int end) {
  //base case
  if (lo > hi) {
    return null;
  }
  if (lo == hi) {
    return intervals[lo].start >= end ? intervals[lo] : null;
  }
  int mid = lo + (hi - lo) / 2;
  if (intervals[mid].start == end) {
    return intervals[mid];
  } else if (intervals[mid].start > end) {
    return binarySearch(intervals, lo, mid, end);
  } else {
    return binarySearch(intervals, mid+1, hi, end);
  }
}
