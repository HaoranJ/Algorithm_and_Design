import java.util.*;

public class ValidAnagram {
	public boolean isAnagram(String s, String t) {
		if (s.length() != t.length()) {
			return false;
		}
		HashMap<Character, Integer> map = new HashMap<>();
		char ch = 'a';
		for (int j = 0; j < s.length(); j++ ) {
			ch = s.charAt(j);
			if (map.containsKey(ch)) {
				map.put(ch, map.get(ch)+1);
			} else {
				map.put(ch, 1);
			}
		}
		for (int j = 0; j < t.length(); j++ ) {
			ch = t.charAt(j);
			if (!map.containsKey(ch)) {
				return false;
			} else {
				if (map.get(ch) == 1) {
					map.remove(ch);
				} else {
					map.put(ch, map.get(ch) - 1);
				}
			}
		}
		return map.size() == 0;
	}

	public boolean useSort(String s, String t) {
		if (s.length() != t.length()) {
			return false;
		}
		char[] st = s.toCharArray();
		char[] ct = t.toCharArray();
		Arrays.sort(st);
		Arrays.sort(ct);
		return Arrays.equals(st, ct);
	}
}