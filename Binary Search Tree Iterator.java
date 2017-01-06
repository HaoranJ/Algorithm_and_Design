// LeetCode - 173 Binary Search Tree Iterator
// Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
//
// Calling next() will return the next smallest number in the BST.
//
// Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.

// 3. implement iterator (hasNext, next) for two BSTs，就是给两棵BST写一个iterator，每次取出最小值.
// next1, next2分别记录BST1， BST2的当前最小值，返回Math.min(next1, next2)后更新next1，next2. 注意hasNext()的写法，因为两颗树都空了之后还要检查next1，next2当中是否还有值。
//
// 4.implement iterator (hasNext, next) for a list of BSTS
// 解法基本同3，用heap存每个BST的当前最小值，这道题没有要求写代码，讲了一下思路，分析了时间和空间复杂度。
/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

//Morris Traversal
public class BSTIterator_MorrisTraversal {
  TreeNode nextNode = null;
  TreeNode root = null;
  public BSTIterator(TreeNode root) {
    this.root = root;
    nextNode = getNext();
  }

  /** @return whether we have a next smallest number */
  //time = O(1)
  public boolean hasNext() {
    return nextNode != null;
  }

  private TreeNode getNext() {
    TreeNode cur = nextNode == null ? root : nextNode.right;
    while(cur != null) {
      if(cur.left == null) {
        return cur;
      } else {
        TreeNode probe = cur.left;
        while(probe.right != null && probe.right != cur) {
          probe = probe.right;
        }
        if(probe.right == null) {
          probe.right = cur;
          cur = cur.left;
        } else if(probe.right == cur) {
          probe.right = null;
          return cur;
        }
      }
    }
    return cur;
  }

  /** @return the next smallest number */
  //time = O(logn)
  public int next() {
    int nextVal = nextNode.val;
    nextNode = getNext();
    return nextVal;
  }
}

//Stack inorder traversal
public class BSTIterator {
  private Deque<TreeNode> stack;
  private TreeNode nextNode = null;
  public BSTIterator(TreeNode root) {
    stack = new ArrayDeque<>();
    TreeNode node = root;
    while(node != null) {
      stack.push(node);
      node = node.left;
    }
    nextNode = getNext();
  }

  /** @return whether we have a next smallest number */
  //time = O(1)
  public boolean hasNext() {
    return nextNode != null;
  }

  /** @return the next smallest number */
  //time = O(logn), space = O(logn)
  public int next() {
    TreeNode ret = nextNode;
    nextNode = getNext();
    return ret.val;
  }

  private TreeNode getNext() {
    TreeNode ret = stack.poll();
    TreeNode node = ret == null ? null : ret.right;
    while(node != null) {
      stack.push(node);
      node = node.left;
    }
    return ret;
  }
}

//Morris Traversal - implement iterator for a list of BSTs
//use min heap to keep track of every next node of all the BSTs.
//k BSTs of size n.
public class BSTIterator_MorrisTraversal {
  PriorityQueue<TreeNode> minHeap;
  public BSTIterator(TreeNode[] roots) {
    minHeap = new PriorityQueue<>((n1, n2) -> {
      return n1.val - n2.val;
    });
    //time = O(klogn + k) = O(klogn)
    for(TreeNode root : roots) {
      //O(logn)
      TreeNode toAdd = getNext(root, true);
      if(toAdd != null) {
        //O(k)
        minHeap.add(toAdd);
      }
    }
  }

  /** @return whether we have a next smallest number */
  //time = O(1)
  public boolean hasNext() {
    return !minHeap.isEmpty();
  }

  /** @return the next smallest number */
  //time = O(logk + logn)
  public int next() {
    TreeNode minNode = minHeap.poll();
    int nextVal = minNode.val;
    TreeNode toAdd = getNext(minNode, false);
    if(toAdd != null) {
      minHeap.add(toAdd);
    }
    return nextVal;
  }

  private TreeNode getNext(TreeNode minNode, boolean initial) {
    TreeNode cur = initial ? minNode : minNode.right;
    while(cur != null) {
      if(cur.left == null) {
        //cur = cur.right;
        return cur;
      } else {
        TreeNode probe = cur.left;
        while(probe.right != null && probe.right != cur) {
          probe = probe.right;
        }
        if(probe.right == null) {
          probe.right = cur;
          cur = cur.left;
        } else if(probe.right == cur) {
          probe.right = null;
          //cur = cur.right;
          return cur;
        }
      }
    }
    return cur;
  }
}
