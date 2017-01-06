import java.util.*;

public class SearchA2DMatrix {
  /*
  LeetCode - 240, search a 2D Matrix
  [
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
  ]
  start with the top right corner, because from right to left - desc, top to bottom - asc
  compare current number with target to rule out the row or column.
  time = O(m+n)
  */
  public boolean searchMatrix(int[][] mat, int t){
    assert mat != null;
    int m = mat.length;
    if(m == 0) return false;
    int n = mat[0].length;
    int r = 0, c = n-1;
    while(c >= 0 && r < m){
      int x = mat[r][c];
      if(x == t) return true;
      else if(x < t) r++;
      else c--;
    }
    return false;
  }
  /*
  first, find the possible columns. - O(logn)
  second, binary search possible columns - worst case = O(nlogm)
  time = O(logn + nlogm), space = O(m)
  */
  public boolean searchMatrix(int[][] mat, int t){
    int m = mat.length;
    if(m == 0) return false;
    int n = mat[0].length;
    int col = bs(mat[0], 0, n-1, t);
    if(col == -2)
    return true;
    if(col == -1)
    return false;
    for(int i = col; i >= 0; i--) {
      int[] ar = new int[m];
      for(int k = 0; k < m; k++){
        ar[k] = mat[k][i];
      }
      if(Arrays.binarySearch(ar, t) >= 0)
      return true;
    }
    return false;
  }

  private int bs(int[] ar, int lo, int hi, int t){
    if(lo > hi) return -1;
    if(ar[hi] < t) return hi;
    if(ar[hi] == t) return -2;
    int mid = (lo + hi) / 2;
    int x = ar[mid];
    int y = mid+1 == ar.length ? Integer.MAX_VALUE : ar[mid+1];
    if(x == t || y == t)
    return -2;
    else if(x < t && t < y)
    return mid;
    else if(t < x)
    return bs(ar, lo, mid-1, t);
    else return bs(ar, mid+1, hi, t);
  }
}
