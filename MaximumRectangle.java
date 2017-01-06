/*Leetcode - 85
go from the top to bottom row, see each row as the ground, and build up the
histograms like Leetcode - 84. Then find the max rectangle which has bottom
side on the "ground".
time = O(n^2), space = O(n)
*/
public int maximalRectangle(char[][] matrix) {
  assert matrix != null;
  int rowNum = matrix.length;
  int colNum = 0;
  if (rowNum > 0) {
    colNum = matrix[0].length;
  }
  int[] preHeights = new int[colNum], curHeights = new int[colNum];
  Deque<Integer> stack = new ArrayDeque<>();
  int maxRec = 0;
  for (int row = 0; row < rowNum; row++) {
    stack.clear();
    for (int i = 0; i < colNum; i++) {
      curHeights[i] = matrix[row][i] == '0' ? 0 : 1 + preHeights[i];
    }
    for (int i = 0; i <= colNum; i++) {
      int curHt = i == colNum ? 0 : curHeights[i];
      if (stack.isEmpty() || curHt > curHeights[stack.peek()]) {
        stack.push(i);
      } else {
        while (!stack.isEmpty() && curHeights[stack.peek()] > curHt) {
          int popIdx = stack.pop();
          int leftEdge = stack.isEmpty() ? -1 : stack.peek();
          int rightEdge = i;
          int area = curHeights[popIdx] * (rightEdge - leftEdge - 1);
          maxRec = Math.max(maxRec, area);
        }
        stack.push(i);
      }
    }
    preHeights = curHeights;
  }
  return maxRec;
}

public int maximalRectangle_DP(char[][] matrix) {
  int area = 0;
  int rowNum = matrix.length;
  if (rowNum == 0) {
    return area;
  }
  int colNum = matrix[0].length;
  //height - the number of succeesive 1's above including the current one
  //left, right - the left and right boundaries of the rectangle which contains the current
  //point with the height of height[c]
  int[] height = new int[colNum], left = new int[colNum], right = new int[colNum];
  Arrays.fill(height, 0);
  Arrays.fill(left, 0);
  Arrays.fill(right, colNum - 1);
  for (int r = 0; r < rowNum; r++) {
    int curLeft = 0, curRight = colNum - 1;
    for (int c = 0; c < colNum; c++) {
      int num = matrix[r][c];
      if (num == '0') {
        height[c] = 0;
        left[c] = 0;
        curLeft = c + 1;
      } else {
        height[c]++;
        left[c] = Math.max(left[c], curLeft);
      }
    }

    for (int c = colNum - 1; c >= 0; c--) {
      int num = matrix[r][c];
      if (num == '0') {
        right[c] = colNum - 1;
        curRight = c - 1;
      } else {
        right[c] = Math.min(right[c], curRight);
      }
    }

    for (int c = 0; c < colNum; c++) {
      area = Math.max(area, height[c] * (right[c] - left[c] + 1));
    }
  }
  return area;
}
