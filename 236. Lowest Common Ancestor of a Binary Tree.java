  // 236. Lowest Common Ancestor of a Binary Tree
  // Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
  //
  // According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”
  //
  //         _______3______
  //        /              \
  //     ___5__          ___1__
  //    /      \        /      \
  //    6      _2       0       8
  //          /  \
  //          7   4
  // For example, the lowest common ancestor (LCA) of nodes 5 and 1 is 3. Another example is LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.

  //Recursive
  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
      if(root == null || root == p || root == q) { return root; }
      TreeNode lTree = lowestCommonAncestor(root.left, p, q);
      TreeNode rTree = lowestCommonAncestor(root.right, p, q);
      if(lTree != null && rTree != null) { return root; }
      if(lTree == null) { return rTree; }
      else { return lTree; }
  }

  //Iterative
  // To find the lowest common ancestor, we need to find where is p and q and a way to track their ancestors. A parent pointer for each node found is good for the job. After we found both p and q, we create a set of p's ancestors. Then we travel through q's ancestors, the first one appears in p's is our answer.

  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    //have to use map instead of two lists to keep track of ancestors
    //if two lists, it will contain some non-ancestor nodes.
    Map<TreeNode, TreeNode> parent = new HashMap<>();
    Deque<TreeNode> stack = new ArrayDeque<>();
    parent.put(root, null);
    stack.push(root);

    while (!parent.containsKey(p) || !parent.containsKey(q)) {
        TreeNode node = stack.pop();
        if (node.left != null) {
            parent.put(node.left, node);
            stack.push(node.left);
        }
        if (node.right != null) {
            parent.put(node.right, node);
            stack.push(node.right);
        }
    }
    Set<TreeNode> ancestors = new HashSet<>();
    while (p != null) {
        ancestors.add(p);
        p = parent.get(p);
    }
    while (!ancestors.contains(q))
        q = parent.get(q);
    return q;
  }
