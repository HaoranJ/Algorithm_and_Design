//Leetcode - 77 
//DP - C(n, k) = C(n-1, k-1) + C(n-1, k)
//time = O(nk), space = (k)
public List<List<Integer>> combine(int n, int k) {
  List<List<List<Integer>>> preCol = new ArrayList<>();
  List<List<List<Integer>>> curCol = new ArrayList<>();
  preCol.add(new ArrayList<List<Integer>>(){{ add(new ArrayList<Integer>()); }});
  preCol.add(new ArrayList<List<Integer>>(){{ add(new ArrayList<Integer>(){{ add(1); }}); }});
  for (int i = 2; i <= n; i++) {
    for (int j = 0; j <= Math.min(i, k); j++) {
      if (j == 0) {
        List<List<Integer>> list = new ArrayList<>();
        list.add(new ArrayList<>());
        curCol.add(list);
        continue;
      }
      curCol.add(new ArrayList<>());
      if(j < preCol.size()) {
        for (List<Integer> list : preCol.get(j)) {
          List<Integer> copyList = new ArrayList<>(list);
          curCol.get(j).add(copyList);
        }
      }

      for (List<Integer> list : preCol.get(j-1)) {
        List<Integer> copyList = new ArrayList<>(list);
        copyList.add(i);
        curCol.get(j).add(copyList);
      }
    }
    preCol.clear();
    List<List<List<Integer>>> temp = preCol;
    preCol = curCol;
    curCol = temp;
  }
  return preCol.get(k);
}

//Recursion - C(n, k) = C(n-1, k-1) + C(n-1, k)
public List<List<Integer>> combine(int n, int k) {
  List<List<Integer>> combs = new ArrayList<>();
  //base case
  if (k > n) { return combs; }
  if (k == 0) {
    combs.add(new ArrayList<>());
    return combs;
  }
  if (k == n) {
    List<Integer> comb = new ArrayList<>();
    for (int i = 1; i <= n; i++) {
      comb.add(i);
    }
    combs.add(comb);
    return combs;
  }
  for (List<Integer> list : combine(n-1, k)) {
    List<Integer> copyList = new ArrayList<>(list);
    combs.add(copyList);
  }
  for (List<Integer> list : combine(n-1, k-1)) {
    List<Integer> copyList = new ArrayList<>(list);
    copyList.add(n);
    combs.add(copyList);
  }
  return combs;
}

//Backtracking, recursion
static List<List<Integer>> ans;

public List<List<Integer>> combine(int n, int k) {
  assert k <= n;
  ans = new ArrayList<>();
  go(new ArrayList<>(), 1, k, n);
  return ans;
}

private void go(List<Integer> list, int startNum, int numLeft, int n) {
  //base case
  if(numLeft == 0) {
    List<Integer> listCopy = new ArrayList<>(list);
    ans.add(listCopy);
    return;
  }
  for (int i = startNum; i <= n - numLeft + 1; i++) {
    list.add(i);
    go(list, i+1, numLeft-1, n);
    list.remove(list.size()-1);
  }
}
