import java.util.*;
//LeetCode 215
public class KthLargestElementInArray {
	//method 1. sort the array, and return kth largest. time = O(nlogn), space = O(1)
	//method 2. Use PriorityQueue (min and max are okay). time = O(k + nlogk), space = O(k)
	public int findKthLargest_PriorityQueue(int[] nums, int k) {
		assert k >= 1 && k <= nums.length;
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int x : nums) {
			pq.add(x);
			if (pq.size() > k) {
				pq.poll();
			}
		}
		return pq.poll();
	}
	//method 3. Randomized Select. time = O(n), space = O(1)
    public int findKthLargest(int[] nums, int k) {
        assert k >= 1 && k <= nums.length;
        return randomizedSelect(nums, 0, nums.length-1, nums.length-k+1);
    }

    private int randomizedSelect(int[] nums, int lo, int hi, int m) {
    		if(lo == hi) { return nums[lo]; }
        int p = randomizedPartition(nums, lo, hi);
        int offset = p-lo+1;
        if (offset == m) {
            return nums[p];
        } else if (offset > m) {
            return randomizedSelect(nums, lo, p-1, m);
        } else {
            return randomizedSelect(nums, p+1, hi, m-(offset));
        }
    }

    private int randomizedPartition(int[] nums, int lo, int hi) {
        int rd = lo + (int)(Math.random()*(hi-lo+1));
        swap(nums, rd, hi);
        int pivot = nums[hi];
        int i = lo-1;
        for (int j = lo; j < hi; j++) {
            if (nums[j] < pivot) {
                i++;
                swap(nums, i, j);
            }
        }
        swap(nums, i+1, hi);
        return i+1;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
