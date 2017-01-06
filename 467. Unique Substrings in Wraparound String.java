// Consider the string s to be the infinite wraparound string of "abcdefghijklmnopqrstuvwxyz", so s will look like this: "...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....".
//
// Now we have another string p. Your job is to find out how many unique non-empty substrings of p are present in s. In particular, your input is the string p and you need to output the number of different non-empty substrings of p in the string s.
//
// Note: p consists of only lowercase English letters and the size of p might be over 10000.
//
// Example 1:
// Input: "a"
// Output: 1
//
// Explanation: Only the substring "a" of string "a" is in the string s.
// Example 2:
// Input: "cac"
// Output: 2
// Explanation: There are two substrings "a", "c" of string "cac" in the string s.
// Example 3:
// Input: "zab"
// Output: 6
// Explanation: There are six substrings "z", "a", "b", "za", "ab", "zab" of string "zab" in the string s.

//To ensure uniqueness, if we can find out the longest substring that starts with every lowercase letter, the we can sum
//up those lengths of longest substring => answer.
//"abc" => "a", "ab", "abc" => 3
//time = O(n), space = O(1)
public int findSubstringInWraproundString(String p) {
  int[] letters = new int[26];
  Arrays.fill(letters, 0);
  int lo = 0;
  while (lo < p.length()) {
    int hi = lo + 1;
    while(hi < p.length() && (p.charAt(hi) - p.charAt(hi-1) == 1 || p.charAt(hi-1) - p.charAt(hi) == 25)) {
      hi++;
    }
    while(lo < hi) {
      letters[s.charAt(lo) - 'a'] = Math.max(letters[s.charAt(lo) - 'a'], hi - lo);
      lo++;
    }
  }
  int ans = 0;
  for (int x : letters) {
    ans += x;
  }
  return ans;
}
