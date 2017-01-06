import java.util.*;

//LeetCode 50, 
//tested Integer.MIN to avoid overflow
//time = O(32)
public class PowerNumber {
	public double myPow(double x, int n) {
		if (n == 0) {
			return 1.0;
		}
		if (x == 0.0) {
			return 0.0;
		}
		int temp = myPow(x, n/2);
		if (n < 0) {
			x = 1 / x;
		}
		return n%2 == 0 ? temp * temp : x * temp * temp;
	}
}