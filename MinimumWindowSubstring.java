import java.util.*;

public class MinimumWindowSubstring {
  /*
  LeetCode - 76.
  Two pointer for a sliding window - time = O(n), space = O(1)
  */
  // Template!!!!
  int count = 0; //
  /* Initialize a hashmap to check valid */
  int lo = 0, hi = 0; //two pointers for sliding window
  int size; //size of the window
  while (hi < s.length()) {
    if (/* maximum window - window is valid; min window - window is NOT valid */) {
      //move hi forward for one step + update count
      hi++;
    }
    while (/* maximum window - window is NOT valid; min window - window is valid */) {
      //move lo forward + update count
      /* update size for minimum window */
      lo++;
    }
    /* update size for maximum window */
  }
  return size;

  public String minWindow(String s, String t) {
    if(t.trim().length() == 0) { return ""; }
    Map<Character, Integer> map = new HashMap<>();
    int count = 0;
    for (char ch : t.toCharArray()) {
      map.put(ch, map.getOrDefault(ch, 0) + 1);
      count++;
    }
    int lo = 0, hi = 0;
    int head = 0;
    int size = s.length() + 1;
    while (hi < s.length()) {
      if (count > 0) {
        char ch = s.charAt(hi++);
        if (map.containsKey(ch)) {
          map.put(ch, map.get(ch) - 1);
          if (map.get(ch) == 0) { count--; }
        }
      }
      while (count == 0) {
        int window = hi - lo;
        if (window < size) {
          head = lo;
          size = window;
        }
        char ch1 = s.charAt(lo++);
        if (map.containsKey(ch1)) {
          map.put(ch1, map.get(ch1) + 1);
          if (map.get(ch1) == 1) { count++; }
        }
      }
    }
    return size == s.length() + 1 ? "" : s.substring(head, head + size);
  }

  public static String minWindow(String s, String t) {
    Map<Character, Integer> map = new HashMap<>();
    int chNum = 0;
    for(int i = 0; i < t.length(); i++) {
      char ch = t.charAt(i);
      if (map.containsKey(ch)) {
        map.replace(ch, map.get(ch)+1);
      } else {
        map.put(ch, 1);
        chNum++;
      }
    }
    int hi = 0, lo = 0;
    int ct = 0, minLen = s.length()+1;
    String ans = "";
    while (hi < s.length() && lo < s.length()) {
      while (hi < s.length() && ct < chNum) {
        char ch = s.charAt(hi++);
        if (t.indexOf(ch) > -1) {
          int val = map.replace(ch, map.get(ch)-1);
          if(val == 1) { ct++; }
        }
      }
      if(ct < chNum) { break; }
      while (lo < s.length() && ct == chNum) {
        char ch = s.charAt(lo++);
        if (t.indexOf(ch) > -1) {
          int val = map.replace(ch, map.get(ch)+1);
          if(val == 0) { ct--; }
        }
      }
      if (hi <= s.length() && lo <= s.length()) {
        int len = (hi-lo+1);
        if(len < minLen) {
          ans = s.substring(lo-1, hi);
          minLen = len;
        }
      }
    }
    return ans;
  }

  public static void main(String[] args) {
    System.out.println(minWindow("ADOBECODEBANC", "ABC"));
  }
}
