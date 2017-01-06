import java.util.*;

public class KSum {
	//LeetCode 1 - 2Sum
  public int[] twoSum(int[] nums, int target) {
      //time = O(n), space = O(n)(worst case)
      HashMap<Integer, Integer> map = new HashMap<>();
      int[] ans = new int[2];
      for(int j = 0; j < nums.length; j++) {
          int x = nums[j];
          int y = target - x;
          if (map.containsKey(y)){
              ans[0] = map.get(y);
              ans[1] = j+1;
              break;
          }
          map.put(x, j+1);
      }
      return ans;
  }

  //Use two pointer. time = O(n), space = O(n)
  public int[] twoSumTwoPointers(int[] nums, int target) {
  	int[] ans = new int[2];
  	Map<Integer, List<Integer>> map = new HashMap<>();
  	for (int j = 0; j < nums.length; j++) {
  		int x = nums[j];
  		map.putIfAbsent(x, new ArrayList<Integer>());
  		map.get(x).add(j+1);
  	}
  	int lo = 0;
  	int hi = nums.length-1;
  	Arrays.sort(nums);
  	while (lo < hi) {
  		int sum = nums[lo] + nums[hi];
  		if (sum == target) {
  			if (nums[lo] == nums[hi]) {
  				ans[0] = map.get(nums[lo]).get(0);
  				ans[1] = map.get(nums[lo]).get(1);
  			} else {
  				ans[0] = map.get(nums[lo]).get(0);
  				ans[1] = map.get(nums[hi]).get(0);
  			}
  		} else if (sum < target) {
  			lo++;
  		} else {
  			hi--;
  		}
  	}
  	Arrays.sort(ans);
  	return ans;
  }

  //LeetCode - 3Sum
  //Use HashSet, time = O(n^2)
  public List<List<Integer>> threeSum(int[] nums) {
  	Arrays.sort(nums);
  	Set<List<Integer>> set = new HashSet<>();
  	for (int j = 0; j < nums.length-2; j++) {
  		int a = nums[j];
  		int lo = j+1;
  		int hi = nums.length-1;
  		while (lo < hi) {
  			int b = nums[lo];
  			int c = nums[hi];
  			if (a+b+c == 0) {
  				set.add(Arrays.asList(a,b,c));
  				lo++;
  				hi--;
  			} else if (a+b+c > 0) {
  				hi--;
  			} else {
  				lo++;
  			}
  		}
  	}
  	return new ArrayList<List<Integer>>(set);
  }
  //In place, time = O(n^2). Skip the duplicates
  public List<List<Integer>> threeSumInPlace(int[] nums) {
  	Arrays.sort(nums);
  	List<List<Integer>> ans = new ArrayList<>();
  	for (int j = 0; j < nums.length-2; j++) {
  		if (j == 0 || nums[j-1] != nums[j]) {
  			int a = nums[j];
	  		int lo = j+1;
	  		int hi = nums.length-1;
	  		while (lo < hi) {
	  			int b = nums[lo];
	  			int c = nums[hi];
	  			if (a+b+c == 0) {
	  				ans.add(Arrays.asList(a,b,c));
	  				while (lo < hi && nums[hi] == nums[hi-1]) {
	  					hi--;
	  				}
	  				hi--;
	  				while (lo < hi && nums[lo] == nums[lo+1]) {
	  					lo++;
	  				}
	  				lo++;
	  			} else if (a+b+c > 0) {
	  				hi--;
	  			} else {
	  				lo++;
	  			}
	  		}
  		}
  	}
  	return ans;
  }

  //LeetCode - 3Sum Closest
  //time = O(n^2), use two pointers.
  public int threeSumClosest(int[] nums, int target) {
    Arrays.sort(nums);
    int ans = 0;
    int closest = Integer.MAX_VALUE;
    for(int j = 0; j < nums.length-2; j++) {
      int a = nums[j];
      int lo = j+1;
      int hi = nums.length-1;
      while(lo < hi) {
        int sum = a + nums[lo] + nums[hi];
        int diff = Math.abs(sum-target);
        if (diff < closest) {
        	ans = sum;
        	closest = diff;
        }
        //Give two pointer next-step direction.
        if (sum == target) {
        	return sum;
        } else if (sum > target) {
        	hi--;
        } else {
        	lo++;
        }
      }
    }
    return ans;
  }

