import java.util.*;

public class BinaryTreeUpsideDown {
    //time = O(logn), space = O(logn)
    private static TreeNode newRoot = null;
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        recursive(root);
        TreeNode ret = newRoot;
        newRoot = null;
        return ret;
    }
    
    private void recursive(TreeNode node) {
        if (node == null || node.left == null) { 
            newRoot = node;
            return; 
        }
        if (node.left.left == null) {
            newRoot = node.left;
            node.left = null;
            newRoot.right = node;
            newRoot.left = node.right;
            node.right = null;
            return;
        }
        recursive(node.left);
        TreeNode newParent = node.left;
        newParent.right = node;
        node.left = null;
        newParent.left = node.right;
        node.right = null;
    }
}