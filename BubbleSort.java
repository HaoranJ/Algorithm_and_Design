import java.util.*;

public class BubbleSort {
  public static void main(String[] args) {
    int[] arr = {5, 1, 12, -6, 9,33,6,7,4,9,9,2,80};
    // bubbleSort(arr);
    selectionSort(arr);
    for (int x : arr) {
      System.out.print(x + ", ");
    }
  }
  public static void bubbleSort(int[] arr) {
    assert arr != null;
    int n = arr.length;
    boolean swapped = false;
    int j = 0;
    do {
      swapped = false;
      j++;
      for (int i = 0; i < n-j; i++) {
        if (arr[i] > arr[i+1]) {
          int temp = arr[i];
          arr[i] = arr[i+1];
          arr[i+1] = temp;
          swapped = true;
        }
      }
    } while(swapped == true);
  }

  public static void selectionSort(int[] arr) {
    int n = arr.length;
    for (int i = 0; i < n; i++) {
      int minIdx = -1;
      int minValue = Integer.MAX_VALUE;
      for (int j = i; j < n; j++) {
        if (arr[j] < minValue) {
          minValue = arr[j];
          minIdx = j;
        }
      }
      int temp = arr[i];
      arr[i] = minValue;
      arr[minIdx] = temp;
    }
  }
}
