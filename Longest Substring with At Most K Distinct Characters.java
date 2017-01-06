public int lengthOfLongestSubstringKDistinct(String s, int k) {
  Map<Character, Integer> map = new HashMap<>();
  int distinctCt = 0;
  int lo = 0, hi = 0;
  int size = 0;
  while (hi < s.length()) {
    if (distinctCt <= k) {
      char ch = s.charAt(hi++);
      if (!map.containsKey(ch)) {
        map.put(ch, 1);
        distinctCt++;
      } else {
        map.put(ch, map.get(ch) + 1);
      }
    }
    while (distinctCt > k) {
      char ch = s.charAt(lo++);
      map.put(ch, map.get(ch) - 1);
      if (map.get(ch) == 0) {
        distinctCt--;
        map.remove(ch);
      }
    }
    size = Math.max(size, hi - lo);
  }
  return size;
}
