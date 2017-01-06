  //LeetCode 23 Merge k Sorted Lists
  //Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
  /**
   * Definition for singly-linked list.
   * public class ListNode {
   *     int val;
   *     ListNode next;
   *     ListNode(int x) { val = x; }
   * }
   */

//k lists, totol node number = n
//use heap, time = O(k + n(logk)) = O(nlogk), space = O(k)
 public ListNode mergeKLists(ListNode[] lists) {
   PriorityQueue<ListNode> minHeap = new PriorityQueue<>((n1, n2) -> {
     return n1.val - n2.val;
   });
   ListNode preHead = new ListNode(0);
   ListNode cur = preHead;
   for(ListNode head : lists) {
     //avoid NullPointerException
     if(head != null) {
       minHeap.add(head);
     }
   }
   while(!minHeap.isEmpty()) {
     ListNode minNode = minHeap.poll();
     cur.next = minNode;
     cur = minNode;
     if(minNode.next != null) {
       minHeap.add(minNode.next);
     }
   }
   return preHead.next;
 }

//Divide & Conquer similar to merge sort. time = O(nlogn), memory = O(n)


//Followup, merge k sorted arrays.
//Use min heap, since we will add and poll every num once,
//time complexity = O(nlogk), n = sum of all nums, k = rowNum
public static int[] mergeArrs(int[][] inputs) {
  assert inputs != null;
  int rowNum = inputs.length;
  assert inputs[0] != null;
  //Java Lambda, and have initial capacity for this priority queue
  PriorityQueue<Node> minHeap = new PriorityQueue<>(rowNum,
      (Node n1, Node n2) -> n1.val - n2.val);
  int[] ptrs = new int[inputs.length];
  int count = 0;
  for (int r = 0; r < rowNum; r++) { //rowNum * log(n)
    int[] curRow = inputs[r];
    count += curRow.length;
    if (ptrs[r] < curRow.length) {
      int val = curRow[ptrs[r]];
      Node node = new Node(val, r);
      minHeap.add(node); //insert ele into heap log(n)
    }
  }
  int cursor = 0;
  int[] ans = new int[count];
  while (minHeap.size() > 0) {
    Node minNode = minHeap.poll();
    ans[cursor++] = minNode.val;
    int row = minNode.rowIdx;
    int[] curRow = inputs[row];
    ptrs[row]++;
    int ptr = ptrs[row];

    if (ptr < curRow.length) {
      Node node = new Node(curRow[ptr], row);
      minHeap.add(node);
    }
  }
  return ans;
}

private static class Node {
  int val;
  int rowIdx;

  Node(int val, int rowIdx) {
    this.val = val;
    this.rowIdx = rowIdx;
  }
}

//Divide and Conquer. From the recursion tree, we could also find
//that the time complexity is the same as the first method.
public static int[] mergeNArrays(int[][] inputs) {
  assert inputs != null;
  int rowNum = inputs.length;
  if (rowNum == 0) {
    return new int[0];
  }
  return mergeNArrays(inputs, 0, rowNum - 1);
}

private static int[] mergeNArrays(int[][] inputs, int lowRowIdx, int highRowIdx) {
  //base case
  if (lowRowIdx == highRowIdx) {
    return inputs[lowRowIdx];
  }
  if (highRowIdx == lowRowIdx + 1) {
    return mergeTwoArrays(inputs[lowRowIdx], inputs[highRowIdx]);
  }
  int midRowIdx = lowRowIdx + (highRowIdx - lowRowIdx) / 2;
  int[][] leftHalf = Arrays.copyOfRange(inputs, lowRowIdx, midRowIdx);
  int[][] rightHalf = Arrays.copyOfRange(inputs, midRowIdx, highRowIdx);
  return mergeTwoArrays(mergeNArrays(leftHalf), mergeNArrays(rightHalf));
}

private static int[] mergeTwoArrays(int[] arr1, int[] arr2) {
  int[] res = new int[arr1.length + arr2.length];
  int ptr1 = 0, ptr2 = 0;
  int cursor = 0;
  while (ptr1 < arr1.length && ptr2 < arr2.length) {
    res[cursor++] = arr1[ptr1] < arr2[ptr2] ? arr1[ptr1++] : arr2[ptr2++];
  }
  if (ptr1 == arr1.length) {
    while (ptr2 < arr2.length) {
      res[cursor++] = arr2[ptr2++];
    }
  } else {
    while (ptr1 < arr1.length) {
      res[cursor++] = arr1[ptr1++];
    }
  }
  return res;
}
