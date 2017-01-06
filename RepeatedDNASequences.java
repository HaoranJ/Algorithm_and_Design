import java.util.*;
//LeetCode 187
public class RepeatedDNASequences {
  public List<String> findRepeatedDnaSequences(String s) {
    Map<String, Integer> map = new HashMap<>();
    List<String> ans = new ArrayList<>();
    if (s == null || s.length() < 10) {
    	return ans;
    }
    int N = s.length();
    int L = 10;
    for (int i = 0; i <= N-10; i++) {
    	int j = i + L;
    	String t = s.substring(i, j);
    	Integer val = map.putIfAbsent(t, 1);
    	if (val != null) {
    		map.replace(t, val + 1);
    	}
    }
    for (Map.Entry<String, Integer> e : map.entrySet()) {
    	if (e.getValue() > 1) {
    		ans.add(e.getKey());
    	}
    }
    return ans;
  }

  /*The key point is that it is not doing hash, it is doing the exact coding of a 10-letter sequence into a 4-bytes number, which is simply not possible for any generic string, but is possible for strings in this problem because they can have only 4 differfent characters.

In more detail:

If two objects have same hash it means that they may or may not be equal (though two equal objects are required to have same hash). So hashing is not enough here (like calling just "AACCCCCGGG".hashCode() and storing it in the map), because there can be another (different) string with same hash and the program will output wrong result.

We also cannot store the 10-letter substrings themselves because they consume too much memory and the program will exceed memory limit.

So, instead of hashing or storing strings themselves the solution converts 10 letter string into 4-bytes integer (which is much smaller than string in terms of consumed memory). This would not be possible if the string could contain all 26 letters of English alphabet for example. But it is possible for our case, because there can be only 'A', 'C', 'G' and 'T' letters.

So we have only 4 possible letters, and we can use as little bits as possible to store each character of our 10-letter string. We really need only 2 bits (bits, not bytes) for this. Specifically the solution uses the following coding:

0 = 00 (bits in binary number system) = 'A'

1 = 01 (bits in binary number system) = 'C'

2 = 10 (bits in binary number system) = 'G'

3 = 11 (bits in binary number system) = 'T'

Note that since there 10 letters and each letter requires only 2 bits, we will need only 10 * 2= 20 bits to code the string (which is less then size of integer in java (as well as in all othere popular languages), which is 4 bytes = 32 bits).

For example, this is how "AACCTCCGGT" string will be coded:

A A C C T C C G G T

00 00 01 01 11 01 01 10 10 11 = 00000101110101101011 (binary) = 23915 (decimal)
  */
	// a Character has 2 bytes, an Integer has 4 bytes.
	//time = O(n), worst-case space is 8 bytes * (N-10)
  public List<String> findRepeatedDnaSequences_LessMemory(String s) {
    Map<Integer, Integer> map = new HashMap<>();
    List<String> ans = new ArrayList<>();
    Map<Character, Integer> dict = new HashMap<Character, Integer>(){{
    	put('A', 0);
    	put('C', 1);
    	put('G', 2);
    	put('T', 3);
    }};
    if (s == null || s.length() < 10) {
    	return ans;
    }
    int N = s.length();
    int L = 10;
    int key = 0;
    int gauge = (1 << 18) - 1;
    for (int i = 0; i < N-9; i++) {
    	if (i == 0) {
    		for (int j = i; j < i+L; j++) {
    			int val = dict.get(s.charAt(j));
    			key = key << 2;
    			key |= val;
    		}   		
    	} else {
    		key &= gauge;
    		key = key << 2;
    		int cur = dict.get(s.charAt(i+9));
    		key |= cur;
    	}
    	Integer value = map.putIfAbsent(key, 1);
  		if (value != null) {
  			map.replace(key, value+1);
  			if (value == 1) {
	  			ans.add(s.substring(i, i+L));
	  		}
  		}
    }
    return ans;
  }
}