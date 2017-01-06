import java.util.*;

public class ZigzagIterator {
		private List<Integer> v1;
		private List<Integer> v2;
		private int p1;
		private int p2;
		private int flag;

    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
      this.v1 = v1;
      this.v2 = v2;
      p1 = 0;
      p2 = 0;
      flag = 1;
    }

    public int next() {
      if (flag == 1) {
      	flag = 2;
      	if (p1 < v1.size()) {
      		return v1.get(p1++);
      	} else {
      		return next();
      	}
      } else {
      	flag = 1;
      	if (p2 < v2.size()) {
      		return v2.get(p2++);
      	} else {
      		return next();
      	}
      }
    }

    public boolean hasNext() {
      return !(p1 == v1.size() && p2 == v2.size());
    }

    public static void main(String[] args) {
    	List<Integer> v1 = new ArrayList<>();
    	List<Integer> v2 = new ArrayList<>();
    	// v1.add(1); v1.add(3);
    	// v2.add(2); v2.add(4); v2.add(5); v2.add(6);
    	ZigzagIterator i = new ZigzagIterator(v1, v2);
    	while(i.hasNext()) {
    		System.out.println(i.next());
    	}
    }
}

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */