import java.util.*;

public class ValidWordAbbr {
  /*
  LeetCode 288, Unique Word Abbreviation
  HashMap, HashSet.
  */
  private Map<String, Set<String>> map;
  public ValidWordAbbr(String[] dictionary) {
    assert dictionary != null;
    map = new HashMap<>();
    for(String s : dictionary){
      String ab = abbr(s);
      if(map.containsKey(ab)) {
        map.get(ab).add(s);
      } else {
        Set<String> set = new HashSet<>();
        set.add(s);
        map.put(ab ,set);
      }
    }
  }

  private String abbr(String s){
    assert s != null;
    int len = s.length();
    if(len <= 2) return s;
    StringBuilder sb = new StringBuilder();
    sb.append(s.charAt(0));
    int n = len - 2;
    sb.append(n);
    sb.append(s.charAt(len-1));
    return sb.toString();
  }

  public boolean isUnique(String word) {
    String ab = abbr(word);
    if(!map.containsKey(ab)) return true;
    else {
      if(map.get(ab).size() > 1) return false;
      else return map.get(ab).contains(word);
    }
  }
}
