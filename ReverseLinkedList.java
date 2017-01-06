//LeetCode 206

//Recursion
public ListNode reverseList(ListNode head) {
  //base case
  if (head == null || head.next == null) {
    return head;
  }
  ListNode newHead = reverseList(head.next);
  head.next.next = head;
  head.next = null;
  return newHead;
}

//Iteration
public ListNode reverseList(ListNode head) {
  ListNode pre = null, cur = head;
  while (cur != null) {
    ListNode suc = cur.next;
    cur.next = pre;
    pre = cur;
    cur = suc;
  }
  return pre;
}
