import java.util.*;

public class ClosestBinarySearchTreeValueII {
  /*
  LeetCode 272 - Closest Binary Search Tree Value II
  First to find out the closest node, and then use getSuccessor() and getPredecessor()
  to get the closest k values.
  time = O(klogn), space = O(logn) -- use stack to keep track of the path from the root to
  the current node, in this way, we can find the parent node of every node.
  */
  public List<Integer> closestKValues(TreeNode root, double target, int k) {
    TreeNode closest = getClosest(root, target);
    int c = 1;
    List<Integer> ans = new ArrayList<>();
    ans.add(closest.val);
    TreeNode pred = getPredecessor(root, closest);
    TreeNode suc = getSuccessor(root, closest);
    while (c < k) {
      if (pred == null) {
        ans.add(suc.val);
        suc = getSuccessor(root, suc);
      } else if (suc == null) {
        ans.add(pred.val);
        pred = getPredecessor(root, pred);
      } else if (Math.abs((double)pred.val-target) < Math.abs((double)suc.val-target)) {
        ans.add(pred.val);
        pred = getPredecessor(root, pred);
      } else {
        ans.add(suc.val);
        suc = getSuccessor(root, suc);
      }
      c++;
    }
    return ans;
  }

  private TreeNode getPredecessor(TreeNode root, TreeNode node) {
    if (node.left != null) {
      node = node.left;
      while (node.right != null) {
        node = node.right;
      }
      return node;
    }
    Deque<TreeNode> stack = new ArrayDeque<>();
    while (root != node) {
      stack.push(root);
      root = root.val > node.val ? root.left : root.right;
    }
    while (!stack.isEmpty()) {
      TreeNode parent = stack.pop();
      if (parent.right == node) {
        return parent;
      }
      //Don't forget to update node.
      node = parent;
    }
    return null;
  }

  private TreeNode getSuccessor(TreeNode root, TreeNode node) {
    if (node.right != null) {
      node = node.right;
      while (node.left != null) {
        node = node.left;
      }
      return node;
    }
    Deque<TreeNode> stack = new ArrayDeque<>();
    while (root != node) {
      stack.push(root);
      root = root.val > node.val ? root.left : root.right;
    }
    while (!stack.isEmpty()) {
      TreeNode parent = stack.pop();
      if (parent.left == node) {
        return parent;
      }
      node = parent;
    }
    return null;
  }

  private TreeNode getClosest(TreeNode root, double t) {
    TreeNode ret = null;
    double diff = Double.MAX_VALUE;
    while (root != null) {
      double v = (double)root.val;
      if (v == t) {
        return root;
      }
      if(Math.abs(v-t) < diff) {
        ret = root;
        diff = Math.abs(v-t);
      }
      root = v > t ? root.left : root.right;
    }
    return ret;
  }


  /*
  Use morris traversal to inorder traverse the BST, and use a max heap to contains
  the closest k values.
  time = O(n + klgk), space = O(k)
  */
  public static class Pair {
    double diff;
    int val;
    public Pair(double d, int v) {
      diff = d;
      val = v;
    }
  }

  public static class SortByDiff implements Comparator<Pair> {
    @Override
    public int compare(Pair p1, Pair p2) {
      return p2.diff < p1.diff ? -1 : (p2.diff == p1.diff ? 0 : 1);
    }
  }
  PriorityQueue<Pair> maxHeap;

  public List<Integer> closestKValues_Heap_Morris(TreeNode root, double target, int k) {
    maxHeap = new PriorityQueue<>(new SortByDiff());
    morris(root, target, k);
    List<Integer> list = new ArrayList<>();
    Iterator<Pair> itr = maxHeap.iterator();
    while (itr.hasNext()) {
      list.add(itr.next().val);
    }
    return list;
  }

  private void morris(TreeNode root, double target, int k) {
    while (root != null) {
      if (root.left == null) {
        double diff = Math.abs((double)root.val - target);
        if(maxHeap.size() < k){
          maxHeap.add(new Pair(diff, root.val));
        } else if (diff < maxHeap.peek().diff) {
          maxHeap.poll();
          maxHeap.add(new Pair(diff, root.val));
        }
        root = root.right;
      } else {
        TreeNode temp = root.left;
        while (temp.right != null && temp.right != root) {
          temp = temp.right;
        }
        if (temp.right == root) {
          temp.right = null;
          double diff = Math.abs((double)root.val - target);
          if(maxHeap.size() < k){
            maxHeap.add(new Pair(diff, root.val));
          } else if (diff < maxHeap.peek().diff) {
            maxHeap.poll();
            maxHeap.add(new Pair(diff, root.val));
          }
          root = root.right;
        } else {
          temp.right = root;
          root = root.left;
        }
      }
    }
  }
}
