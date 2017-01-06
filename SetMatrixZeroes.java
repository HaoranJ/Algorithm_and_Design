import java.util.*;

public class SetMatrixZeroes {
  /*
  LeetCode 73, set matrix zeroes.
  To do it in place, we must store the intial states within the matrix itself.
  We can store the state of the row in its leftmost element, and store the state of
  the column in its top element. Because first row and first column have their
  "state head" overlapped, we store the state of first column in int col0.
  After store all the states, we set zeroes.
  time = O(mn), space = O(1)
  */
  public void setZeroes(int[][] matrix) {
    assert matrix != null;
    int m = matrix.length;
    int n = matrix[0].length;
    int col0 = 1;
    for (int i = 0; i < m; i++) {
      if (matrix[i][0] == 0) {
        col0 = 0;
      }
      for (int j = 1; j < n; j++) {
        if (matrix[i][j] == 0) {
          matrix[i][0] = 0;
          matrix[0][j] = 0;
        }
      }
    }

    for (int j = 1; j < n; j++) {
      if (matrix[0][j] == 0) {
        for (int i = 1; i < m; i++) {
          matrix[i][j] == 0;
        }
      }
    }

    for (int i = 0; i < m; i++) {
      if (matrix[i][0] == 0) {
        Arrays.fill(matrix[i], 0);
      }
    }
    if (col0 == 0) {
      for (int i = 0; i < m; i++) {
        matrix[i][0] == 0;
      }
    }
  }
}
