import java.util.*;

public class BinaryTreeConsecutiveLongestSequence {
  /*
  LeetCode 298.
  Use dfs to go over every node, and record every consecutive sequence.
  time = O(n)
  */

  static int ans;
  public int longestConsecutive(TreeNode root) {
    ans = Integer.MIN_VALUE;
    dfs(root, 0);
    return ans;
  }

  private void dfs(TreeNode node, int c) {
    if(node == null) {
      ans = 0;
      return;
    }
    if((node.left == null || node.left.val != node.val + 1) && (node.right == null ||     node.right.val != node.val + 1)) ans = Math.max(ans, c+1);
    if(node.left != null) {
      if(node.val == node.left.val - 1) dfs(node.left, c+1);
      else dfs(node.left, 0);
    }
    if(node.right != null) {
      if(node.val == node.right.val - 1) dfs(node.right, c+1);
      else dfs(node.right, 0);
    }
  }

}
