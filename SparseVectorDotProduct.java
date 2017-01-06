import java.util.*;

public class SparseVectorDotProduct {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		HashMap<Integer, Integer> map1 = new HashMap<>();
		HashMap<Integer, Integer> map2 = new HashMap<>();
		int k = sc.nextInt();
		int n = sc.nextInt();
		while (k-- > 0) {
			int idx = sc.nextInt();
			int val = sc.nextInt();
			map1.put(idx, val);
		}
		while (n-- > 0) {
			int idx = sc.nextInt();
			int val = sc.nextInt();
			map2.put(idx, val);
		}
		int ret = 0;
		for (Map.Entry<Integer, Integer> e : map1.entrySet()) {
			if (map2.containsKey(e.getKey())) {
				ret += e.getValue() * map2.get(e.getKey());
			}
		}
		System.out.println(ret);
	}
}