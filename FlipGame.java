import java.util.*;

public class FlipGame {
  /*
  LeetCode 294 - Flip Game II
  If first player can make a move that let second player have to lose (secondCanWin = false),
  return true; otherwise (no valid move || every move cannot guarantee second to lose) return false.
  We use map to store <String, Boolean> to improve time.
  time/space complexity: best case = O(n), worst case = O((n-1)(n-3)(n-5)...)
  */
  Map<String, Boolean> map = new HashMap<>();
  public boolean canWin(String s) {
    assert s != null;
    int n = s.length();
    for (int i = 0; i < n; i++) {
      if (s.startsWith("++", i)) {
        String move = s.substring(0, i) + "--" + s.substring(i+2, n);
        boolean secondCanWin = false;
        if (map.containsKey(move)) {
          secondCanWin = map.get(move);
        } else {
          secondCanWin = canWin(move);
          map.put(move, secondCanWin);
        }
        if (!secondCanWin) {
          map.put(s, true);
          return true;
        }
      }
    }
    map.put(s, false);
    return false;
  }
}
