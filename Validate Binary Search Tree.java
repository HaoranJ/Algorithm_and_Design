// LeetCode - 98 Validate Binary Search Tree
// Given a binary tree, determine if it is a valid binary search tree (BST).
//
// Assume a BST is defined as follows:
//
// The left subtree of a node contains only nodes with keys less than the node's key.
// The right subtree of a node contains only nodes with keys greater than the node's key.
// Both the left and right subtrees must also be binary search trees.
// Example 1:
//     2
//    / \
//   1   3
// Binary tree [2,1,3], return true.
// Example 2:
//     1
//    / \
//   2   3
// Binary tree [1,2,3], return false.

//From BST definition!! recursion - DFS. put the val range into parameters, pass it top-down.
//memory load is heavy.
public boolean isValidBST(TreeNode root) {
  return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
}

private boolean isValidBST(TreeNode root, long lowBound, long upBound) {
  if(root == null) {
    return true;
  }
  long curVal = (long)root.val;
  return lowBound < curVal && curVal < upBound
    && isValidBST(root.left, lowBound, curVal)
    && isValidBST(root.right, curVal, upBound);
}

//Stack inorder traversal.
//time = O(n), memory = O(logn)
public boolean isValidBST(TreeNode root) {
  Deque<TreeNode> stack = new ArrayDeque<>();
  TreeNode node = root;
  while(node != null) {
    stack.push(node);
    node = node.left;
  }
  TreeNode pred = null;
  while(!stack.isEmpty()) {
    TreeNode cur = stack.pop();
    if(pred == null || pred.val < cur.val) {
      pred = cur;
      TreeNode ptr = cur.right;
      while(ptr != null) {
        stack.push(ptr);
        ptr = ptr.left;
      }
    } else {
      return false;
    }
  }
  return true;
}

//Morris Traversal - iteration,
//time = O(n), space = O(1)
public boolean isValidBST(TreeNode root) {
  TreeNode cur = root;
  TreeNode predecessor = null;
  while(cur != null) {
    if(cur.left == null) {
      if(predecessor == null || predecessor.val < cur.val) {
        predecessor = cur;
        cur = cur.right;
      } else {
        return false;
      }
    } else {
      //find the rightmost node in the left subtree of cur
      TreeNode node = cur.left;
      while(node.right != cur && node.right != null) {
        node = node.right;
      }
      if(node.right == null) {
        node.right = cur;
        cur = cur.left;
      } else {
        node.right = null;
        if(predecessor == null || predecessor.val < cur.val) {
          predecessor = cur;
          cur = cur.right;
        } else {
          return false;
        }
      }
    }
  }
  return true;
}
