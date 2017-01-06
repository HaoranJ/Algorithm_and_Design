import java.util.*;

public class GroupAnagrams {
	//time = O(nlogn), space = O(n)
	public List<List<String>> groupAnagrams(String[] strs) {
		HashMap<String, List<String>> map = new HashMap<>();
		List<List<String>> ret = new ArrayList<>();
		for (String s : strs) {
			String ana = getAnagram(s);
			if (map.containsKey(ana)) {
				map.get(ana).add(s);
			} else {
				List<String> newList = new ArrayList<>();
				newList.add(s);
				map.put(ana, newList);
			}
		}
		for (Map.Entry<String, List<String>> e : map.entrySet() ) {
			Collections.sort(e.getValue());
			ret.add(e.getValue());
		}
		return ret;
	}

	private String getAnagram(String s) {
		char[] ana = s.toCharArray();
		Arrays.sort(ana);
		String ret = new String(ana);
		return ret;
	}

	public static void main(String[] args) throws Exception {
		GroupAnagrams ga = new GroupAnagrams();
		Scanner sc = new Scanner(System.in);
		ArrayList<String> inputs = new ArrayList<>();
		while (sc.hasNext()) {
			inputs.add(sc.next());
		}
		sc.close();
		List<List<String>> ans = ga.groupAnagrams(inputs.toArray(new String[inputs.size()]));
		for (List<String> ls : ans ) {
			System.out.println(ls);
		}
	}
}