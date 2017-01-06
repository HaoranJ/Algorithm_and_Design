/*
LeetCode - 301. Use BFS, every round remove one '(' or ')' from current strings,
if removed str is valid and never appear before, then put it into answer list.
Whenever we have at least one string in answer list after every round, we stop.
time = n + C(n, n-1)*(n-1) + C(n, n-2)*(n-2) + ... + C(n, 1) * 1 = exponential
*/
public List<String> removeInvalidParentheses(String s) {
  Set<String> curLvl = new HashSet<>();
  List<String> ans = new ArrayList<>();
  if(isValid(s)) {
    ans.add(s);
    return ans;
  }
  curLvl.add(s);
  while(!curLvl.isEmpty() && ans.isEmpty()) {
    Set<String> nxtLvl = new HashSet<>();
    for(String curStr : curLvl) {
      for(int i = 0; i < curStr.length(); i++) {
        if(curStr.charAt(i) == '(' || curStr.charAt(i) == ')') {
          String str = curStr.substring(0, i) + curStr.substring(i+1, curStr.length());
          if (!nxtLvl.contains(str)) { //key!! to avoid duplicates and TLE, check.
            nxtLvl.add(str);
            if(isValid(str)) {
              ans.add(str);
            }
          }
        }
      }
    }
    curLvl = nxtLvl;
  }

  return ans;
}

private boolean isValid(String s) {
  int pair = 0;
  for(char c : s.toCharArray()) {
    if(c == '(') { pair++; }
    else if(c == ')') {
      if(--pair < 0) {
        return false;
      }
    }
  }
  return pair == 0;
}
