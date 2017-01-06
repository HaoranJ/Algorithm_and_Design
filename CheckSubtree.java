import java.util.*;

public class CheckSubtree {
  public boolean isSubtree(TreeNode root, TreeNode node) {
    return isEquivalent(root, node) || isSubtree(root.left, node) || isSubtree(root.right, node);
  }

  private boolean isEquivalent(TreeNode n1, TreeNode n2) {
    if (n1 == null && n2 == null) {
      return true;
    } else if (n1 == null || n2 == null) {
      return false;
    }
    return n1.val == n2.val && isEquivalent(n1.left, n2.left) && isEquivalent(n1.right, n2.right);
  }
}
