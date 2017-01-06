import java.util.*;
import java.io.*;

//origin https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=1239
public class PowerString {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			String s = sc.next();
			if (".".equals(s)) { return; }
			System.out.println(getPower(s));
		}
	}

	//time = O(mn), n is the length of s, m is the number of l's that can divide len
	private static int getPower(String s) {
		int len = s.length();
		int l = 1;
		for (l = 1; l < len+1; l++) {
			//skep the l's that don't divide len.
			if (len % l != 0) { continue; }
			int k = len/l;
			String base = s.substring(0,l);
			boolean broken = false;
			for (int j = 1;	j < k ;j++ ) {
				String compare = s.substring(j*l, (j+1)*l);
				if (!base.equals(compare)) {
					broken = true;
					break;
				}
			}
			if (broken) { continue; } 
			else { return k; }
		}
		return 1;
	}
	//Or use KMP algorithm to search a word W in main text S, time = O(W.length() + S.length())
}