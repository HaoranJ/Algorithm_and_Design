import java.util.*;

public class CountUnivalueSubtrees {
	//time = O(n), space = O(1)
    static int ans = 0;
    public int countUnivalSubtrees(TreeNode root) {
        dfs(root);
        int ret = ans;
        ans = 0;
        return ret;
    }
    
    private boolean dfs(TreeNode node) {
        if (node == null) { return true; }
        boolean leftChild = true;
        boolean rightChild = true;
        boolean leftTree = dfs(node.left);
        boolean rightTree = dfs(node.right);
        if (node.left != null) {
            leftChild = node.val == node.left.val;
        }
        if (node.right != null) {
            rightChild = node.val == node.right.val;
        }
        if (leftTree && rightTree && leftChild && rightChild) {
            ans++;
            return true;
        } else {
            return false;
        }
    }
}