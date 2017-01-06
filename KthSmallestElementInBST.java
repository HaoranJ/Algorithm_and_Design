public class KthSmallestElementInBST {
	//Morris Traversal: space = O(1), time = O(height of BST)
	public int kthSmallest(TreeNode root, int k) {
		assert root != null;
		int counter = 0;
		while (root != null) {
			if (root.left == null) {
				if (++counter == k) {
					return root.val;
				}
				root = root.right;
			} else {
				TreeNode temp = root.left;
				while (temp.right != null && temp.right != root) {
					temp = temp.right;
				}
				if (temp.right != root) {
					temp.right = root;
					root = root.left;
				} else {
					if (++counter == k) {
						return root.val;
					}
					root = root.right;
					temp.right = null;
				}
			}
		}
		return -1;
	}

	//use find successor method 
	public int kthSmallest_2(TreeNode root, int k) {
		TreeNode min = treeMin(root);
		TreeNode ret = min;
		for (int j = 0; j < k-1 ;j++ ) {
			ret = treeSuccessor(ret);
		}
		return ret.val;
	}

	private TreeNode treeSuccessor(TreeNode node) {
		assert node != null;
		if (node.right != null) {
			return treeMin(node.right);
		}
		TreeNode y = node.p;
		while (y != null && y.right == node) {
			x = y;
			y = y.p;
		}
		return y;
	}

	private TreeNode treeMin(TreeNode root) {
		while (root.left != null) {
			root = root.left;
		}
		return root;
	}
}