import java.util.*;

//LeetCode 152, 
//time = o(n), space = constant
public class MaximumProductArray{
	public static int maxProduct(int[] nums) {
		assert nums != null;
		if (nums.length == 0) {
			return 0;
		}
		int maxSofar = Integer.MIN_VALUE;
		int minEndHere = 1;
		int maxEndHere = 1;
		for (int i = 0; i < nums.length; i++) {
			int cur = nums[i];
			int temp = maxEndHere;
			maxEndHere = Math.max(cur * maxEndHere, Math.max(cur, cur * minEndHere));
			minEndHere = Math.min(cur * minEndHere, Math.min(cur, cur * temp));	
			maxSofar = Math.max(maxSofar, maxEndHere);
		}
		return maxSofar;
	}

	public static void main(String[] args) {
		int[] arr = {2,3,-2,-4};
		System.out.println(maxProduct(arr));
	}
}