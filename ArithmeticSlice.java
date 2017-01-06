//LeetCode - 413
//We can also use DP with the length of slice as varible.
//time = O(n^2)
public int numberOfArithmeticSlices(int[] A) {
  int N = A.length;
  int ans = 0;
  //enumerate all possible start points of slices.
  //if for a start point, the length = 4 slice is not arithmetic, we don't need go further.
  for(int lo = 0; lo < N-2; lo++) {
    int hi = lo + 2;
    int diff = A[hi-1] - A[lo];
    while(hi < N && A[hi] - A[hi-1] == diff) {
      ans++;
      hi++;
    }
  }
  return ans;
}

//LeetCode - 446
//time = O(n^2), space = O(n^2)
public int numberOfArithmeticSlices_II(int[] A) {
  if(A == null || A.length < 3) { return 0; }
  int N = A.length;
  int ans = 0;
  //list.get(i) - map<difference, the number of arithmetic seq ending with A[i]>
  List<Map<Integer, Integer>> list = new ArrayList<>();
  list.add(new HashMap<>());
  for(int end = 1; end < N; end++) {
    Map<Integer, Integer> curMap = new HashMap<>();
    for(int i = 0; i < end; i++) {
      long delta = (long)A[end] - (long)A[i];
      //if delta(difference) exceeds the range of int, it's impossible to have a 3rd valid element to form a arithmetic
      //seq, because all elements are ints.
      if(delta <= Integer.MIN_VALUE || delta > Integer.MAX_VALUE) { continue; }
      Map<Integer, Integer> preMap = list.get(i);
      int dif = A[end] - A[i];
      int preSlNum = preMap.containsKey(dif) ? preMap.get(dif) : 0;
      ans += preSlNum;
      //after preSlNum, we add 1 ==> we have a potential arithmetic seq where 1st is A[i], 2nd is A[end].
      curMap.put(dif, curMap.getOrDefault(dif, 0) + preSlNum + 1);
    }
    list.add(curMap);
  }
  return ans;
}
