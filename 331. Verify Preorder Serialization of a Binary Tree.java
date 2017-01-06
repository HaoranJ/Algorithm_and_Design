/*
One way to serialize a binary tree is to use pre-order traversal. When we encounter a non-null node, we record the node's value. If it is a null node, we record using a sentinel value such as #.

     _9_
    /   \
   3     2
  / \   / \
 4   1  #  6
/ \ / \   / \
# # # #   # #
For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", where # represents a null node.

Given a string of comma separated values, verify whether it is a correct preorder traversal serialization of a binary tree. Find an algorithm without reconstructing the tree.

Each comma separated value in the string must be either an integer or a character '#' representing null pointer.

You may assume that the input format is always valid, for example it could never contain two consecutive commas such as "1,,3".

Example 1:
"9,3,4,#,#,1,#,#,2,#,6,#,#"
Return true

Example 2:
"1,#"
Return false

Example 3:
"9,#,#,1"
Return false
*/

/* stack
time = O(n), space = O(n)
*/
public boolean isValidSerialization(String preorder) {
  Deque<String> stack = new ArrayDeque<>();
  String[] nodes = preorder.split(",");
  for (int i = 0; i < nodes.length; i++) {
    String node = nodes[i];
    if (!node.equals("#")) {
      stack.push(node);
    } else {
      while (!stack.isEmpty() && stack.peek().equals("#")) {
        stack.pop();
        // key!!
        if (stack.isEmpty()) { return false; }
        stack.pop();
      }

      // key!! 
      if (i < nodes.length - 1) {
        stack.push(node);
      }
    }
  }
  return stack.isEmpty();
}

/* Outdegree & Indegree
In a binary tree, if we consider null as leaves, then

all non-null node provides 2 outdegree and 1 indegree (2 children and 1 parent), except root
all null node provides 0 outdegree and 1 indegree (0 child and 1 parent).

if a serialization string is correct, outdegree >= indegree at any time and finally outdegree = indegree

time = O(n), space = O(1)
*/
public boolean isValidSerialization(String preorder) {
  if (preorder.isEmpty()) {
    return true;
  }
  String[] nodes = preorder.split(",");
  int outdegree = 0, indegree = -1;
  for (String node : nodes) {
    if (node.equals("#")) {
      indegree++;
      // outdegree >= indegree at any time
      if (outdegree < indegree) { return false; }
    } else {
      indegree++;
      // outdegree >= indegree at any time
      if (outdegree < indegree) { return false; }
      outdegree += 2;
    }
  }

  return outdegree == indegree;
}
