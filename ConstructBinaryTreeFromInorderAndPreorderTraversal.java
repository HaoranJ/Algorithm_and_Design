import java.util.*;

public class ConstructBinaryTreeFromInorderAndPreorderTraversal {
	private static int preS = 0;
	HashMap<Integer, Integer> map = new HashMap<>();
	//** time complexity
	//	1. balanced binary tree, every root index linear search need O(n), it needs logn searches. => time = n(logn)
	//	2. worst case - skewed to the left or right => time = O(n^2)
	// Thus, we can use HashMap to finish root search in constant time => time = O(n)
	//** It won't work if the tree has duplicates
	public TreeNode buildTree(int[] preorder, int[] inorder) {
		for (int j = 0; j < inorder.length; j++) {
			map.put(inorder[j], j);
		}
		return recursive(preorder, inorder, 0, inorder.length-1, preS);
	}

	private TreeNode recursive(int[] preorder, int[] inorder, int inS, int inE, int preS, HashMap<Integer, Integer> map) {
		if (inS > inE) {
			return null;
		}
		TreeNode root = new TreeNode(preorder[preS++]);
		int rootIdx = map.get(root.val);
		// //linear search
		// for (int j = 0; j <= inE; j++) {
		// 	if (inorder[j] == root.val) { 
		// 		rootIdx = j;
		// 		break;
		// 	}
		// }
		root.left = recursive(preorder, inorder, inS, rootIdx-1, preS);
		root.right = recursive(preorder, inorder, rootIdx+1, inE, preS+rootIdx-inS);
		return root;
	}
}