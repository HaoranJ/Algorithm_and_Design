import java.util.*;

/*
LeetCode 251 - Flatten 2D Vector
only use iterator.
*/
public class Vector2D {
  Iterator<List<Integer>> it1;
  Iterator	<Integer> it2;
  Integer nextInt; //* key
  public Vector2D(List<List<Integer>> vec2d) {
    assert vec2d != null;
    nextInt = null;
    it1 = vec2d.iterator();
    updateNextInt(); //* key
  }

  private void updateNextInt() {
    if (it2 != null && it2.hasNext()) {
      nextInt = it2.next();
      return;
    }
    while(it1.hasNext()) {
      List<Integer> list = it1.next();
      it2 = list.iterator();
      if(it2.hasNext()) {
        break;
      }
    }
    if (it2 != null && it2.hasNext()) {
      nextInt = it2.next();
    } else {
      nextInt = null;
    }
  }

  public int next() {
    if (nextInt == null) {
      throw new NoSuchElementException();
    }
    int ret = nextInt;
    updateNextInt();
    return ret;
  }

  public boolean hasNext() {
    return nextInt != null;
  }
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */
