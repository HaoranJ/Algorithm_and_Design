import java.util.*;

/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

public class FindTheCelebrity extends Relation {
  /*
  LeetCode 277
  time = O(n), space = constant
  */
  public int findCelebrity(int n) {
    int cand = 0;
    while(cand < n) {
      boolean candChanged = false;
      for(int i = cand + 1; i < n; i++) {
        if(knows(cand, i)) {
          cand = i;
          candChanged = true;
          break;
        }
      }
      if(!candChanged) { break; }
    }
    for(int i = 0; i < n; i++) {
      if(i != cand) {
        if(knows(cand, i) || !knows(i, cand)) { return -1; }
      }
    }
    return cand;
  }
}
