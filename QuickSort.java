import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Haoran on 7/24/16.
 */
public class QuickSort {
  private static int count1 = 0;
  private static int count2 = 0;
  private static int count3 = 0;
  public static void main(String[] args) {
    //read elements into an array
    Scanner sc = new Scanner(System.in);
    List<Integer> list = new ArrayList<>();
    while (sc.hasNextInt()) {
      list.add(sc.nextInt());
    }
    int[] arr = new int[list.size()];
    for (int i = 0; i < list.size(); i++) {
      arr[i] = list.get(i);
    }

    //call three quicksort methods.
    quicksort1(arr, 0, arr.length-1);
    System.out.println("Q1 = " + count1);

    for (int i = 0; i < list.size(); i++) {
      arr[i] = list.get(i);
    }
    quicksort2(arr, 0, arr.length-1);
    System.out.println("Q2 = " + count2);

    for (int i = 0; i < list.size(); i++) {
      arr[i] = list.get(i);
    }
    quicksort3(arr, 0, arr.length-1);
    System.out.println("Q3 = " + count3);
  }

  private static int quicksort3(int[] arr, int lo, int hi) {
    if(lo < hi) {
      int medIdx = medianOfThree(arr, lo, hi);
      swap(arr, lo, medIdx);
      int pivotIdx = partition(arr, lo, hi);
      count3 += hi - lo;
      quicksort3(arr, lo, pivotIdx-1);
      quicksort3(arr, pivotIdx+1, hi);
    }
    return count3;
  }

  private static int medianOfThree(int[] arr, int lo, int hi) {
    int mid = lo + (hi - lo) / 2;
    if(arr[lo] <= arr[mid] && arr[mid] <= arr[hi]) { return mid; }
    else if(arr[hi] <= arr[mid] && arr[mid] <= arr[lo]) { return mid; }
    else if(arr[mid] <= arr[lo] && arr[lo] <= arr[hi]) { return lo; }
    else if(arr[hi] <= arr[lo] && arr[lo] <= arr[mid]) { return lo; }
    else if(arr[lo] <= arr[hi] && arr[hi] <= arr[mid]) { return hi; }
    else { return hi; }
  }

  private static int quicksort2(int[] arr, int lo, int hi) {
    if(lo < hi) {
      swap(arr, lo, hi);
      int pivotIdx = partition(arr, lo, hi);
      count2 += hi - lo;
      quicksort2(arr, lo, pivotIdx - 1);
      quicksort2(arr, pivotIdx + 1, hi);
    }
    return count2;
  }

  private static int quicksort1(int[] arr, int lo, int hi) {
    if(lo < hi) {
      int pivotIdx = partition(arr, lo, hi);
      count1 += hi - lo; //comparisions = length - 1
      quicksort1(arr, lo, pivotIdx - 1);
      quicksort1(arr, pivotIdx + 1, hi);
    }
    return count1;
  }

  private static int partition(int[] arr, int lo, int hi) {
    int pivot = arr[lo];
    int i = lo + 1;
    for (int j = i; j <= hi; j++) {
      if (arr[j] < pivot) {
        swap(arr, i, j);
        i++;
      }
    }
    swap(arr, lo, i-1);
    return i-1;
  }
  private static void swap(int[] arr, int i1, int i2) {
    int temp = arr[i1];
    arr[i1] = arr[i2];
    arr[i2] = temp;
  }
}
