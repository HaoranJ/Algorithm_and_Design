import java.util.*;

class BinaryTreeZigZagLevelOriderTraversal {
	//Breadth First Search, time = O(n), space = O(n)
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> ret = new ArrayList<List<Integer>>();
		if (root == null) { return ret; }
		ArrayDeque<TreeNode> deque = new ArrayDeque<>();
		Boolean toRight = true;
		deque.push(root);
		int nodeNum = 1;
		while (!deque.isEmpty()) {
			List<Integer> list = new ArrayList<>();
			int count = 0;
			if (toRight) {
				for (int j = 0; j < nodeNum; j++ ) {
					TreeNode node = deque.removeLast();
					if (node.left != null) { deque.push(node.left); count++; }
					if (node.right != null) { deque.push(node.right); count++; }
					list.add(node.val);
				}
				nodeNum = count;
			} else {
				for (int j = 0; j < nodeNum; j++) {
					TreeNode node = deque.pop();
					if (node.right != null) { deque.addLast(node.right); count++; }
					if (node.left != null) { deque.addLast(node.left); count++; }
					list.add(node.val);
				}
				nodeNum = count;
			}
			ret.add(list);
			toRight = !toRight;
		}
		return ret;
	}

	//Recursive, time = O(n), space = O(n), need the level(depth) parameter
	static List<List<Integer>> ans = new ArrayList<>();
	public List<List<Integer>> zigzagLevelOrder_Recursive(TreeNode root) {
		recursive(root, 1);
		for (int j = 0; j < ans.size(); j++) {
			if (j % 2 == 1) {
				Collections.reverse(ans.get(j));
			}
		}
		List<List<Integer>> ret = ans;
		ans = new ArrayList<List<Integer>>();
		return ret;
	}

	private void recursive(TreeNode node, int level) {
		if (node == null) { return; }
		if (ans.size() < level) {
			ans.add(new ArrayList<Integer>());
		}
		ans.get(level-1).add(node.val);
		recursive(node.left, level+1);
		recursive(node.right, level+1);
	}
}