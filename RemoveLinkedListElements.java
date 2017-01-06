public class RemoveLinkedListElements {
	private class ListNode {
		int val;
		ListNode next;
		public ListNode(int x) { val = x; }
	}

	public ListNode removeElements(ListNode head, int val) {
		ListNode dummy = new ListNode(0);
		ListNode pre = new ListNode(0);
		dummy.next = pre;
		pre.next = head;
		while(head != null) {
			if (head.val == val) {
				pre.next = head.next;
			} else {
				pre = head;
				pre.next = head.next;
			}
			head = head.next;
		}
		return dummy.next.next;
	}

	public ListNode removeElements_better(ListNode head, int val) {
		if(head == null) { return head; }
		ListNode pointer = head;
		while(pointer.next != null) {
			if (pointer.next.val == val) {
				pointer.next = pointer.next.next;
			} else { 
				pointer = pointer.next; 
			}
		}
		return head.val == val ? head.next : head;
	}
}