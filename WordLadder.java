//LeetCode - 127

//1. BFS, For a word, its neighbors are those who have only a different letter and exists in wordList.
//Start from beginWord, visit all its not-visited neighbors, then go from last-round words to their not-visited neighbors until we reach endWord.
// Can use a hasmap<curWord, parentWord> to keep track of previous word of current one ==> print the word sequence.
public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
  Deque<String> queue = new ArrayDeque<>();
  wordList.remove(beginWord);
  queue.add(beginWord);
  int len = 1;
  while(!queue.isEmpty()) {
    int size = queue.size();
    for(int i = 0; i < size; i++) {
      String s = queue.poll();
      //Key!!!! to get neighbors, one way is to check all the words in wordList, and get those who have only one //different letter => time = O(size of wordList * length of string); the other way is to enumerate all possible //letters at every index and check if it exists in wordList ==> time = O(26 * length of string).
      char[] chs = s.toCharArray();
      for(int j = 0; j < s.length(); j++) {
        char letter = s.charAt(j);
        for(char c = 'a'; c <= 'z'; c++) {
          if(letter != c) {
            chs[j] = c;
            String wd = new String(chs);
            if(wd.equals(endWord)) {
              return len + 1;
            }
            if(wordList.contains(wd)) {
              queue.add(wd);
              //key!!!! keep track of visited nodes.
              wordList.remove(wd);
            }
            //set letter back to initial one.
            chs[j] = letter;
          }

        }
      }
    }
    len++;
  }
  return 0;
}

//2. Two-end BFS
// "The idea behind bidirectional search is to run two simultaneous searches—one forward from
// the initial state and the other backward from the goal—hoping that the two searches meet in
// the middle. The motivation is that b^(d/2) + b^(d/2) is much less than b^d. b is branch factor, d is depth. "
//
// ----- section 3.4.6 in Artificial Intelligence - A modern approach by Stuart Russel and Peter Norvig
public int ladderLength(String beginWord, String endWord, Set<String> dict) {
  Set<String> head = new HashSet<>(), tail = new HashSet<>();
  head.add(beginWord);
  tail.add(endWord);
  dict.remove(beginWord);
  dict.remove(endWord);
  int dis = 1;
  while(!head.isEmpty() && !tail.isEmpty()) {
    if(head.size() > tail.size()) {
      Set<String> tmp = head;
      head = tail;
      tail = tmp;
    }
    Set<String> nextLvl = new HashSet<>();
    for(String s : head) {
      char[] chs = s.toCharArray();
      for(int i = 0; i < chs.length; i++) {
        char letter = chs[i];
        for(char c = 'a'; c <= 'z'; c++) {
          if(c != letter) {
            chs[i] = c;
            String nextStr = new String(chs);
            if(tail.contains(nextStr)) {
              return dis + 1;
            }
            if(dict.contains(nextStr)) {
              nextLvl.add(nextStr);
              dict.remove(nextStr);
            }
            chs[i] = letter;
          }
        }
      }
    }
    dis++;
    head = nextLvl;
  }
  return 0;
}
