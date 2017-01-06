// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
import java.util.*;
/*
LeetCode 284, Peeking Iterator
Use decorator pattern, look ahead - use nextItem to store the next one.
*/
class PeekingIterator<T> implements Iterator<T> {
  private Iterator<T> itr;
  private T nextItem = null;

  public PeekingIterator(Iterator<T> iterator) {
    // initialize any member here.
    itr = iterator;
    updateNextItem();
  }

  private void updateNextItem(){
    if (itr.hasNext()) {
      nextItem = itr.next();
    } else {
      nextItem = null;
    }
  }

  // Returns the next element in the iteration without advancing the iterator.
  public T peek() {
    return nextItem;
  }

  // hasNext() and next() should behave the same as in the Iterator interface.
  // Override them if needed.
  @Override
  public T next() {
    T ret = nextItem;
    updateNextItem();
    return ret;
  }

  @Override
  public boolean hasNext() {
    return nextItem != null;
  }
}
