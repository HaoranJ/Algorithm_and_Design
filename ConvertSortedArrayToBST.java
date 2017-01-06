import java.util.*;

public class ConvertSortedArrayToBST {
	//height balanced: 
	//time = O(n)
	public TreeNode sortedArrayToBST(int[] nums) {
		return recursiveHalve(nums, 0, nums.length-1);
	}

	private TreeNode recursiveHalve(int[] nums, int start, int end) {
		if (start > end) {
			return null;
		}
		int mid = (start + end) / 2;
		TreeNode root = new TreeNode(nums[mid]);
		root.left = recursiveHalve(nums, start, mid-1);
		root.right = recursiveHalve(nums, mid+1, end);
		return root;
	}
}