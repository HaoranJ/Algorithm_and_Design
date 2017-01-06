import java.util.*;

public class HappyNumber {

	public boolean isHappy(int n) {
		if (n < 0) {
			return false;
		}
		HashMap<Integer, Boolean> store = new HashMap<Integer, Boolean>();
		while(true) {
			n = getSumOfSquares(n);
			if (n == 1) {
				return true;
			} else if(store.containsKey(n)) {
				return false;
			} else {
				store.put(n, true);
			}
		}
	}
	private int getSumOfSquares(int n) {
		int ret = 0;
		int digit;
		while(n > 0) {
			digit = n % 10;
			ret += (digit * digit);
			n /= 10;
		}
		return ret;
	}

	public static void main(String[] args) {
		HappyNumber hn = new HappyNumber();
		System.out.println(hn.isHappy(19));
	}
}