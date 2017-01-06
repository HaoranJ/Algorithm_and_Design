//LeetCode 99 - Recover BST.
// Two elements of a binary search tree (BST) are swapped by mistake.
//
// Recover the tree without changing its structure.
//
// Note:
// A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?

//Get the inorder of BST, then find out the two wrong nodes.
//1,2,3,4,5,6,7
      n1          n2
//1,  5,  3,  4,  2,  6,  7 -> only two places that pred.val > cur.val;
//   pred,cur pred,cur
//1,2,4,3,5,6,7 -> if two wrong nodes are next to each other, only one place as above.
//Morris Traversal, time = O(n), space = O(1)
public void recoverTree(TreeNode root) {
  //Morris inorder trav
  TreeNode n1 = null, n2 = null;
  TreeNode cur = root, curPred = null, n1Succ = null;
  while(cur != null) {
    if(cur.left == null) {
      if(curPred != null && curPred.val > cur.val) {
        if(n1 == null) {
          n1 = curPred;
          n1Succ = cur;
        } else {
          n2 = cur;
        }
      }
      curPred = cur;
      cur = cur.right;
    } else {
      TreeNode probe = cur.left;
      //Don't forget != cur
      while(probe.right != null && probe.right != cur) {
        probe = probe.right;
      }
      //first time to traverse cur's left subtree
      if(probe.right == null) {
        probe.right = cur;
        cur = cur.left;
      } else if (probe.right == cur) {
        //already finish off cur's left subtree
        if(curPred != null && curPred.val > cur.val) {
          if(n1 == null) {
            n1 = curPred;
            n1Succ = cur;
          } else {
            n2 = cur;
          }
        }
        curPred = cur;
        cur = cur.right;
        //recover the changed BST structure at last.
        probe.right = null;
      }
    }
  }
  if(n2 == null) {
    n2 = n1Succ;
  }
  int tmp = n1.val;
  n1.val = n2.val;
  n2.val = tmp;
}
