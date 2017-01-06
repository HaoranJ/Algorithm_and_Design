//Leetcode 366

//Find the height of every node. The initial leaf's height = 0 which is the index
//of the final List answer. Use recursion (bottom-up fashion) to get the heights
//and add them to the answer list.
public List<List<Integer>> findLeaves(TreeNode root) {
  List<List<Integer>> ans = new ArrayList<>();
  dfs(root, ans);
  return ans;
}

private int dfs(TreeNode node, List<List<Integer>> ans) {
  if(node == null) { return -1; }
  int height = 1 + Math.max(dfs(node.left, ans), dfs(node.right, ans));
  if (ans.size() >= height + 1) {
    ans.get(height).add(node.val);
  } else {
    ans.add(new ArrayList<Integer>(){{ add(node.val); }});
  }
  return height;
}

public List<List<Integer>> findLeaves(TreeNode root) {
  List<List<Integer>> ans = new ArrayList<>();
  if(root == null) { return ans; }
  while (root.left != null || root.right != null) {
    List<Integer> leaves = new ArrayList<>();
    ans.add(leaves);
    dfs(root, null, leaves);
  }
  ans.add(new ArrayList<Integer>(){{ add(root.val); }});
  return ans;
}

private void dfs(TreeNode node, TreeNode parent, List<Integer> leaves) {
  if (node.left == null && node.right == null) {
    if(parent.left == node) { parent.left = null; }
    if(parent.right == node) { parent.right = null; }
    leaves.add(node.val);
  }
  if (node.left != null) {
    dfs(node.left, node, leaves);
  }
  if (node.right != null) {
    dfs(node.right, node, leaves);
  }
}
