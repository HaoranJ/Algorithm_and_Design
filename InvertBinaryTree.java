public class InvertBinaryTree {
	public TreeNode invertTree(TreeNode root) {
		invertNode(root);
		return root;
	}

	private void invertNode(TreeNode node) {
		if (node == null || (node.left == null && node.right == null)) {
			return;
		} else if (node.right == null) {
			node.right = node.left;
			node.left = null;
			invertNode(node.right);
		} else if (node.left == null) {
			node.left = node.right;
			node.right = null;
			invertNode(node.left);
		} else {
			TreeNode temp = node.right;
			node.right = node.left;
			node.left = temp;
			invertNode(node.right);
			invertNode(node.left);
		}
	}

	public static void main(String[] args) {
		
	}
}