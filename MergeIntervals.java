import java.util.*;
/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */

//LeetCode 56 Merge Intervals
//time = O(n), space = constant
public class MergeIntervals {
  public List<Interval> merge(List<Interval> intervals) {
    Collections.sort(intervals, new SortByStart());
    List<Interval> ans = new ArrayList<>();
    int i = 0;
    while (i < intervals.size()) {
    	int p = i + 1;
    	Interval preItv = intervals.get(i);
    	int tail = preItv.end;
      while (p < intervals.size() && intervals.get(p).start <= tail) {
      	Interval curItv = intervals.get(p);
      	preItv = getMergedTwo(preItv, curItv);
      	tail = preItv.end;
      	p++;
      }
      ans.add(preItv);
      i = p;
    }
    return ans;
  }

  private Interval getMergedTwo(Interval a, Interval b) {
  	return new Interval(Math.min(a.start, b.start), Math.max(a.end, b.end));
  }

  public static class SortByStart implements Comparator<Interval> {
  	@Override
  	public int compare(Interval i1, Interval i2) {
  		return i1.start - i2.start;
  	}
  }
}