  //LeetCode - 3Sum Smaller
  //time = O(n^2)
  public int threeSumSmaller(int[] nums, int target) {
  	Arrays.sort(nums);
    int count = 0;
    for (int j = 0;	j < nums.length-2; j++) {
   		int lo = j+1;
   		int hi = nums.length-1;
   		while (lo < hi) {
   			int sum = nums[j] + nums[lo] + nums[hi];
   			if (sum < target) {
   				count += hi-lo;
   				lo++;
   			} else {
   				hi--;
   			}
   		}
    }   
    return count;   
  }

  //LeetCode 18 - 4Sum
  //time = O(n^3)
  public List<List<Integer>> fourSumInPlace(int[] nums, int target) {
  	Arrays.sort(nums);
  	List<List<Integer>> ans = new ArrayList<>();
  	for (int i = 0; i < nums.length-3; i++) {
  		if (i == 0 || nums[i] != nums[i-1]) {
  			int a = nums[i];
  			for (int j = i+1; j < nums.length-2; j++) {
	  			if (j == i+1 || nums[j] != nums[j-1]) {
	  				int b = nums[j];
	  				int need = target - nums[i] - nums[j];
		  			int lo = j+1;
		  			int hi = nums.length-1;
		  			while (lo < hi) {
		  				int c = nums[lo];
		  				int d = nums[hi];
		  				int sum = c + d;
		  				if (sum == need) {
		  					ans.add(Arrays.asList(a,b,c,d));
		  					while (lo < hi && nums[hi] == nums[hi-1]) {
		  						hi--;
		  					}
		  					hi--;
		  					while (lo < hi && nums[lo] == nums[lo+1]) {
		  						lo++;
		  					}
		  					lo++;
		  				} else if (sum > need) {
		  					hi--;
		  				} else {
		  					lo++;
		  				}
		  			}
	  			}
	  		}
  		}
  	}
  	return ans;
  }

  public List<List<Integer>> fourSum(int[] nums, int target) {
  	List<List<Integer>> ans = new ArrayList<>();
  	HashMap<Integer, List<List<Integer>>> map = new HashMap<>();
  	Arrays.sort(nums);
  	for (int j = 0; j < nums.length; j++) {
  		for (int p = j+1; p < nums.length; p++) {
  			int key = nums[j] + nums[p];
  			map.putIfAbsent(key, new ArrayList<List<Integer>>());
  			List<Integer> pair = new ArrayList<>();
  			pair.add(nums[j]);
  			pair.add(nums[p]);
  			map.get(key).add(pair);
  		}
  	}
  	for(Map.Entry<Integer, List<List<Integer>>> e : map.entrySet()) {
  		int x = e.getKey();
  		int y = target - x;
  		if (!map.containsKey(y)) {
  			continue;
  		}
  		if (x == y) {
  			List<List<Integer>> list = map.get(x);
  			for (int i = 0; i < list.size(); i++) {
  				for (int j = i+1; j < list.size(); j++) {
  					List<Integer> quad = new ArrayList<>();
  					quad.addAll(list.get(i));
  					quad.addAll(list.get(j));
  					Collections.sort(quad);
  					ans.add(quad);
  				}
  			}
  		} else {
  			List<List<Integer>> xList = map.get(x);
  			List<List<Integer>> yList = map.get(y);
  			for (int i = 0; i < xList.size(); i++) {
  				for (int j = 0; j < yList.size(); j++) {
  					List<Integer> quad = new ArrayList<>();
  					quad.addAll(xList.get(i));
  					quad.addAll(yList.get(j));
  					Collections.sort(quad);
  					ans.add(quad);
  				}
  			}
  		}
  	}
  	return ans;
  }
}