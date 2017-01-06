import java.util.*;

public class TimeIntervalConflict {
	//check conflicts of a bunch of time intervals
	//time = O(nlogn)
	private static class TimeInterval {
		private final int start;
		private final int end;

		public TimeInterval(int s, int e) {
			assert s <= e;
			start = s;
			end = e;
		}
	}

	public static boolean isConflict(List<TimeInterval> intervals) {
		assert intervals.size() > 0;
		Collections.sort(intervals, new SortByStart());
		Iterator<TimeInterval> itr = intervals.iterator();
		TimeInterval cur = itr.next();
		while (itr.hasNext()) {
			TimeInterval post = itr.next();
			if (cur.end > post.start) {
				return true;
			}
			cur = post;
		}
		return false;
	}

	private static class SortByStart implements Comparator<TimeInterval> {
		@Override
		public int compare(TimeInterval t1, TimeInterval t2) {
			return t1.start - t2.start;
		}
	}

	public static void main(String[] args) {
		TimeInterval t1 = new TimeInterval(8, 9);
		TimeInterval t2 = new TimeInterval(1, 2);
		TimeInterval t3 = new TimeInterval(2, 3);
		TimeInterval t4 = new TimeInterval(5, 7);
		List<TimeInterval> list = new ArrayList<>();
		list.add(t1);
		list.add(t2);
		list.add(t3);
		list.add(t4);
		System.out.println(isConflict(list));
	}
}