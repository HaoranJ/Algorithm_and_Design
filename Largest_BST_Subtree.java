//LeetCode 333. Largest BST Subtree
// Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), where largest means subtree with largest number of nodes in it.
//
// Note:
// A subtree must include all of its descendants.
// Here's an example:
//     10
//     / \
//    5  15
//   / \   \
//  1   8   7
// The Largest BST Subtree in this case is the highlighted one.
// The return value is the subtree's size, which is 3.

//bottom-up, recursion, DFS
//time = O(n)
class SubTree {
  long minVal;
  long maxVal;
  int size;
  TreeNode rootBST;
  SubTree(long min, long max, int s, TreeNode root) {
    minVal = min;
    maxVal = max;
    size = s;
    rootBST = root;
  }
}
public int largestBSTSubtree(TreeNode root) {
  return solve(root).size;
}

private SubTree solve(TreeNode root) {
  //base case
  if(root == null) {
    return new SubTree(Long.MAX_VALUE, Long.MIN_VALUE, 0, null);
  }
  if(root.left == null && root.right == null) {
    return new SubTree((long)root.val, (long)root.val, 1, root);
  }

  //recursion.
  SubTree leftST = solve(root.left);
  SubTree rightST = solve(root.right);
  //if root is possible to be in the largest BST subtree.
  if(root.left == null && root.right == rightST.rootBST) {
    if(root.val < rightST.minVal && root.val < rightST.rootBST.val) {
      return new SubTree((long)root.val, rightST.maxVal, rightST.size + 1, root);
    }
  } else if(root.right == null && root.left == leftST.rootBST) {
    if(root.val > leftST.maxVal && root.val > leftST.rootBST.val) {
      return new SubTree(leftST.minVal, (long)root.val, leftST.size + 1, root);
    }
  } else if(root.left == leftST.rootBST && root.right == rightST.rootBST) {
    if(leftST.maxVal < root.val && root.val < rightST.minVal &&
      leftST.rootBST.val < root.val && root.val < rightST.rootBST.val ) {
      return new SubTree(leftST.minVal, rightST.maxVal, 1 + leftST.size + rightST.size, root);
    }
  }
  return leftST.size > rightST.size ? new SubTree(0, 0, leftST.size, leftST.rootBST) :
    new SubTree(0, 0, rightST.size, rightST.rootBST);
}
