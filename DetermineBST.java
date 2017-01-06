import java.util.*;

public class DetermineBST {
	//Determine whether a binary tree is BST
	public boolean isBST(TreeNode root) {
		return checkNode(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	private boolean checkNode(TreeNode node, int low, int high) {
		if (node == null) { return true; }
		return low < node.val && node.val < high &&
				checkNode(node.left, low, node.val) &&
				checkNode(node.right, node.val, high);
	}

	//Use Inorder traversal to check
	public boolean isBST_Inorder(TreeNode root) {
		return checkNode_Inorder(root, Integer.MIN_VALUE);
	}

	private boolean checkNode_Inorder(TreeNode node, int pre) {
		if (node == null) { return true; }
		if (checkNode_Inorder(node.left, pre)) {
			if (node.val > pre) {
				return checkNode_Inorder(node.right, node.val);
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	//Use Morris Traversal to to inorder traverse to check
	private boolean isBST_Morris(TreeNode root) {
		int pre = Integer.MIN_VALUE;
		while (root != null) {
			if (root.left == null) {
				if (root.val < pre) { return false; }
				pre = root.val;
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
					if (root.val < pre) { return false; }
					pre = root.val;
					temp.right = null;
					root = root.right;
				}
			}
		}
		return true;
	}

	public static void main(String[] args) {
		DetermineBST d = new DetermineBST();
		TreeNode root = new TreeNode(10);
		TreeNode n1 = new TreeNode(5);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(17);
		TreeNode n4 = new TreeNode(15);
		TreeNode n5 = new TreeNode(3);
		TreeNode n6 = new TreeNode(20);
		root.left = n1;
		root.right = n4;
		n1.left = n2;
		n1.right = n3;
		n4.left = n5;
		n4.right = n6;
		System.out.println(d.isBST_Morris(root));
	}
}