public class BinaryTreeInorderTraversal {
  //1.recursion. time = O(n), space = O(n)
  //2. stack inorder traversal, time = O(n), space = O(logn)
  //3. Morris Traversal without recursion and without extra space(stack). time = O(n), space = O(1)
  public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> list = new ArrayList<>();
    while (root != null) {
      if (root.left == null) {
        list.add(root.val);
        root = root.right;
      } else {
        TreeNode temp = root.left;
        while (temp.right != null && temp.right != root) {
          temp = temp.right;
        }
        //first time to traverse left subtree of root.
        if (temp.right == null) {
          temp.right = root;
          root = root.left;
        }
        //left subtree has been covered.
        else if (temp.right == root){
          list.add(root.val);
          root = root.right;
          temp.right = null;
        }
      }
    }
    return list;
  }
}
