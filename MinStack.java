import java.util.*;
//LeetCode 155 - MinStack
public class MinStack {
	private final Stack realStack;
	private final Stack auxStack;

	public MinStack() {
		realStack = new Stack();
		auxStack = new Stack();
	}

	public void push(int x) {
		realStack.push(x);
		if (auxStack.isEmpty() || x <= (int)auxStack.peek()) {
			auxStack.push(x);
		} else {
		    auxStack.push(auxStack.peek());
		}
	}

	public void pop() {
		realStack.pop();
		auxStack.pop();
	}

	public int top() {
		return (int)realStack.peek();

	}

	public int getMin() {
		return (int)auxStack.peek();
	}
}