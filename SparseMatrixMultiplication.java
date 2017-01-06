import java.util.*;

public class SparseMatrixMultiplication {
  /*
  LeetCode 311, Sparse Matrix Multiplication
  */
  /*
  the inplace method
  time = O(mnr), space = O(1)
  */
  public int[][] multiply(int[][] A, int[][] B) {
    int resRow = A.length, resCol = B[0].length;
    int ARow = A[0].length;
    int[][] res = new int[resRow][resCol];
    for(int ro = 0; ro < resRow; ro++) {
      int[] row = new int[resCol];
      for(int i = 0; i < ARow; i++) {
        if(A[ro][i] != 0) {
          for(int cl = 0; cl < resCol; cl++) {
            row[cl] += A[ro][i] * B[i][cl];
          }
        }
      }
      res[ro] = row;
    }
    return res;
  }

  // 2nd method, a sparse matrix can be represented as a sequence of rows,
  // each of which is a sequence of (column-idx, value) pairs of non-zero values
  // in the row.
  // time = O(mnr), space = o(n)
  public int[][] multiply(int[][] A, int[][] B) {
    int resRow = A.length, int resCol = B[0].length;
    int ARow = A[0].length;
    int[][] res = new int[resRow][resCol];
    Map<Integer, Integer> map = new HashMap<>();
    for(int ro = 0; ro < resRow; ro++) {
      int[] row = new int[resCol];
      map.clear();
      for(int i = 0; i < ARow; i++) {
        if(A[ro][i] != 0) {
          map.put(i, A[ro][i]);
        }
      }
      for(int cl = 0; cl < resCol; cl++) {
        int sum = 0;
        for(Map.Entry<Integer, Integer> e : map.entrySet()) {
          int idx = e.getKey(), num = e.getValue();
          if(B[idx][cl] != 0) {
            sum += B[idx][cl] * num;
          }
        }
        row[cl] = sum;
      }
      res[ro] = row;
    }
    return res;
  }
}
