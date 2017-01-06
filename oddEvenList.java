//LeetCode 328
//time = O(n), space = O(1)

public ListNode oddEvenList(ListNode head) {
  if (head == null) { return head; }
  ListNode evenHead = head.next;
  ListNode oddCur = head, evenCur = head.next;
  while (evenCur != null) {
    ListNode oddSuc = evenCur.next == null ? evenHead : evenCur.next;
    ListNode evenSuc = evenCur.next == null ? null : oddSuc.next;
    oddCur.next = oddSuc;
    evenCur.next = evenSuc;
    oddCur = oddSuc;
    evenCur = evenSuc;
  }
  if (oddCur != evenHead && oddCur.next != evenHead) {
    oddCur.next = evenHead;
  }
  return head;
}
