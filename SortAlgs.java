import java.util.*;

public class SortAlgs {
	//time = O(nlogn), space = O(1)
	public void quickSort(int[] nums) {
		quickSort(nums, 0, nums.length-1);
	}

	private void quickSort(int[] nums, int lo, int hi) {
		if (lo < hi) {
			int pivot = partition(nums, lo, hi);
			quickSort(nums, lo, pivot-1);
			quickSort(nums, pivot+1, hi);
		}
	}

	private int partition(int[] nums, int lo, int hi) {
		//Randomly pick up an element as pivot
		int i = lo-1;
		for (int j = lo; j < hi; j++) {
			if (nums[j] < nums[hi]) {
				swap(nums, i+1, j);
				i++;
			}
		}
		swap(nums, i+1, hi);
		return i+1;
	} 

	private void swap(int[] nums, int p, int q) {
		if (p != q) {
			int temp = nums[p];
			nums[p] = nums[q];
			nums[q] = temp;
		}
	}

	//time = O(nlogn), space = O(n)
	public void mergeSort(int[] nums) {
		int[] aux = new int[nums.length];
		mergeSort(nums, aux, 0, nums.length-1);
	}

	private void mergeSort(int[] nums, int[] aux, int lo, int hi) {
		if (lo >= hi) { return; }
		int mid = (lo + hi) / 2;
		mergeSort(nums, aux, lo, mid);
		mergeSort(nums, aux, mid+1, hi);
		merge(nums, aux, lo, mid, hi);
	}

	private void merge(int[] nums, int[] aux, int lo, int mid, int hi) {
		for (int j = lo; j <= hi; j++) {
			aux[j] = nums[j];
		}
		int p = lo;
		int q = mid+1;
		for (int j = lo; j <= hi; j++) {
			if (p > mid) {
				nums[j] = aux[q++];
			} else if (q > hi) {
				nums[j] = aux[p++];
			} else if (aux[p] < aux[q]) {
				nums[j] = aux[p++];
			} else {
				nums[j] = aux[q++];
			}
		}
	}

	public static void main(String[] args) {
		SortAlgs sortAlgs = new SortAlgs();
		int[] arr = {4,3,5,2,-1,4,3,20,99};
		sortAlgs.mergeSort(arr);
		for (int x : arr) {
			System.out.println(x);
		}
	}
}