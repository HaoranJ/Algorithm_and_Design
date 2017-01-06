import java.util.*;

public class Median_Of_Two_Sorted_Arrays{
	public static void main(String[] args) {
		int[] nums1 = {1,2}, nums2 = {3,4,5,6};
		System.out.println(findMedianSortedArrays(nums1,nums2));
	}
	//O(log(m+n)), O(1)
	public static double findMedianSortedArrays(int[] nums1, int[] nums2){
		int m = nums1.length, n = nums2.length;
		if(m > n){ return findMedianSortedArrays(nums2, nums1); }
		if ((m+n)%2 == 1) {
			return (double)findK(nums1, 0, m-1, nums2, 0, n-1, (n+m)/2+1);
		}else{
			return ((double)findK(nums1, 0, m-1, nums2, 0, n-1, (n+m)/2) + (double)findK(nums1, 0, m-1, nums2, 0, n-1, (n+m)/2+1))/2.0;
		}
	}

	private static int findK(int[] nums1, int s1, int e1, int[] nums2, int s2, int e2, int k){
		assert k > 0;
		//m = 0, look for the kth in nums2[]
		if(s1 > e1){  return nums2[s2+k-1]; }
		if(k == 1){ return Math.min(nums1[s1], nums2[s2]); }
		if(k == (e1-s1+1)+(e2-s2+1)){ 
			return Math.max(nums1[e1], nums2[e2]); }
		//invariant (mid1 - s1 + 1) + (mid2 -s2 + 1) = k
		int mid1 = Math.min(e1, s1+k/2-1), mid2 = k-2+s2+s1-mid1;
		if (nums1[mid1] > nums2[mid2]) {
			mid2 = mid2 == s2 ? (mid2+1) : mid2;
			return findK(nums1, s1, mid1, nums2, mid2, e2, k-(mid2-s2));
		}else if(nums1[mid1] == nums2[mid2]){
			return nums1[mid1];
		}else{
			mid1 = mid1 == s1 ? (mid1+1) : mid1;
			return findK(nums1, mid1, e1, nums2, s2, mid2, k-(mid1-s1));
		}
	}
}

// I Love You ^V^