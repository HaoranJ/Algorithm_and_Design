import java.util.*;

//LeetCode - 69
public class SquareRoot {
	//Newton's method
	//f(x) = x^2 - k;
	//use recurse and derivative to find the root x;
	public int mySqrt(int k) {
		assert k >= 0;
		double pre = k/2.0;
		while (true) {
			double cur = pre - (pre*pre - (double)k)/(2*pre);
			if ((int)cur == (int)pre) {
				return (int)cur;
			} else {
				pre = cur;
			}
		}
	}

	//binary search, use iteration rather than recusion to avoid stack overflow.
	//time = O(logk)
	public int mySqrt_BinarySearch(int k) {
		int lo = 0;
		int hi = k;
		while (hi - lo > 1) {
			int mid = (lo + hi) / 2;
			long sq = (long)mid * (long)mid;
			if (sq > k) {
				hi = mid;
			} else if (sq == k) {
				return mid;
			} else {
				lo = mid;
			}
		}
		if (k == hi * hi) {
			return hi;
		} else {
			return lo;
		}
	}
}