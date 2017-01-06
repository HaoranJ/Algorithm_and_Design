import java.io.*;
import java.util.*;
import java.util.regex.*;

/*
http://www.geeksforgeeks.org/counting-inversions/
*/
public class Main
{
	private static int[] aux;
	  public static void main(String[] args) {
			//read file
	    Scanner sc = new Scanner(System.in);
	    List<Integer> nums = new ArrayList<>();
	    while (sc.hasNextLine()) {
	      nums.add(Integer.parseInt(sc.nextLine()));
	    }
	    aux = new int[nums.size()];
	    int[] arr = new int[nums.size()];
	    for(int i = 0; i < nums.size(); i++) {
	      arr[i] = nums.get(i);
	    }
	    System.out.println(getInv(arr, 0, arr.length-1));
	  }

		//enhanced merge sort. Recursion.
		//time = O(nlogn)
	  public static long getInv(int[] nums, int lo, int hi) {
			if(lo >= hi) { return 0; }
	    long inv = 0;
	    int mid = lo + (hi - lo) / 2;
	    inv += getInv(nums, lo, mid); //left inversions
	    inv += getInv(nums, mid+1, hi); //right inversions
			//merge
	    int i = lo, j = mid+1;
	    for(int k = lo; k <= hi; k++) {
	      aux[k] = nums[k];
	    }
	    for(int k = lo; k <= hi; k++) {
	      if(i > mid) { nums[k] = aux[j++]; }
	      else if (j > hi) { nums[k] = aux[i++]; }
	      else if (aux[i] > aux[j]) {
	        inv += mid - i + 1; //elements from i to mid are inversed to aux[j]
	        nums[k] = aux[j++];
	      } else {
	        nums[k] = aux[i++];
	      }
	    }
	    return inv;
	  }
}
