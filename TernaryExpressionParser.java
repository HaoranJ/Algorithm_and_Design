/*LeetCode - 439
Use stack, iterate from the begin to end.
But from end to begin would be simpler. char after '?' must be condition.
*/
public String parseTernary(String expression) {
  Deque<Character> stack = new ArrayDeque<>();
  for (int i = 0; i < expression.length(); i++) {
    char ch = expression.charAt(i);
    //T?T:F?T?1:2:F?3:4
    //to avoid ambiguity, the condition can only be 'T' or 'F' instead of 'T?F:T'
    //if 'T' or 'F' is followed by '?', it means 'T' or 'F' is condition.
    if (ch == '?' || ((ch == 'T' || ch == 'F') && i + 1 < expression.length() && expression.charAt(i+1) == '?')) {
      stack.push(ch);
    } else if (ch == ':') {
      continue;
    } else {
      while (!stack.isEmpty() && stack.peek() != '?') {
        char topCh = stack.pop();
        if (stack.peek() == '?') {
          stack.pop();
          char tf = stack.pop();
          ch = tf == 'T' ? topCh : ch;
        }
      }
      stack.push(ch);
    }
  }
  return "" + stack.pop();
}
