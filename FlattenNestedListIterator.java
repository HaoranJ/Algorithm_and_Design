/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
 /*
 LeetCode - 341.
 Decorator design pattern. use stack to keep track of previous iterators is the key.
 */
public class NestedIterator implements Iterator<Integer> {
  private Iterator<NestedInteger> itr;
  private Integer nextInt;
  private Deque<Iterator<NestedInteger>> stack;
  public NestedIterator(List<NestedInteger> nestedList) {
    stack = new ArrayDeque<>();
    stack.push(nestedList.iterator());
    getNext();
  }

  private void getNext() {
    while (!stack.isEmpty()) {
      itr = stack.peekFirst();
      if (itr.hasNext()) {
        NestedInteger ni = itr.next();
        if (ni.isInteger()) {
          nextInt = ni.getInteger();
          return;
        } else {
          stack.push(ni.getList().iterator());
        }
      } else {
        stack.pop();
      }
    }
    if (stack.isEmpty()) {
      nextInt = null;
    }
  }

  @Override
  public Integer next() {
    Integer ret = nextInt;
    getNext();
    return ret;
  }

  @Override
  public boolean hasNext() {
    return nextInt != null;
  }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
