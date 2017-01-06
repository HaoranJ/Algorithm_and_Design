import java.util.*;

public class MyQueue {
	Stack<Integer> stack;
	Stack<Integer> aux;

	public MyQueue() {
		stack = new Stack<>();
		aux = new Stack<>();
	}

	public void push(int x) {
		stack.push(x);
	}

	public void pop() {
		while (stack.size() > 1) {
			aux.push(stack.pop());
		}
		stack.pop();
		while (aux.size() > 0) {
			stack.push(aux.pop());
		}
	}

	public int peek() {
		while (stack.size() > 1) {
			aux.push(stack.pop());
		}
		int ret = stack.peek();
		while (aux.size() > 0) {
			stack.push(aux.pop());
		}
		return ret;
	}

	public boolean empty() {
		return stack.size() == 0;
	}
}