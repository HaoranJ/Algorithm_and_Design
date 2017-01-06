/* LeetCode 320 - Generalized Abbreviation
Write a function to generate the generalized abbreviations of a word.

Example:
Given word = "word", return the following list (order does not matter):
["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]

*/

//word has n letters
//DFS, recursion. time = O(n^2)
public List<String> generateAbbreviations(String word) {
  List<String> abbs = new ArrayList<>();
  //base case
  if (word.length() == 0) {
    abbs.add("");
    return abbs;
  }

  String letter = word.substring(0, 1);
  List<String> suffixList = generateAbbreviations(word.substring(1));
  for (String suf : suffixList) {
    abbs.add(letter + suf);
    //get number prefix.
    int i = 0;
    while (i < suf.length() && suf.charAt(i) >= '0' && suf.charAt(i) <= '9') {
      i++;
    }
    //Integer.parseInt("") => NumberFormatException!!
    String strNum = i == 0 ? 1 + suf.substring(i) : Integer.parseInt(suf.substring(0, i)) + 1 + suf.substring(i);
    abbs.add(strNum);
  }
  return abbs;
}

//word has n letters
//BFS, iteration. time = O(n^2)
public List<String> generateAbbreviations(String word) {
  Deque<String> queue = new ArrayDeque<>();
  queue.add("");
  for (char ch : word.toCharArray()) {
    int qSize = queue.size();
    for (int i = 0; i < qSize; i++) {
      String prefix = queue.poll();
      int j = prefix.length() - 1;
      while(j >= 0 && prefix.charAt(j) >= '0' && prefix.charAt(j) <= '9') {
        j--;
      }
      if (prefix.isEmpty() || j == prefix.length() - 1) {
        queue.add(prefix + "1");
      } else {
        int lastNum = Integer.parseInt(prefix.substring(j+1));
        queue.add(prefix.substring(0, j+1) + String.valueOf(lastNum + 1));
      }
      queue.add(prefix + ch);
    }
  }
  return new ArrayList<String>(queue);
}
