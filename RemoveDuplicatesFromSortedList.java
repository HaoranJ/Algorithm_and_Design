import java.util.*;

public class RemoveDuplicatesFromSortedList {
	//LeetCode 82
	//two pointers, in place, time = O(n)
	public ListNode deleteDuplicates(ListNode head) {
		ListNode fake = new ListNode(0);
		fake.next = head;
		ListNode root = fake;
		ListNode f = head;
		ListNode p = head;
		while (p != null) {
			while (p != null && p.val == f.val) {
				p = p.next;
			}
			if (f.next == p) {
				root = f;
			} else {
				root.next = p;
			}
			f = p;
		}
		return fake.next;
	}
}