import java.util.*;

public class InsertInterval{
	public static void main(String[] args) {
		ArrayList<Interval> intervals = new ArrayList<>();
		intervals.add(new Interval(1100,8000));
		//intervals.add(new Interval(6,8));
		//intervals.add(new Interval(12,15));
		display(conciseInsert(intervals, new Interval(5,6)));

	}

	public static void display(List<Interval> intervals){
		for (Interval i : intervals ) {
			System.out.println(i);
		}
	}

	//a more concise in-place solution
	public static List<Interval> conciseInsert(List<Interval> intervals, Interval newInterval){
		int j = 0;
		while (j < intervals.size()) {
			int js = intervals.get(j).start, je = intervals.get(j).end;
			if (je < newInterval.start) {
				j++;
			}else if(newInterval.end < intervals.get(j).start){
				intervals.add(j, newInterval);
				newInterval = null;
				break;
			}else{
				newInterval.start = Math.min(intervals.get(j).start, newInterval.start);
				newInterval.end = Math.max(intervals.get(j).end, newInterval.end);
				intervals.remove(j);
			}
		}
		if (newInterval != null) {
			intervals.add(newInterval);
		}
		return intervals;
	}

	//insert the interval in place
	public static List<Interval> insert(List<Interval> intervals, Interval newInterval){
		int s = newInterval.start, e = newInterval.end;
		if(intervals.size() == 0){
			intervals.add(newInterval);
			return intervals;
		}
		for (int j = 0; j < intervals.size(); j++ ) {
			int js = intervals.get(j).start, je = intervals.get(j).end;
			if (s < js) {
				if (e < js) { intervals.add(j, new Interval(s, e)); break; }
				else if(e >= js && e <= je) { intervals.set(j, new Interval(s, je)); break; }
				else{
					intervals.remove(j);
					while(intervals.size() > j){
						if(intervals.get(j).end >= e){
							if (intervals.get(j).start > e) { intervals.add(j, new Interval(s, e)); break; }
							else { intervals.set(j, new Interval(s, intervals.get(j).end)); break; }
						}else{
							intervals.remove(j);
						}
					}
					if (intervals.size() == j) {
						intervals.add(j, new Interval(s, e));
					}else{ break; }
				}
			}else if (s >= js && s <= je) {
				if (e <= intervals.get(j).end) { break; }
				else if(intervals.size() == j+1 || e < intervals.get(j+1).start){
					intervals.set(j, new Interval(intervals.get(j).start, e));
					break;
				}else{
					intervals.remove(j);
					while(intervals.size() > j){
						if(intervals.get(j).end >= e){
							if (intervals.get(j).start > e) { intervals.add(j, new Interval(js, e)); break; }
							else { intervals.set(j, new Interval(js, intervals.get(j).end)); break; }
						}else{
							intervals.remove(j);
						}
					}
					if (intervals.size() == j) {
						intervals.add(j, new Interval(js, e));
						break;
					}else{ break; }
				}
			}else if(intervals.size() == j+1){
				intervals.add(newInterval);
			}
		}
		return intervals;
	}
}

class Interval{
	int start, end;
	public Interval(){ start = 0; end = 0; }
	public Interval(int s, int e){
		start = s; end = e;
	}

	public String toString(){
		return "[" + this.start + "," + this.end + "]";
	}
}