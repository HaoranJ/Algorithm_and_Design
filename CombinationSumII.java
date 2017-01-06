/** LeetCode 40 **/
//Recursion.
public List<List<Integer>> combinationSum2(int[] nums, int target) {
  List<List<Integer>> ans = new ArrayList<>();
  Arrays.sort(candidates);
  return combinationSum2(nums, target, 0);
}

private List<List<Integer>> combinationSum2(int[] nums, int target, int startIdx) {
  List<List<Integer>> ret = new ArrayList<>();
  //base case
  if(target == 0) {
    ret.add(new ArrayList<>());
    return ret;
  }
  int i = startIdx;
  while (i < nums.length && nums[i] <= target) {
    for (List<Integer> list : combinationSum2(nums, target - nums[i], i+1)) {
      list.add(nums[i]);
      ret.add(list);
    }
    i++;
    while(i < nums.length && nums[i] == nums[i-1]) { i++; }
  }
  return ret;
}

//DFS, Backtracking
static List<List<Integer>> ans;
public List<List<Integer>> combinationSum2(int[] candidates, int target) {
  ans = new ArrayList<>();
  if(candidates.length == 0) { return ans; }
  Arrays.sort(candidates);
  go(new ArrayList<>, 0, target, candidates);
  return ans;
}

private void go(List<Integer> list, int startIdx, int target, int[] candidates) {
  if (target == 0) {
    ans.add(new ArrayList<>(list));
    return;
  }
  int i = startIdx;
  int num;
  while (i < candidates.length && (num = candidates[i]) <= target) {
    list.add(num);
    go(list, i+1, target-num, candidates);
    list.remove(list.size()-1);
    i++;
    while (i < candidates.length && candidates[i] == candidates[i-1]) {
      i++;
    }
  }
}
