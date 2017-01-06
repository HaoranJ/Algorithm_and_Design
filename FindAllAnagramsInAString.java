//LeetCode - 438
//two pointer - sliding window
//time = O(n), space O(1)
public List<Integer> findAnagrams(String s, String p) {
  List<Integer> ans = new ArrayList<>();
  int[] pArr = new int[26], sArr = new int[26];
  for (int i = 0; i < p.length(); i++) {
    char ch = p.charAt(i);
    pArr[ch - 'a']++;
  }
  int lo = 0, hi = p.length()-1;
  while (hi < s.length()) {
    if (lo == 0) {
      for (int i = lo; i <= hi; i++) {
        sArr[s.charAt(i) - 'a']++;
      }
    } else {
      sArr[s.charAt(lo-1) - 'a']--;
      sArr[s.charAt(hi) - 'a']++;
    }
    if (isAnagram(pArr, sArr)) {
      ans.add(lo);
    }
    lo++;
    hi++;
  }
  return ans;
}

private boolean isAnagram(int[] standard, int[] candidate) {
  assert standard.length == candidate.length;
  for (int i = 0; i < standard.length; i++) {
    if (standard[i] != candidate[i]) {
      return false;
    }
  }
  return true;
}
