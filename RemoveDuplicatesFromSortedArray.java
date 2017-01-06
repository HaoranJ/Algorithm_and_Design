import java.util.*;

public class RemoveDuplicatesFromSortedArray {
	//LeetCode 80
	//Two pointers, time = O(n), in place
	public int removeDuplicates(int[] nums) {
		int len = nums.length;
		int f = 0;
		int p = 1;
		if (len == 0) { return f; }
		while (p < len) {
			int flag = nums[f];
			//tolerate twice duplicates
			if (flag == nums[p]) {
				swap(nums, f+1, p);
				f++;
				p++;
			}
			while (p < len && nums[p] == flag) {
				p++;
			}
			if (p == len) { break; }
			swap(nums, f+1, p);
			f++;
			p++;
		}
		return f+1;
	}

	private void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
}