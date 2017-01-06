import java.util.*;
<<<<<<< 80c257f2999f994806b99e0950c89320b1fb05fb

public class MaxPointsInLine {
  public int maxPoints(Point[] points) {
  	assert points != null;
    if(points.length <= 2) { return points.length; }
    Map<Line, Integer> map = new HashMap<>();
    int ans = 0;
    for (int i = 0; i < points.length; i++) {
    	Point p1 = points[i];
    	int duplicates = 0;
    	int max = 0;
    	for (int j = i+1; j < points.length; j++) {
    		Point p2 = points[j];
    		if (p1.x == p2.x && p1.y == p2.y) {
    			duplicates++;
    			continue;
    		}
    		Line line = getLine(p1, p2);
    		if (map.containsKey(line)) {
    			map.replace(line, map.get(line)+1);
    		} else {
    			map.put(line, 1);
    		}
    		max = Math.max(max, map.get(line));
    	}
    	ans = Math.max(ans, max+1+duplicates);
=======
/**
* Definition for a point.
* class Point {
*     int x;
*     int y;
*     Point() { x = 0; y = 0; }
*     Point(int a, int b) { x = a; y = b; }
* }
*/
//time = O(n^2), space = O(n)
public class MaxPointsInLine {
  public int maxPoints(Point[] points) {
    assert points != null;
    int pNum = points.length;
    if(pNum < 3) { return pNum; }
    Map<Line, Integer> map = new HashMap<>();
    int ans = 0;
    for (int i = 0; i < pNum; i++) {
      map.clear();
      Point p1 = points[i];
      int samePtr = 0;
      for (int j = i+1; j < pNum; j++) {
        Point p2 = points[j];
        if (p1.x == p2.x && p1.y == p2.y) {
          samePtr++;
        } else {
          Line line = getLine(p1, p2);
          map.put(line, map.getOrDefault(line, 1)+1);
        }
      }
      int maxPtr = 1;
      for (int num : map.values()) {
        maxPtr = Math.max(maxPtr, num);
      }
      ans = Math.max(ans, maxPtr + samePtr);
>>>>>>> 8/14/16
    }
    return ans;
  }

  private Line getLine(Point a, Point b) {
<<<<<<< 80c257f2999f994806b99e0950c89320b1fb05fb
  	if (a.x == b.x) {
  		return new Line(a.x, Double.MAX_VALUE, Double.MAX_VALUE);
  	} else {
  		double k = (a.y - b.y) / (a.x - b.x);
  		double intercept = a.y - (k * a.x);
  		return new Line(Integer.MIN_VALUE, intercept, k);
  	}
  }

  public static class Line {
  	int x;
  	double b;
  	double k;
  	public Line(int x, double intercept, double slope) {
  		this.x = x;
  		b = intercept;
  		k = slope;
  	}

  	@Override
  	public boolean equals(Object ob) {
  		if (!(ob instanceof Line)) {
  			return false;
  		}
  		Line line = (Line)ob;
  		if (line.b == Double.MAX_VALUE && line.k == Double.MAX_VALUE && line.x != Integer.MIN_VALUE) {
  			return this.x == line.x;
  		} else {
  			return (this.b == line.b && this.k == line.k);
  		}
  	}

  	@Override
  	public int hashCode() {
  		int result = 17;
  		result = 31 * result + x;
  		result = 31 * result + (int)b;
  		result = 31 * result + (int)k;
  		return result;
  	}
  }
}
=======
    double ax = (double)a.x, ay = (double)a.y, bx = (double)b.x, by = (double)b.y;
    if (a.x == b.x) {
      return new Line(a.x, 0, Double.MAX_VALUE);
    } else {
      //lose precision if double k = (a.y - b.y) / (a.x - b.x);
      double k = (ay - by) / (ax - bx);
      double intercept = ay - (k * ax);
      return new Line(0, intercept, k);
    }
  }

  public static class Line {
    int x;
    double b;
    double k;
    public Line(int x, double intercept, double slope) {
      this.x = x;
      b = intercept;
      k = slope;
    }

    @Override
    public boolean equals(Object ob) {
      if (!(ob instanceof Line)) {
        return false;
      }
      Line line = (Line)ob;
      if (this.k == Double.MAX_VALUE && line.k == Double.MAX_VALUE) {
        return this.x == line.x;
      } else {
        return (this.b == line.b && this.k == line.k);
      }
    }

    @Override
    public int hashCode() {
      int result = 17;
      result = 31 * result + x;
      result = 31 * result + (int)b;
      result = 31 * result + (int)k;
      return result;
    }
  }
}
>>>>>>> 8/14/16
