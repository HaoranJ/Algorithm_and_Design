// Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters. You may assume that each word will contain only lower case letters. If no such two words exist, return 0.
//
// Example 1:
// Given ["abcw", "baz", "foo", "bar", "xtfn", "abcdef"]
// Return 16
// The two words can be "abcw", "xtfn".
//
// Example 2:
// Given ["a", "ab", "abc", "d", "cd", "bcd", "abcd"]
// Return 4
// The two words can be "ab", "cd".
//
// Example 3:
// Given ["a", "aa", "aaa", "aaaa"]
// Return 0
// No such pair of words.

//enumerate all the possible pairs, check if the two share common letter and get the product.
//How to speed up the check-common-letter process, use an integer with 32 bits to be as a hashmap
//for 26 lower-case letters.
//time = O(n^2), space = O(n)
public int maxProduct(String[] words) {
  int n = words.length;
  int[] letterInts = new int[n];
  //use an integer to represent the distribution of letters for a word
  //i.e. "abd" -> 00...000000111
  for (int i = 0; i < n; i++) {
    int letterInt = 0;
    for (char ch : words[i].toCharArray()) {
      letterInt |= (1 << (ch - 'a'));
    }
    letterInts[i] = letterInt;
  }
  int maxPro = 0;
  for (int i = 0; i < n; i++) {
    for (int j = i + 1; j < n; j++) {
      if ((letterInts[i] & letterInts[j]) == 0) {
        maxPro = Math.max(maxPro, words[i].length() * words[j].length());
      }
    }
  }
  return maxPro;
}
