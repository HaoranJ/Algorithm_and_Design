//Definition of singly-linked list
class ListNode{
	int val;
	ListNode next;
	ListNode(int x){ val = x; }
}

public class AddTwoNumbers{
	public static void main(String[] args) {
		ListNode l1 = new ListNode(2), l2 = new ListNode(5),n1 = new ListNode(4), n2 = new ListNode(3), r1 = new ListNode(6), r2 = new ListNode(4);
		l1.next = n1; //n1.next = n2;
		l2.next = r1; r1.next = r2;
		display(solution(l1,l2));
	}
	public static ListNode solution(ListNode l1, ListNode l2){
		ListNode p1 = l1, p2 = l2, dummy = new ListNode(0), p = dummy;
		int carry = 0;
		while(p1 != null || p2 != null){
			int x = carry;
			if (p1 != null) {
				x += p1.val;
				p1 = p1.next;
			}
			if (p2 != null) {
				x += p2.val;
				p2 = p2.next;
			}
			int v = x % 10; carry = x / 10;
			ListNode n = new ListNode(v);
			p.next = n; p = p.next;
		}
		if (carry == 1) {
			ListNode tail = new ListNode(1);
			p.next = tail;
		}
		return dummy.next;
	}
	private static void display(ListNode node){
		while(node != null){
			System.out.print(node.val + " ");
			node = node.next;
		}
	}
}