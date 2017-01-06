//LeetCode - 25
//time = O(n), space = O(1)
public ListNode reverseKGroup(ListNode head, int k) {
  ListNode lo = head;
  ListNode newHead = head;
  ListNode preTail = null; //key!!
  while(lo != null) {
    ListNode hi = lo;
    int count = 0;
    //get the k-group
    while(count < k && hi != null) {
      count++;
      hi = hi.next;
    }
    if(count == k) {
      ListNode[] hdtl = reverseAndGetGroupHeadTail(lo, hi);
      if(lo == head) {
        //first k-group is special, we need a new head out of this group.
        newHead = hdtl[0];
      } else {
        preTail.next = hdtl[0];
      }
      //last group's tail should conncect to this group's head.
      preTail = hdtl[1];
      hdtl[1].next = hi;
    }
    lo = hi;
  }
  return newHead;
}

//in-place reverse a linked list.
//BUT!!!! this reverse operation are within the whole parent linked list. the right boundary is hi which is not necessarily null.
private ListNode[] reverseAndGetGroupHeadTail(ListNode lo, ListNode hi) {
  ListNode cur = lo, suc = cur.next;
  while(suc != hi) {//NOT suc != null
    ListNode nxt = suc.next;
    suc.next = cur;
    cur = suc;
    suc = nxt;
  }
  lo.next = null;
  return new ListNode[]{cur, lo};
}
