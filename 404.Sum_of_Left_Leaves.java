// LeetCode 404. Sum of Left Leaves.
// Find the sum of all left leaves in a given binary tree.
//
// Example:
//
//     3
//    / \
//   9  20
//     /  \
//    15   7
//
// There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.

//recursion
public int sumOfLeftLeaves(TreeNode root) {
  return sumOfLeftLeaves(root, false);
}

private int sumOfLeftLeaves(TreeNode root, boolean isLeftChild) {
  if(root == null) { return 0; }
  if(root.left == null && root.right == null && isLeftChild) {
    return root.val;
  }
  return sumOfLeftLeaves(root.left, true) + sumOfLeftLeaves(root.right, false);
}

//BFS - iterative
public int sumOfLeftLeaves(TreeNode root) {
  if(root == null) { return 0; }
  Deque<TreeNode> queue = new ArrayDeque<>();
  queue.add(root);
  int sum = 0;
  while(!queue.isEmpty()) {
    TreeNode node = queue.poll();
    if (node.left != null) {
      if(node.left.left == null && node.left.right == null) {
        sum += node.left.val;
      } else {
        queue.add(node.left);
      }
    }
    if (node.right != null) {
      if(node.right.left != null || node.right.right != null) {
        queue.add(node.right);
      }
    }
  }
  return sum;
}
