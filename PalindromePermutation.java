import java.util.*;

public class PalindromePermutation {
<<<<<<< 80c257f2999f994806b99e0950c89320b1fb05fb
=======
	/* LeetCode-266*/
>>>>>>> 8/14/16
	public boolean canPermutePalindrome(String s) {
		HashMap<Character, Integer> map = new HashMap<>();
		int len = s.length();
		for (int j = 0; j < len ; j++) {
			char ch = s.charAt(j);
			if (map.containsKey(ch)) {
				map.put(ch, map.get(ch) + 1);
			} else {
				map.put(ch, 1);
			}
		}
		int oddCharNum = 0;
		for (Map.Entry<Character, Integer> e : map.entrySet()) {
			int val = e.getValue();
			if (val % 2 == 1) { oddCharNum++; }
		}
		return oddCharNum <= 1;
	}

<<<<<<< 80c257f2999f994806b99e0950c89320b1fb05fb
	// public List<String> generatePalindromes(String s) {

	// }

=======
>>>>>>> 8/14/16
	public boolean isPalindrome(String s) {
		int len = s.length();
		for (int j = 0; j < len/2; j++ ) {
			if (s.charAt(j) != s.charAt(len-j-1)) { return false; }
		}
		return true;
	}

<<<<<<< 80c257f2999f994806b99e0950c89320b1fb05fb
	public static void main(String[] args) throws Exception {
		PalindromePermutation pp = new PalindromePermutation();
		Scanner sc = new Scanner(System.in);
		String s = "";
		if (sc.hasNext()) { s = sc.next(); }
		System.out.println(pp.isPalindrome(s));
	}
}
=======
	/*
	LeetCode - 267
	Solution 1, fix position to permute
	*/
	List<String> ans;
	String midChar= "";
	public List<String> generatePalindromes(String s) {
		Map<Character, Integer> map = new HashMap<>();
		ans = new ArrayList<>();
		int oddCharNum = 0;
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			map.put(ch, map.containsKey(ch) ? map.get(ch)+1 : 1);
			oddCharNum += (map.get(ch) % 2 == 0) ? -1 : 1;
		}
		if(oddCharNum > 1) { return ans; }
		//store half of the palindrome as a list
		List<Character> paChs = new ArrayList<>();
		for (Map.Entry<Character, Integer> e : map.entrySet()) {
			int times = e.getValue();
			char ch = e.getKey();
			if (times % 2 == 1) {
				midChar = Character.toString(ch);
			}
			for (int i = 0; i < times/2; i++) {
				paChs.add(ch);
			}
		}
		permute(paChs, new StringBuilder(), new boolean[paChs.size()]);
		return ans;
	}
	private void permute(List<Character> chs, StringBuilder sb, boolean[] used) {
		if (sb.length() == chs.size()) {
			ans.add(sb.toString() + midChar + sb.reverse().toString());
			sb.reverse();
			return;
		}
		for (int i = 0; i < chs.size(); i++) {
			if (!used[i]) {
				//skip duplications
				if(i > 0 && chs.get(i-1) == chs.get(i) && !used[i-1]) { continue; }
				//recursion
				char ch = chs.get(i);
				sb.append(ch);
				used[i] = true;
				permute(chs, sb, used);
				used[i] = false;
				sb.deleteCharAt(sb.length()-1);
			}
		}
	}

	//Solution 2, fix letter to permute
	List<String> ans;
	String midChar= "";
	public List<String> generatePalindromes(String s) {
		Map<Character, Integer> map = new HashMap<>();
		ans = new ArrayList<>();
		int oddCharNum = 0;
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			map.put(ch, map.containsKey(ch) ? map.get(ch)+1 : 1);
			oddCharNum += (map.get(ch) % 2 == 0) ? -1 : 1;
		}
		if(oddCharNum > 1) { return ans; }
		char[] paChs = new char[s.length()/2];
		Arrays.fill(paChs, '\0');
		int ptr = 0;
		for (Map.Entry<Character, Integer> e : map.entrySet()) {
			int times = e.getValue();
			char ch = e.getKey();
			if (times % 2 == 1) {
				midChar = Character.toString(ch);
			}
			for (int i = 0; i < times/2; i++) {
				paChs[ptr++] = ch;
			}
		}
		recur(paChs, 0, new char[paChs.length]);
		return ans;
	}
	private void recur(char[] paChs, int charIdx, char[] seq) {
		if(charIdx == paChs.length) {
			StringBuilder sb = new StringBuilder();
			sb.append(seq);
			String res = sb.toString() + midChar + sb.reverse().toString();
			if(!ans.contains(res)) { ans.add(res); }
			return;
		}
		char ch = paChs[charIdx];
		for (int i = 0; i < paChs.length; i++) {
			if (seq[i] == '\0') {
				//skip duplications
				if (i > 0 && paChs[i] == paChs[i-1] && charIdx > i) { continue; }
				seq[i] = ch;
				recur(paChs, charIdx+1, seq);
				seq[i] = '\0';
			}
		}
	}
}
>>>>>>> 8/14/16
