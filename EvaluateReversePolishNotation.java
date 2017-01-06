import java.util.*;

//LeetCode
//time = O(n); space = O(n) in worst case
public class EvaluateReversePolishNotation {
	public int evalRPN(String[] tokens) {
		Stack<Integer> stack = new Stack<>();
		for (String s : tokens) {
			if (!isOperator(s)) {
				stack.push(Integer.parseInt(s));
			} else {
				int y = stack.pop();
				int x = stack.pop();
				int result = compute(x, y, s);
				stack.push(result);
			}
		}
		return stack.pop();
	}

	private boolean isOperator(String s) {
		return "+".equals(s) || "-".equals(s) || "*".equals(s) || "/".equals(s);
	}

	private int compute(int x, int y, String op) {
		switch(op) {
			case "+": {
				return x+y;
			}
			case "-": {
				return x-y;
			}
			case "*": {
				return x*y;
			}
			case "/": {
				return x/y;
			}
			default:
				throw new IllegalArgumentException("wrong");
		}
	}
}