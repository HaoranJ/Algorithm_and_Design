//http://www.geeksforgeeks.org/largest-rectangle-under-histogram/
/*Leetcode - 84.
1. brute-force - O(n^2)
2. Divide and Conquer + Segment tree - O(nlogn)
3. Stack - O(n)
*/
public int largestRectangleArea(int[] heights) {
  Deque<Integer> stack = new ArrayDeque<>();
  int maxArea = 0;
  for (int i = 0; i <= heights.length; i++) {
    int curHeight = i == heights.length ? 0 : heights[i];
    if (stack.isEmpty() || curHeight > heights[stack.peek()]) {
      stack.push(i);
    } else {
      while (!stack.isEmpty() && heights[stack.peek()] > curHeight) {
        int popIdx = stack.pop();
        int leftEdge = stack.isEmpty() ? -1 : stack.peek();
        int rightEdge = i;
        int area = heights[popIdx] * (rightEdge - leftEdge - 1);
        maxArea = Math.max(maxArea, area);
      }
      stack.push(i);
    }
  }
  return maxArea;
}
