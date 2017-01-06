import java.util.*;

public class ContainsDuplicate {
	public static boolean containsDuplicate(int... nums) {
		if(nums == null || nums.length == 0) {
        return false;
    }
		HashSet<Integer> set = new HashSet<>();
		for(int num : nums) {
			if(set.contains(num)) {
				return true;
			}
			set.add(num);
		}
		return false;
	}

	public static boolean containsNearbyDuplicate(int[] nums, int k) {
		if (nums == null || nums.length == 0) {
<<<<<<< 80c257f2999f994806b99e0950c89320b1fb05fb
			return false;	
=======
			return false;
>>>>>>> 8/14/16
		}
		int len = nums.length;
		// assert k <= (len-1);
		HashMap<Integer, Integer> map = new HashMap<>();
		for(int j = 0; j < Math.min(k, len); j++) {
			int num = nums[j];
			if (map.containsKey(num)) {
				return true;
			} else {
				map.put(num, 1);
			}
		}
		for(int x = k; x < len; x++) {
			int num = nums[x];
			if (map.containsKey(num)) {
				return true;
			} else {
				map.put(num, 1);
				int leftNum = nums[x-k];
				map.remove(leftNum);
			}
		}
		return false;
	}
	public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
		if (nums == null || nums.length == 0) {
			return false;
		}
		int len = nums.length;
		TreeMap<Integer, Integer> map = new TreeMap<>();
		for(int j = 0; j < Math.min(len, k); j++) {
			int num = nums[j];
			if (getDiff(map, num) <= t) {
				return true;
			}
			map.put(num, 1);
		}
		for(int j = k; j < Math.max(len, k); j++) {
			int num = nums[j];
			if (getDiff(map, num) <= t) {
				return true;
			} else {
				int leftKey = nums[j - k];
				map.put(num, 1);
				map.remove(leftKey);
			}
		}
		return false;
	}

	private long getDiff(TreeMap<Integer, Integer> map, int x) {
		long ceilingDiff = map.ceilingKey(x) == null ? Long.MAX_VALUE : (long)map.ceilingKey(x) - (long)x;
		long floorDiff = map.floorKey(x) == null ? Long.MAX_VALUE : (long)x - (long)map.floorKey(x);
		return ceilingDiff > floorDiff ? floorDiff : ceilingDiff;
	}

	public static void main(String[] args) {
		int[] nums = {-1,2147483647};
		ContainsDuplicate cd = new ContainsDuplicate();
		int a = 2147483647;
<<<<<<< 80c257f2999f994806b99e0950c89320b1fb05fb
		//\\System.out.println(Long.MAX_VALUE > a);
		System.out.println(cd.containsNearbyAlmostDuplicate(nums, 1, 2147483647));
	}
}
=======
		System.out.println(cd.containsNearbyAlmostDuplicate(nums, 1, 2147483647));
	}
}
>>>>>>> 8/14/16
