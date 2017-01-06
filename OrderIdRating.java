import java.util.*;

public class OrderIdRating {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		TreeMap<Integer, String> tmap = new TreeMap<>(new RC());
		while (sc.hasNext()) {
			String id = sc.next();
			int rating = sc.nextInt();
			tmap.put(rating, id);
		}
		
		Iterator<Map.Entry<Integer, String>> it = tmap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, String> e = it.next();
			System.out.println(e.getValue() + " " + e.getKey());
		}		
	}

	private static class RC implements Comparator {
		@Override
		public int compare(Object o1, Object o2) {
			Integer r1 = (Integer)o1;
			Integer r2 = (Integer)o2;
			//never return 0 to avoid merging entries with same rating.
			if (r1 >= r2) {
				return -1;
			} else {
				return 1;
			}
		}
	}
}

