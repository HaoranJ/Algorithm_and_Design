//Leetcode 249
public class GroupShiftedStrings {
  public List<List<String>> groupStrings(String[] strings) {
    Map<String, List<String>> map = new HashMap<>();
    for (String str : strings) {
      String key = getOriginal(str);
      if (map.containsKey(key)) {
        map.get(key).add(str);
      } else {
        map.put(key, new ArrayList<String>(){{ add(str); }});
      }
    }
    return new ArrayList<List<String>>(map.values());
  }

  private static String getOriginal(String str) {
    StringBuilder sb = new StringBuilder();
    int offset = 0;
    for (int i = 0; i < str.length(); i++) {
      char c = str.charAt(i);
      if (i == 0) {
        sb.append('a');
        offset = c - 'a';
      } else {
        c -= offset;
        if (c < 'a') {
          c += 26;
        }
        sb.append(c);
      }
    }
    return sb.toString();
  }
  public static void main(String[] args) {
    System.out.println(getOriginal("ba"));
  }
}
