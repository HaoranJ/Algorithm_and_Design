import java.util.*;
//LeetCode 205
//time = O(n), space = constant
//http://stackoverflow.com/questions/2680548/given-an-array-of-numbers-return-array-of-products-of-all-other-numbers-no-div
// An explanation of polygenelubricants method is: The trick is to construct the arrays (in the case for 4 elements)
//
// {              1,         a[0],    a[0]*a[1],    a[0]*a[1]*a[2],  }
// { a[1]*a[2]*a[3],    a[2]*a[3],         a[3],                 1,  }
// Both of which can be done in O(n) by starting at the left and right edges respectively.
//
// Then multiplying the two arrays element by element gives the required result
//
// My code would look something like this:
//
// int a[N] // This is the input
// int products_below[N];
// p=1;
// for(int i=0;i<N;++i) {
//   products_below[i]=p;
//   p*=a[i];
// }
//
// int products_above[N];
// p=1;
// for(int i=N-1;i>=0;--i) {
//   products_above[i]=p;
//   p*=a[i];
// }
//
// int products[N]; // This is the result
// for(int i=0;i<N;++i) {
//   products[i]=products_below[i]*products_above[i];
// }
// If you need to be O(1) in space too you can do this (which is less clear IMHO)
//
// int a[N] // This is the input
// int products[N];
//
// // Get the products below the current index
// p=1;
// for(int i=0;i<N;++i) {
//   products[i]=p;
//   p*=a[i];
// }
//
// // Get the products above the curent index
// p=1;
// for(int i=N-1;i>=0;--i) {
//   products[i]*=p;
//   p*=a[i];
// }
public class ProductOfArrayExceptSelf {
	public int[] productExceptSelf(int[] nums) {
		int n = nums.length;
		assert n > 1;
		int[] output = new int[n];
		int store = 1;

		for (int j = n-1; j >= 0; j--) {
			if (j == n-1) {
				output[j] = 1;
			} else {
				output[j] = store * output[j+1];
			}
			store = nums[j];
		}

		for (int j = 0; j < n; j++) {
			int temp = nums[j];
			if (j == 0) {
				nums[j] = 1;
			} else {
				nums[j] = store * nums[j-1];
			}
			store = temp;
		}
		for (int j = 0; j < n; j++) {
			output[j] = output[j] * nums[j];
		}
		return output;
	}
}
