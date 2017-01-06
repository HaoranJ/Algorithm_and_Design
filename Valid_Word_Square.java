//LeetCode - 422
public boolean validWordSquare(List<String> words) {
  int numRows = words.size();
  for (int r = 0; r < numRows; r++) {
    for (int i = 0; i < words.get(r).length(); i++) {
      if (i >= numRows || words.get(i).length() <= r || words.get(r).charAt(i) != words.get(i).charAt(r)) {
        return false;
      }
    }
  }
  return true;
}
