private List<String> ans;
private int target;
private String num;
/*
LeetCode - 282.
Basic calculation - order matters, we can only go from the head to the end.
1-1-5 is not 5, but if we get 1 at hand, use "-", and expect the rest to be -4 when target = 5
However, we need 1-(1-5) when use recursion on "15" of "115"
*/

//TODO try put "" / "+" / "-" / "*" into (n-1) slots, and get all possible result.
public List<String> addOperators(String num, int target) {
  assert num != null;
  ans = new ArrayList<>();
  this.target = target;
  this.num = num;
  recur(0, "", 0, 0);
  return ans;
}

private void recur(int startIdx, String preStr, long preVal, long lastNum) {
  //base case
  if (startIdx == num.length() && preVal == (long)target) {
    ans.add(preStr);
    return;
  }
  for (int i = startIdx+1; i <= num.length(); i++) {
    //005 invalid, but 5 is okay.
    if (num.charAt(startIdx) == '0' && i > startIdx + 1) {
      break;
    }
    long curNum = Long.parseInt(num.substring(startIdx, i));
    //consider the initial case.
    if (startIdx == 0) {
      recur(i, String.valueOf(curNum), curNum, curNum);
      continue;
    }
    recur(i, preStr + "+" + curNum, preVal + curNum, curNum);
    recur(i, preStr + "-" + curNum, preVal - curNum, -curNum);
    //1+2+3, curNum = 4, ==> 1+2+3*4 = 1+2+3-3+(3*4)
    //lastNum and curNum both are separated by "+" or "-"
    //2*3*4 = preStr, preVal = 2 x 3 x 4 = 24, lastNum = 24
    recur(i, preStr + "*" + curNum, preVal - lastNum + (lastNum * curNum), lastNum * curNum);
  }
}
