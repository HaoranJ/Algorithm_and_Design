//LeetCode 138. Copy List with Random Pointer
// A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
//
// Return a deep copy of the list.

/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */

//HashMap to map the initial nodes and copy nodes.
//time = O(n), space = O(n)
public RandomListNode copyRandomList(RandomListNode head) {
  Map<RandomListNode, RandomListNode> map = new HashMap<>();
  RandomListNode node = head;
  while (node != null) {
    map.put(node, new RandomListNode(node.label));
    node = node.next;
  }
  node = head;
  while (node != null) {
    map.get(node).next = map.get(node.next);
    map.get(node).random = map.get(node.random);
    node = node.next;
  }
  return map.get(head);
}

//Modify the initial structure, append the copy node to its initial node first, and finish random pointer copying.
//then split it into two lists.
//time = O(n), space = O(1)
//initial list - 1->2->3 (1.random = 3, 2.random = 2)
public RandomListNode copyRandomList(RandomListNode head) {
  RandomListNode cur = head;
  //1->1'->2->2'->3->3'
  while(cur != null) {
    RandomListNode copy = new RandomListNode(cur.label);
    RandomListNode iniNext = cur.next;
    cur.next = copy;
    copy.next = iniNext;
    cur = iniNext;
  }

  // finish random pointers copying.
  cur = head;
  while(cur != null) {
    RandomListNode copyCur = cur.next;
    copyCur.random = cur.random == null ? null : cur.random.next;
    cur = copyCur.next;
  }

  //split the list into odd and even element lists.
  RandomListNode newHead = head == null ? null : head.next;
  RandomListNode p1 = head, p2 = newHead;
  RandomListNode nextP1, nextP2;
  while(p1 != null) {
    nextP1 = p2.next;
    nextP2 = nextP1 == null ? null : nextP1.next;
    p1.next = nextP1;
    p2.next = nextP2;
    p1 = nextP1;
    p2 = nextP2;
  }
  return newHead;
}
