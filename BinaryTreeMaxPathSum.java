public int maxPathSum(TreeNode root) {
  List<Integer> list = helper(root);
  return Collections.max(list);
}

private List<Integer> helper(TreeNode root) {
  List<Integer> ret = new ArrayList<>();
  //base case
  if(root == null) { return ret; }
  if(root.left == null && root.right == null) { ret.add(root.val); return ret; }
  //       1 (root)
  //     /   \
  //    2     3
  //   / \   / \
  //  4  5  6   7
  //upSum - max path sum that has root and allows its parent to connect it to consist of a new path.
  //i.e. 5-2-1 or 6-3-1
  //downSum - max path sum that is impossible for root's parent to connect.
  //i.e. 4-2-5, 5-2, 2-1,3,7
  List<Integer> lTreeList = helper(root.left);
  List<Integer> rTreeList = helper(root.right);
  int upSum = root.val;
  if(!lTreeList.isEmpty()) { upSum = Math.max(upSum, root.val + lTreeList.get(0)); }
  if(!rTreeList.isEmpty()) { upSum = Math.max(upSum, root.val + rTreeList.get(0)); }

  int downSum = Math.max(getMax(lTreeList), getMax(rTreeList));
  if (lTreeList.size() >= 1 && rTreeList.size() >= 1) {
    downSum = Math.max(downSum, lTreeList.get(0) + root.val + rTreeList.get(0));
  }
  ret.add(upSum);
  ret.add(downSum);
  return ret;
}

private int getMax(List<Integer> list) {
  int ret = Integer.MIN_VALUE;
  for (int num : list) {
    ret = Math.max(ret, num);
  }
  return ret;
}

// A path from start to end, goes up on the tree for 0 or more steps, then goes down for 0 or more steps. Once it goes down, it can't go up. Each path has a highest node, which is also the lowest common ancestor of all other nodes on the path.
// A recursive method dfs(TreeNode root) (1) computes the maximum path sum with highest node as the input node, update maximum if necessary (2) returns the maximum sum of the path that can be extended to input node's parent.
//time = O(n)
int maxPath;
public int maxPathSum(TreeNode root) {
  maxPath = Integer.MIN_VALUE;
  dfs(root);
  return maxPath;
}

private int dfs(TreeNode root) {
  //base case
  if(root == null) {
    return 0;
  }
  int leftST = Math.max(0, dfs(root.left));
  int rightST = Math.max(0, dfs(root.right));
  maxPath = Math.max(maxPath, leftST + root.val + rightST);
  return Math.max(leftST, rightST) + root.val;
}
