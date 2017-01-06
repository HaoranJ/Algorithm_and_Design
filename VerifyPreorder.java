import java.util.*;

//first try: use the root node (head node) to cut the rest sequence into two decreasing subsequence, recursively 

public class VerifyPreorder {
	//use stack, time = O(n), space = O(n)
	public boolean verifyPreorder(int[] preorder) {
		ArrayDeque<Integer> stack = new ArrayDeque<>();
		int low = Integer.MIN_VALUE;
		for (int x : preorder ) {
			if (x < low) {
				return false;
			}
			while (!stack.isEmpty() && x > stack.peek()) {
				low = stack.pop();
			}
			stack.push(x);
		}
		return true;
	}

	//use the preorder array itself as a fake stack, maintain a window (left, right) to contain the elements that has been poped out. 
	//time = O(n), space = O(1)
  public boolean verifyPreorderInPlace(int[] preorder) {
      int left = -1;
      int right = -1;
      int low = Integer.MIN_VALUE;
      int preNum = Integer.MAX_VALUE;
      for (int j = 0; j < preorder.length; j++) {
          int num = preorder[j];
          if (num <= low) { return false; }
          if (num >= preNum) {
              int p = j-1;
              while (p >= 0 && preorder[p] < num) {
                  low = preorder[p] > low ? preorder[p] : low;
                  if (p == right) { p = right == left ? left-1 : left; }
                  else { p--; }
              }
              left = p+1;
              right = j-1;
          }
          preNum = num;
      }
      return true;
  }
}