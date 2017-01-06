// LeetCode 116 & 117. - Populating Next Right Pointers in Each Node I & II
// Given a binary tree
//
//     struct TreeLinkNode {
//       TreeLinkNode *left;
//       TreeLinkNode *right;
//       TreeLinkNode *next;
//     }
// Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
//
// Initially, all next pointers are set to NULL.
//
// Note:
//
// You may only use constant extra space.
// You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
// For example,
// Given the following perfect binary tree,
//          1
//        /  \
//       2    3
//      / \  / \
//     4  5  6  7
// After calling your function, the tree should look like:
//          1 -> NULL
//        /  \
//       2 -> 3 -> NULL
//      / \  / \
//     4->5->6->7 -> NULL


// class TreeLinkNode{
// 	int val;
// 	TreeLinkNode next, left, right;
// 	TreeLinkNode(int x){ val = x; }
// }

//perfect binary tree
//1. we can use DFS or BFS with queue to solve it, but it needs extra space in call stack or in method itself.
//2. So we have to use iterative level-by-level traversal.
//Common method for a normal binary tree instead of a perfect binary tree.
//time = O(n), in place.
public void connect(TreeLinkNode root) {
	TreeLinkNode cur = root; //cur node in current level.
	//level by level traversal
	//stand in the current level where the next right pointers have been populated,
	//then try to handle the next level.
	while(cur != null) {
		//nextLvlHead - the head of next level, leftSibling - left sibling of current node in next level.
		//key!!!! No easy way to know the right sibling of current node in next lvl, so we keep track of
		//the previous one - left sibling.
		TreeLinkNode nextLvlHead = null, leftSibling = null;
		while(cur != null) {
			if(cur.left != null) {
				nextLvlHead = nextLvlHead == null ? cur.left : nextLvlHead;
				if(leftSibling != null) {
					leftSibling.next = cur.left;
				}
				leftSibling = cur.left;
			}
			if(cur.right != null) {
				nextLvlHead = nextLvlHead == null ? cur.right : nextLvlHead;
				if(leftSibling != null) {
					leftSibling.next = cur.right;
				}
				leftSibling = cur.right;
			}
			cur = cur.next;
		}
		cur = nextLvlHead;
	}
}

//Recursion - DFS for perfect binary tree.
public void connect(TreeLinkNode root) {
	connect(root, null);
}

private void connect(TreeLinkNode root, TreeLinkNode rightSib) {
	if(root == null) {
		return;
	}
	root.next = rightSib;
	connect(root.left, root.right);
	connect(root.right, rightSib == null ? null : rightSib.left);
}

//use constant space, for the perfect binary tree
public static void connnectInPlace(TreeLinkNode root){
	TreeLinkNode leftMost = root;
	while(leftMost != null){
		TreeLinkNode cur = leftMost;
		while(cur.left != null){
			cur.left.next = cur.right;
			if(cur.next != null){
				cur.right.next = cur.next.left;
				cur = cur.next;
			}else{
				break;
			}
		}
		leftMost = leftMost.left;
	}
}
