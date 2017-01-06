import java.util.*;

public class PreorderTraversal {
	//recursion. time = O(n), space = O(n)
	public List<Integer> preorderTraversal(TreeNode root) {
		List<Integer> ans = new ArrayList<>();
		if(root == null) {
			return ans;
		}
		ans.add(root.val);
		ans.addAll(preorderTraversal(root.left));
		ans.addAll(preorderTraversal(root.right));
		return ans;
	}

	/*
	LeetCode 144
	Use stack to simulate the workflow of call stack in system.
	time = O(n), space = O(logn)
	*/
	public List<Integer> preorderTraversal(TreeNode root) {
		List<Integer> ans = new ArrayList<>();
		if(root == null) { return ans; }
		Deque<TreeNode> stack = new ArrayDeque<>();
		stack.push(root);
		while (!stack.isEmpty()) {
			TreeNode cur = stack.pop();
			ans.add(cur.val);
			if (cur.right != null) {
				stack.push(cur.right);
			}
			if (cur.left != null) {
				stack.push(cur.left);
			}
		}
		return ans;
	}

	//Morris traversal for preorder without stack or recursive. time = O(n), space = O(1)
	public List<Integer> preorderTraversal_Morris(TreeNode root) {
		List<Integer> ans = new ArrayList<>();
		while (root != null) {
			if (root.left == null) {
				ans.add(root.val);
				root = root.right;
			} else {
				TreeNode probe = root.left;
				while (probe.right != null && probe.right != root) {
					probe = probe.right;
				}
				if (probe.right == null) {
					ans.add(root.val);
					probe.right = root;
					root = root.left;
				} else if (probe.right == root){
					probe.right = null;
					root = root.right;
				}
			}
		}
		return ans;
	}
}
