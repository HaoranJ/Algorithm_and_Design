<<<<<<< 80c257f2999f994806b99e0950c89320b1fb05fb

=======
>>>>>>> 8/14/16
import java.util.*;

public class Longest_Substring_Without_Repeating{
	public static void main(String[] args) {
		System.out.println(Character.MAX_VALUE);
	}
	//O(n), O(1)
	public static int lengthOfLongestSubstring(String s){
		int len = s.length();
		if(len <= 1){ return len; }
		int p = 0, q = 1, ans = 1, c = 1;
		while(q < len){
			if (s.substring(p,q).contains(s.substring(q, q+1))) {
<<<<<<< 80c257f2999f994806b99e0950c89320b1fb05fb
				ans = c > ans ? c : ans; 
=======
				ans = c > ans ? c : ans;
>>>>>>> 8/14/16
				int i = p;
				while (i < q) {
					if(s.charAt(i) == s.charAt(q)){ break; }
					i++;
				}
				p = i + 1; c = q - p;
			}else{ q++; c++; }
		}
		return ans = c > ans ? c : ans;
	}
<<<<<<< 80c257f2999f994806b99e0950c89320b1fb05fb
}
=======
}
>>>>>>> 8/14/16
