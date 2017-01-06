import java.util.*;

public class BinaryTreePaths {

	public List<String> binaryTreePaths(TreeNode root) {
		ArrayList<String> ret = new ArrayList<>();
		ArrayList<TreeNode> path = new ArrayList<>();
		StringBuilder sb = new StringBuilder();

		searchPath(root, path, ret, sb);
		return ret;
	}

	private void searchPath(TreeNode node, List<TreeNode> path, List<String> ret, StringBuilder sb) {
		if (node == null) {
			return;
		} 
		path.add(node);
		if (node.left == null && node.right == null) {
			ListIterator<TreeNode> litr = path.listIterator();
			while (litr.hasNext()) {
				if (litr.nextIndex() == path.size()-1) {
					sb.append(litr.next().val);
				} else {
					sb.append(litr.next().val + "->");
				}
			}
			ret.add(sb.toString());
			sb.delete(0, sb.length());
		} else {
			if (node.left != null) {
				searchPath(node.left, path, ret, sb);
			}
			if (node.right != null) {
				searchPath(node.right, path, ret, sb);
			}
		}
		path.remove(path.size()-1);
	}
}