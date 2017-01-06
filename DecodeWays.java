
//LeetCode - 91
public int numDecodings(String s) {
  int len = s.length();
  if(len == 0) { return 0; }
  int twoPrev = 1;
  int onePrev = s.charAt(0) == '0' ? 0 : 1;
  int cur = 0;
  for (int i = 2; i < len+1; i++) {
    cur = 0;
    if (s.charAt(i-1) != '0') {
      cur += onePrev;
    }
    if (s.charAt(i-2) != '0') {
      int lastTwo = Integer.parseInt(s.substring(i-2, i));
      if (1 <= lastTwo && lastTwo <= 26) {
        cur += twoPrev;
      }
    }
    twoPrev = onePrev;
    onePrev = cur;
  }
  return cur == 0 ? onePrev : cur;
}
