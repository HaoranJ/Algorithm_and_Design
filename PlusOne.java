import java.util.*;

public class PlusOne{
	public int[] plusOne(int[] digits){
		int len = digits.length, carry = 1, sum = 0;
		for (int j = len-1; j >= 0 ; j-- ) {
			sum = digits[j] + carry;
			digits[j] = sum%10;
			carry = sum/10;
		}
		if(carry == 0){
			return digits;
		}else{
			int[] ans = new int[len + 1];
			ans[0] = 1;
			System.arraycopy(digits, 0, ans, 1, digits.length);
			return ans;
		}

	}
}