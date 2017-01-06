import java.util.*;

public class Permutations {
	//LeetCode 46
	// time = O(n!), space = O(n*(n!))
	public List<List<Integer>> permute(int[] nums) {
		int n = nums.length;
		List<List<Integer>> ans = new ArrayList<>();
		for (int j = 0; j < n; j++) {
			int x = nums[j];
			if (ans.size() == 0) {
				int idx = 0;
				while (idx < n) {
					List<Integer> list = new ArrayList<>();
					for (int k = 0; k < n; k++) {
						if (k == idx) { list.add(idx, x); }
						else { list.add(k, null); }
					}
					idx++;
					ans.add(list);
				}
			} else {
				List<List<Integer>> temp = new ArrayList<>(ans);
				ans.clear();
				Iterator<List<Integer>> itr = temp.iterator();
				while (itr.hasNext()) {
					List<Integer> list = new ArrayList<>(itr.next());
					for (int k = 0; k < n; k++) {
						if (list.get(k) == null) {
							List<Integer> newList = new ArrayList<>(list);
							newList.set(k, x);
							ans.add(newList);
						}
					}
				}
			}
		}
		return ans;
	}

	//LeetCode 47
	public List<List<Integer>> permuteUnique(int[] nums) {
		Set<List<Integer>> set = new HashSet<>();
		List<List<Integer>> ans = new ArrayList<>();
		int n = nums.length;
		for (int j = 0; j < n; j++) {
			int x = nums[j];
			if (j == 0) {
				for (int k = 0; k < n; k++) {
					List<Integer> list = new ArrayList<>();
					for (int i = 0; i < n; i++) {
						if (i != k) { list.add(i, null); }
						else { list.add(k, x); }
					}
					set.add(list);
				}
			} else {
				Set<List<Integer>> temp = new HashSet<>(set);
				set.clear();
				Iterator<List<Integer>> itr = temp.iterator();
				while (itr.hasNext()) {
					List<Integer> list = itr.next();
					for (int i = 0; i < n; i++) {
						if (list.get(i) == null) {
							List<Integer> newList = new ArrayList<>(list);
							newList.set(i, x);
							set.add(newList);
						}
					}
				}
			}
		}
		ans.addAll(set);
		return ans;
	}


}