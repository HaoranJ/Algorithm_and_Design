import java.util.*;

public class FindLargestBSTInBinaryTree {
	private static class TreeNode {
		private int val;
		private TreeNode left;
		private TreeNode right;

		public TreeNode(int val) {
			this.val = val;
		}

		public String toString() {
			return String.valueOf(this.val);
		}
	}

	/*
	* Definition of BST:
			1. both left and right subtrees of the root is BST
			2. root.val > the maximum of left subtree
			3. root.val < the minimum of right subtree
	* Definition of Subtree: a subtree in a tree T is a tree consisting of a node in T and all of its descendants in T
	*/
	private class Subtree {
		private TreeNode subroot;
		private int min;
		private int max;
		private int nodesNum;

		public Subtree(TreeNode subroot, int min, int max, int nodesNum) {
			this.subroot = subroot;
			this.min = min;
			this.max = max;
			this.nodesNum = nodesNum;
		}
	}

	//Find the largest BST subtree in a binary tree
	//time = O(n), space = O(1)
	public TreeNode findLargestBST(TreeNode root) {
		return checkBST(root).subroot;
	}

	private Subtree checkBST(TreeNode root) {
		if (root == null) {
<<<<<<< 80c257f2999f994806b99e0950c89320b1fb05fb
			return new Subtree(null, 0, 0, 0);
=======
			return new Subtree(null, Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
>>>>>>> 8/14/16
		}
		if (root.left == null && root.right == null) {
			return new Subtree(root, root.val, root.val, 1);
		}
		Subtree lSub = checkBST(root.left);
		Subtree rSub = checkBST(root.right);
<<<<<<< 80c257f2999f994806b99e0950c89320b1fb05fb
		if (lSub.subroot == null && rSub.subroot != null) {
			if (root.right == rSub.subroot && root.val < rSub.min) {
				return new Subtree(root, root.val, rSub.max, rSub.nodesNum + 1);
			} else {
				return rSub;
			}
		}
		if (lSub.subroot != null && rSub.subroot == null) {
			if (lSub.subroot == root.left && lSub.max < root.val) {
				return new Subtree(root, lSub.min, root.val, lSub.nodesNum + 1);
			} else {
				return lSub;
			}
		}
=======
>>>>>>> 8/14/16
		if (lSub.subroot == root.left && rSub.subroot == root.right
				&& lSub.max < root.val && rSub.min > root.val) {
			return new Subtree(root, lSub.min, rSub.max, lSub.nodesNum + rSub.nodesNum + 1);
		} else {
			return lSub.nodesNum > rSub.nodesNum ? lSub : rSub;
		}
	}

	public static void main(String[] args) {
		FindLargestBSTInBinaryTree f = new FindLargestBSTInBinaryTree();
		TreeNode root = new TreeNode(10);
		TreeNode n1 = new TreeNode(5);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(7);
		TreeNode n4 = new TreeNode(5);
		TreeNode n5 = new TreeNode(3);
		TreeNode n6 = new TreeNode(20);
		TreeNode n7 = new TreeNode(1);
		TreeNode n8 = new TreeNode(9);
		TreeNode n9 = new TreeNode(4);
		TreeNode n10 = new TreeNode(21);
		root.left = n1;
		// root.right = n4;
		n1.left = n2;
		// n1.right = n3;
		// n2.left = n7;
		// n4.left = n5;
		// n5.right = n9;
		// n4.right = n6;
		// n6.left = n9;
		// n6.right = n10;
		System.out.println(f.findLargestBST(root));
	}
}
