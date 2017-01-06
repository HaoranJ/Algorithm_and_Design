public int lengthOfLongestSubstringTwoDistinct(String s) {
  Map<Character, Integer> map = new HashMap<>();
  int count = 0;
  int lo = 0, hi = 0;
  int size = 0;
  while (hi < s.length()) {
    //if the sliding window is valid
    if (count <= 2) {
      char ch = s.charAt(hi++);
      if (!map.containsKey(ch)) {
        map.put(ch, 1);
        count++;
      } else {
        map.put(ch, map.get(ch) + 1);
      }
    }
    //move lo pointer forward till the window becomes valid again.
    while (count > 2) {
      char ch = s.charAt(lo++);
      map.put(ch, map.get(ch) - 1);
      if (map.get(ch) == 0) {
        map.remove(ch);
        count--;
      }
    }
    //record the size of window.
    size = Math.max(size, hi - lo);
  }
  return size;
}
