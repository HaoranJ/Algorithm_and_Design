import java.util.*;

public class SumRootToLeafNumbers {
    private static int sum = 0;
    private static StringBuilder sb = new StringBuilder();
    
    //Depth first search, time = O(V+E) = O(n), space = O(1)
    public int sumNumbers(TreeNode root) {
        dfs(root);
        int ret = sum;
        sum = 0;
        return ret;
    }
    
    private void dfs(TreeNode node) {
        if (node == null) { return; }
        sb.append(node.val);
        if (node.left == null && node.right == null) { 
            sum += Integer.parseInt(sb.toString());
        }
        if(node.left != null) { dfs(node.left); }
        if(node.right != null) { dfs(node.right); }
        sb.deleteCharAt(sb.length()-1);
    }
}