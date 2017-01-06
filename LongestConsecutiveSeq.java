int[] id;
int[] sz;
int maxLen = 0;
//**Union Find**, the key is to find the connection of two components.
//Since the find() and union() method is similar to constant time complexity,
//the overall time = O(n), space = O(n)
public int longestConsecutive(int[] nums) {
  Map<Integer, Integer> valIdxMap = new HashMap<>();
  id = new int[nums.length];
  sz = new int[nums.length];
  for (int i = 0; i < nums.length; i++) {
    id[i] = i;
    sz[i] = 1;
  }

  for (int i = 0; i < nums.length; i++) {
    int num = nums[i];
    if (!valIdxMap.containsKey(num)) {
      valIdxMap.put(num, i);
      if (valIdxMap.containsKey(num-1)) {
        union(i, valIdxMap.get(num-1));
      }
      if (valIdxMap.containsKey(num+1)) {
        union(i, valIdxMap.get(num+1));
      }
    }
  }
  return maxLen;
}

private int find(int i) {
  while (i != id[i]) {
    //path compression
    id[i] = id[id[i]];
    i = id[i];
  }
  return i;
}

private void union(int p, int q) {
  int rootP = find(p);
  int rootQ = find(q);
  if(rootP == rootQ) { return; }
  //weighted
  if (sz[rootP] > sz[rootQ]) {
    id[rootQ] = rootP;
    sz[rootP] += sz[rootQ];
    maxLen = Math.max(maxLen, sz[rootP]);
  } else {
    id[rootP] = rootQ;
    sz[rootQ] += sz[rootP];
    maxLen = Math.max(maxLen, sz[rootQ]);
  }
}

//time = O(n), space = O(n)
public int longestConsecutive(int[] nums) {
  Set<Integer> set = new HashSet<>();
  for (int num : nums) {
    set.add(num);
  }
  int ans = 0;
  for (int num : nums) {
    if (set.contains(num)) {
      int cons = 1;
      set.remove(num);
      int cent = num;
      while (set.contains(--num)) {
        set.remove(num);
        cons++;
      }
      while (set.contains(++cent)) {
        set.remove(cent);
        cons++;
      }
      ans = Math.max(ans, cons);
    }
  }
  return ans;
}

//time = O(n), space = O(n)
public int longestConsecutive(int[] nums) {
  Map<Integer, Integer> map = new HashMap<>();
  int ans = 0;
  for (int num : nums) {
    if (!map.containsKey(num)) { //avoid duplicates
      int left = map.containsKey(num-1) ? map.get(num-1) : 0;
      int right = map.containsKey(num+1) ? map.get(num+1) : 0;
      int len = left + right + 1;
      ans = Math.max(ans, len);
      map.put(num, len);
      //update boundaries
      map.put(num-left, len);
      map.put(num+right, len);
    }
  }
  return ans;
}
