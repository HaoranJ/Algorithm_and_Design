import java.util.*;

class BinaryTreeRightSideView {
	//Breadth First Search, time = O(n), space = O(n )
	public List<Integer> rightSideView(TreeNode root) {
		ArrayDeque<TreeNode> queue = new ArrayDeque<>();
		List<Integer> list = new ArrayList<>();
		if (root == null) { return list; }
		queue.push(root);
		int levelSize = 1;
		while (!queue.isEmpty()) {
			int nextLevelSize = 0;
			for (int j = 1; j <= levelSize ;j++ ) {
				TreeNode node = queue.removeLast();
				if (node.left != null) { 
					queue.push(node.left);
					nextLevelSize++;
				}
				if (node.right != null) { 
					queue.push(node.right); 
					nextLevelSize++;
				}
				if (j == levelSize) {
					list.add(node.val);
				}
			}
			levelSize = nextLevelSize;
		}
		return list;
	}


	//Depth First Search, time = O(n), space = O(1)
	static List<Integer> view = new ArrayList<Integer>();
	public List<Integer> rightSideView_DFS(TreeNode root) {
		recursive(root, 1);
		return view;
	}

	private void recursive(TreeNode node, int depth) {
		if (node == null) { return; }
		if (view.size() < depth) {
			view.add(node.val);	
		}
		recursive(node.right, depth+1);
		recursive(node.left, depth+1);
	}

	public static void main(String[] args) {
		BinaryTreeRightSideView rsv = new BinaryTreeRightSideView();
		TreeNode root = new TreeNode(1);
		TreeNode n1 = new TreeNode(3);
		root.right = n1;
		rsv.rightSideView_DFS(root);
		System.out.println(view);
	}
}