/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
import java.util.*;

public class lowestCommonAncestorBST {
  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null) {
			return root;
		}
		if (p.val > q.val) {
			return lowestCommonAncestor(root, q, p);
		}
		if (root.val >= p.val && root.val <= q.val) {
			return root;
		} else if (root.val < p.val) {
			return lowestCommonAncestor(root.right, p, q);
		} else {
			return lowestCommonAncestor(root.left, p, q);
		}
	}

	//if a node is the LCA of p and q <=> both subtrees(itself included) contains one of p and q
	public TreeNode lowestCommonAncestorInBinaryTree(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null || root == q || root == p) {
			return root;
		}
		TreeNode left = lowestCommonAncestorInBinaryTree(root.left, p, q);
		TreeNode right = lowestCommonAncestorInBinaryTree(root.right, p, q);
		if (left != null && right != null) {
			return root;
		}
		return left == null ? right : left;
	}

}