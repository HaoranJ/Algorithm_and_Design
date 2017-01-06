import java.util.*;
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class LinkedListCycle {
	//LeetCode 141
	public boolean hasCycle(ListNode head) {
		ListNode walker = head;
		ListNode runner = head;
		while (walker != null && runner != null) {
			walker = walker == null ? null : walker.next;
			runner = runner == null ? null : runner.next;
			runner = runner == null ? null : runner.next;
			if (walker == null && runner == null) {
		    return false;
			} 
			if (walker == runner) {
				return true;
			}
		}
		return false;
	}

	//LeetCode 142
	/*
	When walker and runner firstly met, walker took k steps, runner took 2k steps.
	c = the nodes in the cycle. 2k - k = c.
	s = the steps needed from head to cycle-start node
	m = the steps needed from cycle-start node to walker-runner-first-meet node
	we have s = c - m
	*/
	private static ListNode walker;
	private static ListNode runner;
	public ListNode detectCycle(ListNode head) {
		ListNode cur = head;
		if (isCycled(head)) {
			while (cur != walker) {
				cur = cur.next;
				walker = walker.next;
			}
			return cur;
		}
		return null;
	}

	private boolean isCycled(ListNode head) {
		walker = head;
		runner = head;
		while (walker != null && runner != null) {
			walker = walker == null ? null : walker.next;
			runner = runner == null ? null : runner.next;
			runner = runner == null ? null : runner.next;
			if (walker == null && runner == null) {
				return false;
			}
			if (walker == runner) {
				return true;
			}
		}
		return false;
	}
}