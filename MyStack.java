public class MyStack {
	List<Integer> queue;
	List<Integer> aux;

	public MyStack() {
		queue = new ArrayList<>();
		aux = new ArrayList<>();
	}

	public void push(int x) {
		queue.add(x);
	}

	public void pop() {
		while (queue.size() > 1) {
			aux.add(queue.remove(0));
		}
		queue.clear();
		List<Integer> temp = queue;
		queue = aux;
		aux = temp;
	}

	public int top() {
		while (queue.size() > 1) {
			aux.add(queue.remove(0));
		}
		int ret = queue.remove(0);
		aux.add(ret);
		List<Integer> temp = queue;
		queue = aux;
		aux = temp;
		return ret;
	}

	public boolean empty() {
		return queue.size() == 0;
	}
}