import java.util.*;

public class Subsets {
  /** LeetCode - 90 Subsets II **/
  //Bit manipulate
  public List<List<Integer>> subsetsWithDup(int[] nums) {
    List<List<Integer>> ans = new ArrayList<>();
    Arrays.sort(nums);
    int subsetNum = 1 << nums.length;
    for (int sub = 0; sub < subsetNum; sub++) {
      List<Integer> list = new ArrayList<>();
      boolean isDuplicate = false;
      for (int i = 0; i < nums.length; i++) {
        if ((sub >> i & 1) == 1) {
          if (i > 0 && nums[i-1] == nums[i] && (sub >> (i-1) & 1) == 0) {
            isDuplicate = true;
            break;
          }
          list.add(nums[i]);
        }
      }
      if (!isDuplicate) {
        ans.add(list);
      }
    }
    return ans;
  }

  //Iteration - mainly use elements
  /* Then how many subsets are there if there are duplicate elements? We can treat duplicate element as a spacial element.
  For example, if we have duplicate elements (5, 5), instead of treating them as two elements that are duplicate, we can treat it as one special element 5,
  but this element has more than two choices: you can either NOT put it into the subset, or put ONE 5 into the subset, or put TWO 5s into the subset.
  Therefore, we are given an array (a1, a2, a3, ..., an) with each of them appearing (k1, k2, k3, ..., kn) times, the number of subset is (k1+1)(k2+1)...(kn+1).
  We can easily see how to write down all the subsets similar to the approach above.
  */
  public List<List<Integer>> subsetsWithDup(int[] nums) {
    assert nums != null;
    Arrays.sort(nums);
    List<List<Integer>> ans = new ArrayList<>();
    ans.add(new ArrayList<>());
    int i = 0;
    while (i < nums.length) {
      int count = 1;
      while(i < nums.length - 1 && nums[i] == nums[i+1]) { count++; i++; }
      int num = nums[i];
      List<List<Integer>> temp = new ArrayList<>();
      for (List<Integer> prevList : ans) {
        List<Integer> copyList = new ArrayList<>(prevList);
        for (int size = 1; size <= count; size++) {
          copyList.add(num);
          temp.add(new ArrayList<>(copyList));
        }
      }
      ans.addAll(temp); //avoid ConcurrentModificationException
      i++;
    }
    return ans;
  }

  //Recursion. - mainly use lengths of subsets
  //Could use DP, but needs much more space
  public List<List<Integer>> subsetsWithDup(int[] nums) {
    assert nums != null;
    Arrays.sort(nums);
    List<List<Integer>> ans = new ArrayList<>();
    for (int l = 0; l <= nums.length; l++) {
      ans.addAll(subsetsWithDup(nums, 0, l));
    }
    return ans;
  }
  private List<List<Integer>> subsetsWithDup(int[] nums, int startIdx, int size) {
    List<List<Integer>> ret = new ArrayList<>();
    if(size == 0) {
      ret.add(new ArrayList<>());
      return ret;
    }
    int i = startIdx;
    while (i <= nums.length - size) {
      for (List<Integer> preList : subsetsWithDup(nums, i+1, size-1)) {
        preList.add(nums[i]);
        ret.add(preList);
      }
      i++;
      while (i <= nums.length - size && nums[i] == nums[i-1]) { i++; }
    }
    return ret;
  }

  //DFS, Backtracking
  List<List<Integer>> ans;
  public List<List<Integer>> subsetsWithDup_DFS(int[] nums) {
    assert nums != null;
    ans = new ArrayList<>();
    Arrays.sort(nums);
    for (int l = 0; l <= nums.length; l++) {
      dfs(nums, 0, l, new ArrayList<>());
    }
    return ans;
  }

  private void dfs(int[] nums, int startIdx, int size, List<Integer> list) {
    if (size == 0) {
      List<Integer> copyList = new ArrayList<>(list);
      ans.add(copyList);
      return;
    }
    int i = startIdx;
    while (i <= nums.length - size) {
      list.add(nums[i]);
      dfs(nums, i+1, size-1, list);
      list.remove(list.size()-1);
      i++;
      while (i <= nums.length - size && nums[i] == nums[i-1]) {
        i++;
      }
    }
  }

