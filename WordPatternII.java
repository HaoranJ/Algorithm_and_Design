private int[] times;
private String pattern;
private String str;
/* LeetCode 291
pattern = "abccaa", 3a + b + 2c = str.length()
get all possible a,b,c lengths for this linear equation. use the lengths to check
str with pattern.
*/
public boolean wordPatternMatch(String pattern, String str) {
  times = new int[26];
  this.pattern = pattern;
  this.str = str;
  for (char c : pattern.toCharArray()) {
    times[c-'a']++;
  }
  int[] lens = new int[26];
  return getLens(0, str.length(), lens);
}

//get all possible lengths combinations
private boolean getLens(int idx, int sum, int[] lens) {
  if (idx == 26) {
    if(sum == 0) {
      if(checkPattern(lens)) { return true; }
    }
  } else {
    if (times[idx] == 0) {
      if(getLens(idx+1, sum, lens)) return true;
    }
    else {
      int ceiling = sum / times[idx];
      for (int l = 1; l <= ceiling; l++) {
        lens[idx] = l;
        int cur = l * times[idx];
        if (cur > sum) {
          break;
        }
        if(getLens(idx + 1, sum - cur, lens)) { return true; }
        lens[idx] = 0;
      }
    }
  }
  return false;
}

//check if str is a match for pattern.
private boolean checkPattern(int[] lens) {
  Map<Character, String> letStrMap = new HashMap<>();
  Set<String> appearedStr = new HashSet<>();
  int offset = 0;
  for (char c : pattern.toCharArray()) {
    int len = lens[c-'a'];
    String subStr = str.substring(offset, offset+len);
    if (letStrMap.containsKey(c)) {
      if(!letStrMap.get(c).equals(subStr)) { return false; }
    } else {
      if(appearedStr.contains(subStr)) { return false; }
      letStrMap.put(c, subStr);
      appearedStr.add(subStr);
    }
    offset += len;
  }
  return true;
}
