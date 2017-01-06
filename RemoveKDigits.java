/*Leetcode - 402
Start from the leftmost digit which has the biggest weight,
if cur digit > top of stack, pop the stack until cur digit <= top of stack
or stack is empty, then push cur digit into stack.
time = O(n), space = O(n)
*/
public String removeKdigits(String num, int k) {
  int digits = num.length() - k;
  Deque<Integer> stk = new ArrayDeque<>();
  int idx = 0;
  while (idx < num.length()) {
    int curDigit = num.charAt(idx) - '0';
    while (!stk.isEmpty() && stk.peek() > curDigit && k > 0) {
      stk.pop();
      k--;
    }
    stk.push(curDigit);
    idx++;
  }
  //remove leading zeroes.
  while (!stk.isEmpty() && stk.peekLast() == 0) {
    stk.removeLast();
  }
  if (stk.isEmpty()) {
    return "0";
  }
  StringBuilder sb = new StringBuilder();
  int resLen  = Math.min(digits, stk.size()); //key!! ensure the result is valid.
  for (int i = 0; i < resLen; i++) {
    sb.append(stk.removeLast());
  }
  return sb.length() == 0 ? "0" : sb.toString();
}
