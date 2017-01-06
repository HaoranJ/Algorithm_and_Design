import java.util.*;

public class CountCompleteTreeNodes {
	private int getHeight(TreeNode root) {
		return root == null ? -1 : 1 + getHeight(root.left);
	}	
	//time = O(logn*logn), space = O(1)
	//iterative
	public int countNodes(TreeNode root) {
		int ret = 0;
		int H = getHeight(root);
		while (root != null) {
			int rH = getHeight(root.right);
			if (rH + 1 == H) {
				ret += 1 << H;
				root = root.right;
			} else {
				ret += 1 << (H-1);
				root = root.left;
			}
			H--;
		}
		return ret;
	}

	//recursive
	public int countNodesRecursive(TreeNode root) {
		return getNodes(root, getHeight(root));
	}

	private int getNodes(TreeNode root, int H) {
		if (root == null) {
			return count;
		}
		int rH = getHeight(root.right);
		if (rH + 1 == H) {
			return ((1 << H) + getNodes(root.right, H-1));
		} else {
			return ((1 << (H-1)) + getNodes(root.left, H-1));
		}
	}
}