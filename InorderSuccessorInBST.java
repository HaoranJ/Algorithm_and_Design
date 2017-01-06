public class inorderSuccessorInBST {
  //Iteration. time = O(logn), space = O(1)
  public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
    //if p's right subtree is not null.
    if(p.right != null) {
      TreeNode probe = p.right;
      while(probe.left != null) {
        probe = probe.left;
      }
      return probe;
    }
    //find the nearest ancestor that has p as left child.
    else {
      TreeNode succ = null;
      TreeNode probe = root;
      while(probe != p) {
        if(p.val < probe.val) {
          succ = probe;
          probe = probe.left;
        } else {
          probe = probe.right;
        }
      }
      return succ;
    }
  }

  //Recursion. time = O(logn), space = O(logn)
  public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
    if(root == null) {
      return null;
    }
    if(p.val < root.val) {
      TreeNode leftSubTree = inorderSuccessor(root.left, p);
      return leftSubTree == null ? root : leftSubTree;
    } else {
      TreeNode rightSubTree = inorderSuccessor(root.right, p);
      return rightSubTree;
    }
  }
}