  /** LeetCode - 78 **/
  //DFS, Backtracking, time = exponential, space = O(n)
  private List<List<Integer>> ans = new ArrayList<>();
  public List<List<Integer>> subsets(int[] nums) {
    Arrays.sort(nums);
    ans.clear();
    int n = nums.length;
    for(int l = 0; l <= n; l++) {
      explore(nums, 0, l, new ArrayList<>());
    }
    return ans;
  }

  private void explore(int[] nums, int start, int len, List<Integer> list) {
    if(len == 0) {
      List<Integer> copy = new ArrayList<>(list);
      ans.add(copy);
      return;
    }
    int n = nums.length;
    for(int i = start; i <= n-len; i++) {
      list.add(nums[i]);
      explore(nums, i+1, len-1, list);
      list.remove(list.size()-1);
    }
  }

  //Bit manipulate, time = O(n*(2^n)), space = O(1)
  /*
  Number of subsets for {1 , 2 , 3 } = 2^3 .
  why ?
  case    possible outcomes for the set of subsets
   1   ->          Take or dont take = 2
   2   ->          Take or dont take = 2
   3   ->          Take or dont take = 2

  therefore , total = 2*2*2 = 2^3 = { { } , {1} , {2} , {3} , {1,2} , {1,3} , {2,3} , {1,2,3} }

  Lets assign bits to each outcome  -> First bit to 1 , Second bit to 2 and third bit to 3
  Take = 1
  Dont take = 0

  0) 0 0 0  -> Dont take 3 , Dont take 2 , Dont take 1 = { }
  1) 0 0 1  -> Dont take 3 , Dont take 2 ,   take 1       =  {1 }
  2) 0 1 0  -> Dont take 3 ,    take 2       , Dont take 1 = { 2 }
  3) 0 1 1  -> Dont take 3 ,    take 2       ,      take 1    = { 1 , 2 }
  4) 1 0 0  ->    take 3      , Dont take 2  , Dont take 1 = { 3 }
  5) 1 0 1  ->    take 3      , Dont take 2  ,     take 1     = { 1 , 3 }
  6) 1 1 0  ->    take 3      ,    take 2       , Dont take 1 = { 2 , 3 }
  7) 1 1 1  ->    take 3     ,      take 2     ,      take 1     = { 1 , 2 , 3 }

  In the above logic ,Insert S[i] only if (j>>i)&1 ==true   { j E { 0,1,2,3,4,5,6,7 }   i = ith element in the input array }

  element 1 is inserted only into those places where 1st bit of j is 1
    if( j >> 0 &1 )  ==> for above above eg. this is true for sl.no.( j )= 1 , 3 , 5 , 7

  element 2 is inserted only into those places where 2nd bit of j is 1
    if( j >> 1 &1 )  == for above above eg. this is true for sl.no.( j ) = 2 , 3 , 6 , 7

  element 3 is inserted only into those places where 3rd bit of j is 1
    if( j >> 2 & 1 )  == for above above eg. this is true for sl.no.( j ) = 4 , 5 , 6 , 7

  Time complexity : O(n*2^n) , for every input element loop traverses the whole solution set length i.e. 2^n
  */
  public List<List<Integer>> subsets(int[] nums) {
    assert nums != null;
    Arrays.sort(nums);
    int eleNum = nums.length;
    int listNum = 2 << eleNum;
    List<List<Integer>> ans = new ArrayList<>();
    for (int i = 0; i < listNum; i++) {
      ans.add(new ArrayList<Integer>());
    }
    for (int i = 0; i < eleNum; i++) {
      for (int j = 0; j < listNum; j++) {
        if ((j >> i) & 1) {
          ans.get(j).add(nums[i]);
        }
      }
    }
    return ans;
  }
}
