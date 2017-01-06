import java.util.*;

public class PalindromeLinkedList {
  /*
  234. Palindrome Linked List
  time = O(1), space = O(1)
  */
  public boolean isPalindrome(ListNode head) {
    if(head == null) { return true; }
    int len = 0;
    ListNode probe = head;
    while(probe != null) {
      len++;
      probe = probe.next;
    }
    int mid = len / 2;
    ListNode midNode = head;
    for (int i = 0; i < mid; i++) {
      midNode = midNode.next;
    }
    ListNode tail = reverseList(midNode);
    for (int j = 0; j < mid; j++) {
      if (head.val != tail.val) {
        return false;
      }
      head = head.next;
      tail = tail.next;
    }
    return true;
  }

  private ListNode reverseList(ListNode begin) {
    ListNode cur = begin;
    ListNode post = cur.next;
    cur.next = null;
    while(post != null) {
      ListNode temp = post.next;
      post.next = cur;
      cur = post;
      post = temp;
    }
    return cur;
  }
}
