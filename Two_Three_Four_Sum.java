/**LeetCode - Two Sum**/
//1. HashMap - time = O(n), space = O(n)
//2. Two pointers - time = O(nlogn), constant space

/**LeetCode - 15 - Three Sum**/
//time = O(n^2), space = O(1)
public List<List<Integer>> threeSum(int[] nums) {
  List<List<Integer>> tris = new ArrayList<>();
  int n = nums.length;
  Arrays.sort(nums);
  for(int p1 = 0; p1 < n-2; p1++) {
    //avoid duplicates.
    if(p1 > 0 && nums[p1] == nums[p1-1]) {
      continue;
    }
    //convert to 2 SUM question. Two pointers.
    int p2 = p1 + 1, p3 = n-1;
    int target = -nums[p1];
    while(p2 < p3) {
      //avoid duplicates
      if(p2 > p1 + 1 && nums[p2] == nums[p2-1]) {
        p2++;
        continue;
      }
      if(p3 < n - 1 && nums[p3] == nums[p3+1]) {
        p3--;
        continue;
      }
      int n2 = nums[p2], n3 = nums[p3];
      if(n2 + n3 > target) {
        p3--;
      } else if (n2 + n3 < target) {
        p2++;
      } else {
        List<Integer> trp = new ArrayList<Integer>(){{
          add(-target);
          add(n2);
          add(n3);
        }};
        tris.add(trp);
        p2++;
        p3--;
      }
    }
  }
  return tris;
}

/**LeetCode 16 - 3 Sum Closest**/
// Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. Return the sum of the three integers. You may assume that each input would have exactly one solution.
//
// For example, given array S = {-1 2 1 -4}, and target = 1.
//
// The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).

//convert it to 2 Sum with two pointers.
//time = O(n^2)
public int threeSumClosest(int[] nums, int target) {
  Arrays.sort(nums);
  int minDiff = Integer.MAX_VALUE;
  int closest = 0;
  int idx1, idx2, idx3, n1, n2, n3;
  for(idx1 = 0; idx1 < nums.length - 2; idx1++) {
    n1 = nums[idx1];
    int target2 = target - n1;
    idx2 = idx1 + 1;
    idx3 = nums.length-1;
    //truncate some cases.
    if(nums[idx2] + nums[idx2+1] >= target2) {
      int diff = Math.abs(nums[idx2]+nums[idx2+1]-target2);
      if(diff < minDiff) {
        closest = n1+nums[idx2]+nums[idx2+1];
        minDiff = diff;
      }
      continue;
    }
    if(nums[idx3] + nums[idx3-1] <= target2) {
      int diff = Math.abs(nums[idx3]+nums[idx3-1]-target2);
      if(diff < minDiff) {
        closest = n1+nums[idx3]+nums[idx3-1];
        minDiff = diff;
      }
      continue;
    }
    //2 Sum - two pointers
    while(idx2 < idx3) {
      n2 = nums[idx2];
      n3 = nums[idx3];
      if(n2 + n3 == target2) {
        return n1 + n2 + n3;
      }
      int diff = Math.abs(n2+n3-target2);
      if(diff < minDiff) {
        closest = n1+n2+n3;
        minDiff = diff;
      }
      if (n2 + n3 < target2) {
        idx2++;
      } else {
        idx3--;
      }
    }
  }
  return closest;
}

/**LeetCode - 18 - Four Sum**/
//1. K Sum - recursion - time = O(n^3)
public List<List<Integer>> fourSum(int[] nums, int target) {
  Arrays.sort(nums);
  return kSum(nums, target, 4, 0);
}

//General K Sum - recursively DFS the problem until reach two sum.
//Assume the array nums is sorted.
private List<List<Integer>> kSum(int[] nums, int target, int k, int startIdx) {
  List<List<Integer>> ans = new ArrayList<>();
  //base case - two sum - two pointers.
  if(k == 2) {
    int lo = startIdx, hi = nums.length-1;
    while(lo < hi) {
      if(lo > startIdx && nums[lo] == nums[lo-1]) {
        lo++;
        continue;
      }
      if(hi < nums.length-1 && nums[hi] == nums[hi+1]) {
        hi--;
        continue;
      }
      int loNum = nums[lo], hiNum = nums[hi];
      int sum = loNum + hiNum;
      if(sum == target) {
        List<Integer> list = new ArrayList<Integer>(){{
          add(loNum);
          add(hiNum);
        }};
        lo++;
        hi--;
        ans.add(list);
      } else if (sum < target) {
        lo++;
      } else {
        hi--;
      }
    }
  } else {
    //DFS (recursion)
    for(int i = startIdx; i < nums.length - k + 1; i++) {
      //avoid duplicates
      if(i > startIdx && nums[i] == nums[i-1]) {
        continue;
      }
      List<List<Integer>> preAns = kSum(nums, target - nums[i], k-1, i+1);
      for(List<Integer> seq : preAns) {
        seq.add(nums[i]);
        ans.add(seq);
      }
    }
  }
  return ans;
}

