import java.util.*;

public class ConvertSortedListToBST {
	//time = O(n)
	public TreeNode sortedListToBST(ListNode head) {
		int len = 0;
		ListNode dummy = head;
		while (dummy != null && dummy.next != null) {
			len++;
			dummy = dummy.next;
		}
		return recursiveHalve(head, len);
	}

	private TreeNode recursiveHalve(ListNode head, int len) {
		//base case
		if (head == null || len < 0) {
			return null;
		}
		int mid = len / 2;
		ListNode lnode = head;
		while (mid > 0) {
			lnode = lnode.next;
			mid--;
		}
		TreeNode root = new TreeNode(lnode.val);
		root.left = recursiveHalve(head, (len/2)-1);
		root.right = recursiveHalve(lnode.next, len-(len/2)-1);
		return root;
	}
}