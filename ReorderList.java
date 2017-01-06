/*LeetCode 143
Reverse second half, and then merge
time = O(n), space = 0(1)
*/
public void reorderList(ListNode head) {
  if (head == null || head.next == null) {
    return;
  }
  ListNode[] twoHeads = reverseSecondHalf(head);
  merge(twoHeads);
}

private ListNode[] reverseSecondHalf(ListNode head) {
  ListNode[] twoHeads = new ListNode[2];
  int len = 0;
  ListNode cur = head;
  while (cur != null) {
    cur = cur.next;
    len++;
  }
  cur = head;
  for (int i = 0; i < len/2-1; i++) {
    cur = cur.next;
  }
  ListNode halfHead = cur.next;
  cur.next = null;
  twoHeads[0] = head;
  head2 = reverseList(halfHead);
  twoHeads[1] = head2;
  return twoHeads;
}

private ListNode reverseList(ListNode head) {
  if(head == null) { return head; }
  ListNode pre = head;
  ListNode cur = head.next;
  pre.next = null;
  while (cur != null) {
    ListNode suc = cur.next;
    cur.next = pre;
    pre = cur;
    cur = suc;
  }
  return pre;
}

private void merge(ListNode[] twoHeads) {
  ListNode hd1 = twoHeads[0];
  ListNode hd2 = twoHeads[1];
  ListNode nextHd1, nextHd2;
  while (hd1 != null && hd2 != null) {
    nextHd1 = hd1.next;
    nextHd2 = hd2.next;
    hd1.next = hd2;
    if(nextHd1 == null) { break; }
    hd2.next = nextHd1;
    hd1 = nextHd1;
    hd2 = nextHd2;
  }
}
