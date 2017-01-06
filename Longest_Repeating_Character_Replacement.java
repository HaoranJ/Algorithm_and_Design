/*LeetCode - 424
Sliding window - time = O(n)
*/
//1. Sliding window
public int characterReplacement(String s, int k) {
  int ans = 0;
  int lo = 0, hi = 0;
  int[] letters = new int[26];
  int mostLtNum = 0;
  while(hi < s.length()) {
    char hiCh = s.charAt(hi++);
    mostLtNum = Math.max(mostLtNum, ++letters[hiCh - 'A']);
    while(lo < hi && hi - lo - mostLtNum > k) {
      char loCh = s.charAt(lo++);
      letters[loCh - 'A']--;
      mostLtNum = 0;
      for(int x : letters) {
        mostLtNum = Math.max(x, mostLtNum);
      }
    }
    ans = Math.max(ans, hi - lo);
  }
  return ans;
}

//2.
// check every letter sequentially.
public int characterReplacement(String s, int k) {
  char ch = 'A';
  int ans = 0;
  for (int i = 0; i < 26; i++) {
    char letter = (char)(ch + i);
    int ret = characterReplacement(s, k, letter);
    ans = Math.max(ret, ans);
  }
  return ans;
}

private int characterReplacement(String s, int k, char letter) {
  int lo = 0, hi = 0, replace = 0;
  int window = 0;
  while (hi < s.length()) {
    while (hi < s.length() && (s.charAt(hi) == letter || replace < k)) {
      if (s.charAt(hi) != letter) {
        replace++;
      }
      hi++;
    }
    window = Math.max(window, hi - lo);
    while (lo < s.length() && replace == k) {
      if (s.charAt(lo) != letter) {
        replace--;
      }
      lo++;
    }
  }
  return window;
}
