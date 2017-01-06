import java.util.*;

public class SummaryRanges {
	public List<String> summaryRanges(int[] nums) {
		List<String> ret = new ArrayList<String>();
		if (nums.length == 0) {
			return ret;
		}
		if (nums.length == 1) {
			ret.add(String.valueOf(nums[0]));
			return ret;
		}
		int i = 0;
		int j = 1;
		StringBuilder sb = new StringBuilder();
		while (i < nums.length) {
			while (j < nums.length) {
				if (nums[j] - nums[j-1] == 1) {
					j++;
				} else {
					addString(i, j, sb, nums, ret);
					i = j;
					j++;
				}
			}
			addString(i, nums.length, sb, nums, ret);
			break;
		}
		return ret;
	}

	private void addString(int i, int j, StringBuilder sb, int[] nums, List<String> ret) {
		if (i + 1 == j) {
			sb.append(nums[i]);
		} else {
			sb.append(nums[i] + "->" + nums[j-1]);
		}
		ret.add(sb.toString());
		sb.delete(0, sb.length());		
	}
}