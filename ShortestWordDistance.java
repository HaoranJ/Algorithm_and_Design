import java.util.*;

//LeetCode 243, 244, 245 Shortes Word Distance

public class ShortestWordDistance {
	//LeetCode 244, time = O(n), space = O(n)
	private final Map<String, List<Integer>> map;
	public ShortestWordDistance(String[] words) {
		map = new HashMap<>();
		for (int i = 0; i < words.length; i++) {
			String s = words[i];
			if (map.containsKey(s)) {
				map.get(s).add(i);
			} else {
				List<Integer> list = new ArrayList<>();
				list.add(i);
				map.put(s, list);
			}
		}
	}

	public int shortest(String word1, String word2) {
		assert word1 != null && word2 != null;
		assert !word1.equals(word2);
		List<Integer> list1 = map.get(word1);
		List<Integer> list2 = map.get(word2);
		int ans = Integer.MAX_VALUE;
		for (int i = 0, j = 0; i < list1.size() && j < list2.size(); ) {
			int idx1 = list1.get(i);
			int idx2 = list2.get(j);
			if (idx1 < idx2) {
				ans = Math.min(ans, idx2 - idx1);
				i++;
			} else {
				ans = Math.min(ans, idx1 - idx2);
				j++;
			}
		}
		return ans;
	}

	//LeetCode 243, time = O(n), space = constant
  public int shortestDistance(String[] words, String word1, String word2) {
		assert word1 != null && word2 != null;
    assert !word1.equals(word2);
    int p1 = -1;
    int p2 = -1;
    int minDis = Integer.MAX_VALUE;
    for (int i = 0; i < words.length; i++) {
    	String s = words[i];
    	if (s.equals(word1)) {
    		p1 = i;
    	} else if (s.equals(word2)) {
    		p2 = i;
    	}
    	if (p1 != -1 && p2 != -1) {
    		minDis = Math.min(minDis, Math.abs(p1 - p2));
    	}
    }
    return minDis;
  }

	//LeetCode 245, word1 may equal word2,
	//time = O(n), space = constant
	public int shortestWordDistance(String[] words, String word1, String word2) {
      assert word1 != null && word2 != null;
      int p1 = -1;
      int p2 = -1;
      int ans = Integer.MAX_VALUE;

      boolean isP1Turn = true;
      for(int i = 0; i < words.length; i++) {
          String s = words[i];
          if(word1.equals(word2)) {
              if(s.equals(word1)) {
                  if(isP1Turn) {
                      p1 = i;
                      isP1Turn = false;
                  } else {
                      p2 = i;
                      isP1Turn = true;
                  }
              }
          } else {
              if(s.equals(word1)) {
                  p1 = i;
              } else if (s.equals(word2)) {
                  p2 = i;
              }
          }

          if(p1 > -1 && p2 > -1) {
              ans = Math.min(ans, Math.abs(p1-p2));
          }
      }
      return ans;
  }
}
