import java.util.*;

public class MeetingRooms {
  //LeetCode 252
  public boolean canAttendMeetings(Interval[] intervals) {
    assert intervals != null;
    Arrays.sort(intervals, new SortByStart());
    for (int i = 0; i < intervals.length-1; i++) {
      if (intervals[i].end > intervals[i+1].start) {
        return false;
      }
    }
    return true;
  }

  /*
  LeetCode 253, find the min number of meeting room needed.
  Greedy, min heap, sort
  time = O(nlogn), space = O(n)
  */
  public int minMeetingRooms(Interval[] intervals) {
    //sort by start time - O(nlogn)
    Arrays.sort(intervals, (i1, i2) -> {
      if(i1.start != i2.start) { return i1.start - i2.start; }
      else { return i1.end - i2.end; }
    });

    //min heap sort by end time.
    PriorityQueue<Interval> minHeap = new PriorityQueue<>((i1, i2) -> {
      return i1.end - i2.end;
    });
    for(Interval it : intervals) {
      //greedy, for a meeting it, it's the next earliest meeting and we better put it
      //into a room with smallest end time.
      if(minHeap.isEmpty() || minHeap.peek().end > it.start) {
        minHeap.add(it);
      } else {
        minHeap.poll();
        minHeap.add(it);
      }
    }
    return minHeap.size();
  }
}
