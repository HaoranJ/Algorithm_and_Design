import java.util.*;

public class LRUCache {
  /*
  The reason we don't use LinkedList library in Java is #remove(Object ob) method
  needs linear time to operate instead of constant time. We have our own #remove method
  to remove an element in constant time. 
  */
  private static class Node {
    int key;
    int val;
    Node pred;
    Node succ;
    Node(int k, int v) {
      key = k;
      val = v;
    }
  }

  private Map<Integer, Node> map;
  private Node head;
  private Node tail;
  private int capacity, size;
  public LRUCache(int capacity) {
    this.size = 0;
    this.capacity = capacity;
    map = new HashMap<>();
    head = new Node(0,0);
    tail = new Node(0,0);
    head.pred = null;
    head.succ = tail;
    tail.pred = head;
    tail.succ = null;
  }

  private void remove(Node node) {
    Node pre = node.pred;
    Node suc = node.succ;
    pre.succ = suc;
    suc.pred = pre;
    node.pred = null;
    node.succ = null;
  }

  private void addFirst(Node node) {
    Node oldFirst = head.succ;
    head.succ = node;
    node.pred = head;
    node.succ = oldFirst;
    oldFirst.pred = node;
  }

  private Node removeLast() {
    Node last = tail.pred;
    Node newLast = tail.pred.pred;
    newLast.succ = tail;
    tail.pred = newLast;
    last.pred = null;
    last.succ = null;
    return last;
  }

  public int get(int key) {
    if (map.containsKey(key)) {
      Node node = map.get(key);
      //move node which is just used to first
      remove(node);
      addFirst(node);
      return node.val;
    } else {
      return -1;
    }
  }

  public void set(int key, int value) {
    if (map.containsKey(key)) {
      Node node = map.get(key);
      node.val = value;
      //move node just used to first
      remove(node);
      addFirst(node);
    } else {
      Node node = new Node(key, value);
      map.put(key, node);
      //add node to first
      addFirst(node);
      size++;
      if (size > 0 && size > capacity) {
        //remove last
        Node last = removeLast();
        //update hashmap
        map.remove(last.key, last);
        size--;
      }
    }
  }
}
