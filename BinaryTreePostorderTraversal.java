import java.util.*;

public class BinaryTreePostorderTraversal {
  //recursion - time = O(n), space = O(n)
  /*
  LeetCode 145
  Use stack to simulate the workflow of call stack in system.
  time = O(n), space = O(logn)
  */
  public List<Integer> postorderTraversal(TreeNode root) {
    List<Integer> ans = new ArrayList<>();
    if(root == null) { return ans; }
    Deque<TreeNode> stack = new ArrayDeque<>();
    stack.push(root);
    while (!stack.isEmpty()) {
      TreeNode cur = stack.pop();
      ans.add(cur.val);
      if (cur.left != null) {
        stack.push(cur.left);
      }
      if (cur.right != null) {
        stack.push(cur.right);
      }
    }
    Collections.reverse(ans);
    return ans;
  }

  /*
  Morris traversal for postorder without stack or recursive. time = O(n), space = O(1)
  Key!!! - use a dummy node as the real root. That way, we can reversely add the right edge
  nodes including intial root in the left subtree of the dummy.
  */
  public List<Integer> postorderTraversal(TreeNode root) {
    List<Integer> ans = new ArrayList<>();
    TreeNode fakeRoot = new TreeNode(0);
    fakeRoot.left = root;
    TreeNode cur = fakeRoot;
    while(cur != null) {
      if(cur.left == null) {
        cur = cur.right;
      } else {
        TreeNode probe = cur.left;
        //!= null && != cur
        while(probe.right != null && probe.right != cur) {
          probe = probe.right;
        }
        //first time to traverse left subtree.
        if(probe.right == null) {
          probe.right = cur;
          cur = cur.left;
        }
        //left subtree of cur.left has been covered.
        else if(probe.right == cur) {
          //reversely add all the right-edge nodes in cur's left subtree.
          TreeNode startNode = cur.left;
          List<Integer> rightEdgeNodes = new ArrayList<>();
          while(startNode != cur) {
            rightEdgeNodes.add(startNode.val);
            startNode = startNode.right;
          }
          Collections.reverse(rightEdgeNodes);
          ans.addAll(rightEdgeNodes);
          probe.right = null;
          cur = cur.right;
        }
      }
    }
    return ans;
  }
}