//2. Four Sum - Tree Map
//Convert 4 Sum => 2 * 2 Sum, use TreeMap<sum of pair, list of pair>, and use two pointers to these pairs sorted by
//the sum of pair.
//time = O(n^2logn) (build a treemap.)
//Improve - we can get the possible range of sum of pair, use List<Pair>[] to replace TreeMap, but this needs the array
//nums to be dense(range of possible sum is similar to nums.length), otherwise two-pointer part need span a range of the sum of pair.
class Pair {
  int val1, val2;
  int idx1, idx2;
  Pair(int v1, int v2, int i1, int i2) {
    val1 = v1;
    val2 = v2;
    idx1 = i1;
    idx2 = i2;
  }
}
public List<List<Integer>> fourSum(int[] nums, int target) {
  List<List<Integer>> ans = new ArrayList<>();
  TreeMap<Integer, List<Pair>> pairMap = new TreeMap<>();
  Arrays.sort(nums);
  int n = nums.length;
  if(n < 4) {
    return ans;
  }
  for(int i = 0; i < n-1; i++) {
    //Don't avoid duplicates here, counter example - {0,0,0,0}, target = 0
    // if(i > 0 && nums[i] == nums[i-1]) {
    //   continue;
    // }
    for(int j = i+1; j < n; j++) {
      // if(j > i+1 && nums[j] == nums[j-1]) {
      //   continue;
      // }
      int v1 = nums[i], v2 = nums[j];
      int pairSum = v1 + v2;
      pairMap.putIfAbsent(pairSum, new ArrayList<>());
      pairMap.get(pairSum).add(new Pair(v1, v2, i, j));
    }
  }
  //Two pointers.
  Map.Entry<Integer, List<Pair>> et1 = pairMap.firstEntry();
  Map.Entry<Integer, List<Pair>> et2 = pairMap.lastEntry();
  //Consider NullPointerException
  while(et1 != null && et2 != null && et1.getKey() <= et2.getKey()) {
    int pairSum1 = et1.getKey(), pairSum2 = et2.getKey();
    if(pairSum1 + pairSum2 == target) {
      for(Pair p1 : et1.getValue()) {
        for(Pair p2 : et2.getValue()) {
          if(p1.idx2 < p2.idx1) {
            List<Integer> quad = Arrays.asList(p1.val1, p1.val2, p2.val1, p2.val2);
            //avoid duplicates
            if(!ans.contains(quad)) {
              ans.add(quad);
            }
          }
        }
      }
      et1 = pairMap.higherEntry(et1.getKey());
      et2 = pairMap.lowerEntry(et2.getKey());
    } else if (pairSum1 + pairSum2 > target) {
      et2 = pairMap.lowerEntry(et2.getKey());
    } else {
      et1 = pairMap.higherEntry(et1.getKey());
    }
  }
  return ans;
}

//3. Pairwise method (Best)
//time = O(n^2)
//Observation - if we have two pointers lo and hi (lo < hi), to get all non-duplicate quadruplets contains lo-hi pair,
//all we need is all the pairs from 0 - (lo-1)
class Pair {
  int val1, val2;
  Pair(int v1, int v2) {
    val1 = v1;
    val2 = v2;
  }
}
public List<List<Integer>> fourSum(int[] nums, int target) {
  List<List<Integer>> ans = new ArrayList<>();
  Map<Integer, List<Pair>> pairMap = new HashMap<>();
  for(int lo = 0; lo < nums.length - 1; lo++) {
    //check if there are matched pairs.
    for(int hi = lo+1; hi < nums.length; hi++) {
      if(hi > lo+1 && nums[hi] == nums[hi-1]) {
        continue;
      }
      int need = target - (nums[lo] + nums[hi]);
      if(pairMap.containsKey(need)) {
        for(Pair pair : pairMap.get(need)) {
          List<Integer> quad = Arrays.asList(pair.val1, pair.val2, nums[lo], nums[hi]);\
          if(!ans.contains(quad)) {
            ans.add(quad);
          }
        }
      }
    }
    //put pairs into map for following lo.
    for(int i = 0; i < lo; i++) {
      if(i > 0 && nums[i] == nums[i-1]) {
        continue;
      }
      int val1 = nums[i], val2 = nums[lo];
      pairMap.putIfAbsent(val1+val2, new ArrayList<>());
      pairMap.get(val1+val2).add(new Pair(val1, val2));
    }
  }
  return ans;
}

/** LeetCode - 259 3 Sum Smaller **/
// Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.
//
// For example, given nums = [-2, 0, 1, 3], and target = 2.
//
// Return 2. Because there are two triplets which sums are less than 2:
//
// [-2, 0, 1]
// [-2, 0, 3]
// Follow up:
// Could you solve it in O(n2) runtime?

//convert to 2 Sum with two pointers.
//time  = O(n^2)
public int threeSumSmaller(int[] nums, int target) {
  Arrays.sort(nums);
  int ans = 0;
  for(int i = 0; i < nums.length-2; i++) {
    int lo = i+1, hi = nums.length-1;
    int n1 = nums[i];
    int delta = target - n1;
    while(lo < hi) {
      int n2 = nums[lo], n3 = nums[hi];
      if(n2 + n3 < delta) {
        ans += hi - lo;
        lo++;
      } else {
        hi--;
      }
    }
  }
  return ans;
}

/** LeetCode - 454 Four Sum II **/
//time = O(n^2)
public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
  Map<Integer, Integer> pairMap = new HashMap<>();
  for(int a : A) {
    for(int b : B) {
      int sum = a + b;
      pairMap.put(sum, pairMap.getOrDefault(sum, 0) + 1);
    }
  }
  int count = 0;
  for(int c : C) {
    for(int d : D) {
      int sum = c + d;
      int target = -sum;
      if(pairMap.containsKey(target)) {
        count += pairMap.get(target);
      }
    }
  }
  return count;
}
