import java.util.*;
//LeetCode 205
public class IsomorphicString {
  public boolean isIsomorphic(String s, String t) {
  	assert s.length() == t.length();
  	Map<Character, Character> map = new HashMap<>();
  	for (int j = 0; j < s.length(); j++) {
  		char sCh = s.charAt(j);
  		char tCh = t.charAt(j);
  		if (map.containsKey(sCh)) {
				char expected = map.get(sCh);
				if (tCh != expected) {
					return false;
				}  			
  		} else if (map.containsValue(tCh)) {
  			return false;
  		} else {
  			map.put(sCh, tCh);
  		}
  	}
  	return true;
  }
}