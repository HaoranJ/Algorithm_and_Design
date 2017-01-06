import java.util.*;

/*
LeetCode - , Valid Number
We start with trimming.

If we see [0-9] we reset the number flags.
We can only see . if we didn't see e or ..
We can only see e if we didn't see e but we did see a number. We reset numberAfterE flag.
We can only see + and - in the beginning and after an e
any other character break the validation.
At the and it is only valid if there was at least 1 number and if we did see an e then a number after it as well.

So basically the number should match this regular expression:

[-+]?[0-9]*(.[0-9]+)?(e[-+]?[0-9]+)?

time = O(n), space = constant
*/
public class ValidNumber {
  public boolean isNumber(String s) {
    s = s.trim();
    boolean numSeen = false;
    boolean eSeen = false;
    boolean pointSeen = false;
    boolean isNumAfterE = false;
    for (int i = 0; i < s.length(); i++) {
      char ch = s.charAt(i);
      if (ch >= '0' && ch <= '9') {
        if (eSeen) {
          isNumAfterE = true;
        } else {
          numSeen = true;
        }
      } else if (ch == '.') {
        if (eSeen || pointSeen) {
          return false;
        }
        pointSeen = true;
      } else if (ch == '+' || ch == '-') {
        if (i != 0 && s.charAt(i-1) != 'e') {
          return false;
        }
      } else if (ch == 'e') {
        if (!numSeen || eSeen) {
          return false;
        }
        eSeen = true;
      } else {
        return false;
      }
    }
    return eSeen ? (numSeen && isNumAfterE) : numSeen;
  }
}
