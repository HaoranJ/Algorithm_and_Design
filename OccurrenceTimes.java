import java.util.*;

public class OccurrenceTimes {
  /*
  Uber Phone Interview
  Given a sorted integer array - arr, and integer x is contained by the arr,
  return the number of x occurrence in arr.
  Use tweaked binary search to find out the leftmost and rightmost index of x,
  occurrences = rightmost - leftmost + 1;
  time = O(lgn), space = O(1)
  */
  public static void main(String[] args) {
    OccurrenceTimes t = new OccurrenceTimes();
    int[] arr = {1,2,3,3,4,4,4,4,7,8,9};
    System.out.println(t.times(arr, 4));
  }

  public int times(int[] arr, int x) {
    int n = arr.length;
    int low = bs_low(arr, 0, n-1, x);
    int hi = bs_high(arr, 0, n-1, x);
    if(low == -1 || hi == -1) {
      throw new IllegalArgumentException();
    }
    return hi-low+1;
  }

  private int bs_low(int[] arr, int lo, int hi, int x) {
    if (lo > hi) { return -1; }
    int mid = (lo + hi) / 2;
    if (arr[mid] == x) {
      if (mid == 0 || arr[mid-1] < x) {
        return mid;
      } else {
        return bs_low(arr, lo, mid-1, x);
      }
    } else if (arr[mid] > x) {
      return bs_low(arr, lo, mid-1, x);
    } else {
      return bs_low(arr, mid+1, hi, x);
    }
  }

  private int bs_high(int[] arr, int lo, int hi, int x) {
    if (lo > hi) { return -1; }
    int mid = (lo + hi) / 2;
    if (arr[mid] == x) {
      if (mid == arr.length-1 || arr[mid+1] > x) {
        return mid;
      } else {
        return bs_high(arr, mid+1, hi, x);
      }
    } else if (arr[mid] > x) {
      return bs_high(arr, lo, mid-1, x);
    } else {
      return bs_high(arr, mid+1, hi, x);
    }
  }
}
