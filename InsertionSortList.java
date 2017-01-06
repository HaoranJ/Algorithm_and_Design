/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 4    1   3   7
 dummy  1(head)    4     3     7
          ptr           cur  post
 dummy  1(head)    3     4     7
                              cur   post
 */

//time = O(n^2)
public class InsertionSortList {
    public ListNode insertionSortList(ListNode head) {
      ListNode dummy = new ListNode(0);
      dummy.next = head;
      ListNode cur = head;
      while (cur != null) {
        ListNode ptr = dummy;
        ListNode post = cur.next;
        while (ptr.next != null && ptr.next.val < cur.val) {
          ptr = ptr.next;
        }
        if (ptr.next != cur) {
          ListNode pre = ptr;
          while (pre.next != cur) {
            pre = pre.next;
          }
          ListNode ptrPost = ptr.next;
          ptr.next = cur;
          cur.next = ptrPost;
          pre.next = post;
        }
        cur = post;
      }
      return dummy.next;
    }
}
