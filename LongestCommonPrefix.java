import java.util.*;

//LeetCode - 14
public class LongestCommonPrefix {
	//time = O(nk), n is the number of words, k is the shortest string's length
    public String longestCommonPrefix(String[] strs) {
        StringBuilder sb = new StringBuilder();
        if (strs.length == 0) {
            return sb.toString();
        }
        int len = Integer.MAX_VALUE;
        int shortIdx = -1;
        for(int j = 0; j < strs.length; j++) {
            String s = strs[j];
            if (s.length() < len) {
                len = s.length();
                shortIdx = j;
            }
        }
        String shortest = strs[shortIdx];
        for(int j = 0; j < shortest.length(); j++) {
            char ch = shortest.charAt(j);
            boolean broken = false;
            for(int p = 0; p < strs.length; p++) {
                if (p != shortIdx) {
                    if (strs[p].charAt(j) != ch) {
                        broken = true;
                        break;
                    }
                }
            }
            if (broken) {
                break;
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    //time = O(n+k)
    public String longestCommonPrefix_Concise(String[] strs) {
    	if (strs == null || strs.length == 0) { return ""; }
    	StringBuilder sb = new StringBuilder(strs[0]);
    	for (int j = 1; j < strs.length; j++) {
    		while (strs[j].indexOf(sb.toString()) != 0) {
    			sb.deleteCharAt(sb.length()-1);
    			if (sb.length() == 0) {
    				return sb.toString();
    			}
    		}
    	}
    	return sb.toString();
    }
}