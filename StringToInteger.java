public class StringToInteger {
  /*LeetCode - 8
  discards all leading whitespaces
  sign of the number
  overflow
  invalid input
  */
  private static final int maxDiv10 = Integer.MAX_VALUE / 10;
  public int myAtoi(String str) {
      char[] chs = str.trim().toCharArray();
      int i = 0, sign = 1, num = 0;
      if (i < chs.length && (chs[i] == '+' || chs[i] == '-')) {
        i++;
        if(chs[0] == '-') { sign = -1; }
      }
      while (i < chs.length && chs[i] >= '0' && chs[i] <= '9') {
        int digit = chs[i] - '0';
        if (num > maxDiv10 || (num == maxDiv10 && digit >= 8)) {
          return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }
        num = num * 10 + digit;
        i++;
      }
      return sign * num;
  }
}
