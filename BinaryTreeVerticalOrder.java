//LeetCode 314. Binary Tree Vertical Order Traversal
// Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).
//
// If two nodes are in the same row and column, the order should be from left to right.
//
// Examples:
//
// Given binary tree [3,9,20,null,null,15,7],
//    3
//   /\
//  /  \
//  9  20
//     /\
//    /  \
//   15   7
// return its vertical order traversal as:
// [
//   [9],
//   [3,15],
//   [20],
//   [7]
// ]
// Given binary tree [3,9,8,4,0,1,7],
//      3
//     /\
//    /  \
//    9   8
//   /\  /\
//  /  \/  \
//  4  01   7
// return its vertical order traversal as:
// [
//   [4],
//   [9],
//   [3,0,1],
//   [8],
//   [7]
// ]
// Given binary tree [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5),
//      3
//     /\
//    /  \
//    9   8
//   /\  /\
//  /  \/  \
//  4  01   7
//     /\
//    /  \
//    5   2
// return its vertical order traversal as:
// [
//   [4],
//   [9,5],
//   [3,0,1],
//   [8,2],
//   [7]
// ]
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

 //BFS, time = O(n), space = O(n)
 //DFS doesn't work
  //     10
  //   /   \
  //  1     3
  //   \
  //     2
  //      \
  //        5
  //5 is before 3 for the related column.
 //keep track of column # for each tree node.
 class NodeWrapper {
   TreeNode node;
   int col;
   NodeWrapper(TreeNode n, int c) {
     node = n;
     col = c;
   }
 }
 public List<List<Integer>> verticalOrder(TreeNode root) {
   List<List<Integer>> ans = new ArrayList<>();
   //map<column #, list of tree nodes>
   Map<Integer, List<Integer>> colMap = new HashMap<>();
   //BFS, queue
   Deque<NodeWrapper> queue = new ArrayDeque<>();
   if(root != null) {
     queue.add(new NodeWrapper(root, 0));
   }
   //keep track of the range of col.
   int minCol = Integer.MAX_VALUE, maxCol = Integer.MIN_VALUE;

   while(!queue.isEmpty()) {
     int lvlSize = queue.size();
     for(int i = 0; i < lvlSize; i++) {
       NodeWrapper cur = queue.poll();
       int col = cur.col;
       minCol = Math.min(minCol, col);
       maxCol = Math.max(maxCol, col);
       TreeNode node = cur.node;
       colMap.putIfAbsent(col, new ArrayList<>());
       colMap.get(col).add(node.val);
       if(node.left != null) {
         queue.add(new NodeWrapper(node.left, col - 1));
       }
       if(node.right != null) {
         queue.add(new NodeWrapper(node.right, col + 1));
       }
     }
   }
   for(int c = minCol; c <= maxCol; c++) {
     ans.add(colMap.get(c));
   }
   return ans;
 }
