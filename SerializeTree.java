  /**
   * Definition for a binary tree node.
   * public class TreeNode {
   *     int val;
   *     TreeNode left;
   *     TreeNode right;
   *     TreeNode(int x) { val = x; }
   * }
   */
  public class Codec {
    // Encodes a tree to a single string.
    //BFS
    public String serialize(TreeNode root) {
      StringBuilder sb = new StringBuilder();
      if (root == null) {
        sb.append("null");
      } else {
        Deque<TreeNode> queue = new ArrayDeque<>();
        sb.append(String.valueOf(root.val));
        queue.add(root);
        while (!queue.isEmpty()) {
          TreeNode node = queue.poll();
          if(node.left == null) { sb.append(",null"); }
          else {
            sb.append("," + String.valueOf(node.left.val));
            queue.add(node.left);
          }
          if(node.right == null) { sb.append(",null"); }
          else {
            sb.append("," + String.valueOf(node.right.val));
            queue.add(node.right);
          }
        }
      }
      return sb.toString();
    }

    // Decodes your encoded data to tree.
    //BFS
    public TreeNode deserialize(String data) {
      if (data.equals("null")) {
        return null;
      } else {
        Deque<TreeNode> queue = new ArrayDeque<>();
        String[] tks = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(tks[0]));
        queue.add(root);
        int ptr = 1;
        while (!queue.isEmpty() && ptr < tks.length) {
          int size = queue.size();
          for (int i = 0; i < size; i++) {
            TreeNode node = queue.poll();
            node.left = tks[ptr].equals("null") ? null : new TreeNode(Integer.parseInt(tks[ptr]));
            if(node.left != null) { queue.add(node.left); }
            ptr++;
            node.right = tks[ptr].equals("null") ? null : new TreeNode(Integer.parseInt(tks[ptr]));
            if(node.right != null) { queue.add(node.right); }
            ptr++;
          }
        }
        return root;
      }
    }

    private final String spliter = ",";
    private final String nullVal = "null";
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
      StringBuilder sb = new StringBuilder();
      //recur preorder trav
      recur(root, sb);
      return sb.toString();
    }

    private void recur(TreeNode node, StringBuilder sb) {
      //base case
      if (node == null) {
        sb.append(nullVal);
      } else {
        sb.append(node.val);
        sb.append(spliter)
        recur(node.left, sb);
        sb.append(spliter)
        recur(node.right, sb);
      }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
      Deque<String> tks = new ArrayDeque<>(Arrays.asList(data.split(spliter)));
      return buildTree(tks);
    }

    private TreeNode buildTree(Deque<String> tks) {
      String valStr = tks.poll();
      if (valStr.equals(nullVal)) {
        return null;
      } else {
        TreeNode node = new TreeNode(Integer.parseInt(valStr));
        node.left = buildTree(tks);
        node.right = buildTree(tks);
      }
      return node;
    }
  }

  // Your Codec object will be instantiated and called as such:
  // Codec codec = new Codec();
  // codec.deserialize(codec.serialize(root));
