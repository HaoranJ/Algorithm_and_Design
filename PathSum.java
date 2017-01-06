/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

//Path Sum III - LeetCode 437.
private int count = 0;
public int pathSum(TreeNode root, int sum) {
   if (root == null) {
     return 0;
   }
   //ancestors contains all possible path sum starting with every ancestor.
   List<Integer> ancestors = new ArrayList<>();
   dfs(root, ancestors, sum);
   return count;
}

private void dfs(TreeNode root, List<Integer> ancestors, int sum) {
   if (root.val == sum) {
     count++;
   }
   int num = root.val;
   for (int i = ancestors.size()-1; i >= 0; i--) {
     num += ancestors.get(i);
     if(num == sum) { count++; }
   }
   ancestors.add(root.val);
   if (root.left != null) {
     dfs(root.left, ancestors, sum);
   }
   if (root.right != null) {
     dfs(root.right, ancestors, sum);
   }
   ancestors.remove(ancestors.size()-1);
}

public int pathSum(TreeNode root, int sum) {
  if (root == null) {
    return 0;
  }
  return dfs(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
}

private int dfs(TreeNode root, int sum) {
  int count = 0;
  if (root.val == sum) {
    count++;
  }
  if (root.left != null) {
    count += dfs(root.left, sum - root.val);
  }
  if (root.right != null) {
    count += dfs(root.right, sum - root.val);
  }
  return count;
}
