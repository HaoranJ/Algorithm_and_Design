import java.util.*;

public class BasicCalculator {
/*
LeetCode 224 - Basic Calculator
Use stack (deque) to simplify the problem.
*/
/*
Naive approach, iterate from the end to the head, when meet '(', compute the sum
of this pair of parentheses, until only one element in the stack.
time = O(n), space = worst case, O(n)
*/
  public int calculate(String s) {
    Deque<String> stk = new ArrayDeque<>();
    s = s.trim();
    int len = s.length();
    int i = len-1;
    while( i >= -1) {
      if(i == -1 || s.charAt(i) == '(') {
        while(stk.size() > 1 && !stk.peekFirst().equals(")")) {
          String t = stk.pop();
          int x = Integer.parseInt(t);
          String sign = stk.pop();
          if(sign.equals(")")) { stk.push(t); break; }
          else if(sign.equals("+")) {
            String a = String.valueOf(x + Integer.parseInt(stk.pop()));
            stk.push(a);
          } else {
            String a = String.valueOf(x - Integer.parseInt(stk.pop()));
            stk.push(a);
          }
        }
        i--;
      } else if(s.charAt(i) == ')' || s.charAt(i) == '+' || s.charAt(i) == '-') {
        stk.push(s.substring(i, i+1));
        i--;
      } else if(s.charAt(i) == ' ') {
        i--;
      } else {
        int p = i - 1;
        while(p >= 0 && Character.isDigit(s.charAt(p))) {
          p--;
        }
        stk.push(s.substring(p+1, i+1));
        i = p;
      }
    }
    assert stk.size() == 1;
    return Integer.parseInt(stk.pop());
  }

  /*
  Better approach, iterate from the head
  time = O(n), space = worst case, O(n)
  */
  public int calculate_Faster(String s) {
    Deque<Integer> stk = new ArrayDeque<>();
    int result = 0, num = 0, sign = 1;
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if ('0' <= c && c <= '9') {
        num = 10*num + (c - '0');
      } else if (c == '+' || c == '-') {
        result += sign * num;
        num = 0;
        sign = c == '+' ? 1 : -1;
      } else if (c == '(') {
        stk.push(result);
        stk.push(sign);
        result = 0;
        sign = 1;
      } else if (c == ')') {
        result += sign * num;
        result = result * stk.pop() + stk.pop();
        num = 0;
      }
    }
    return result + sign*num;
  }
}
