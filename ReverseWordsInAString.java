import java.util.*;

public class ReverseWordsInAString {
	//time = O(n), space = O(1)
	public void reverseWords(char[] s) {
		rotate(s, 0, s.length-1);
		int i = 0;
		int j = 0;
		while (i < s.length) {
			while (i < s.length && s[i] == ' ') {
				i++;
			}
			j = i;
			while (j < s.length && s[j] != ' ') {
				j++;
			}
			rotate(s, i, j-1);
			i = j + 1;
		}
	}
	// public void reverseWords(char[] s) {
	// 	rotate(s, 0, s.length-1);
	// 	int i = 0;
	// 	int j = 0;
	// 	while (j <= s.length) {
	// 		if (j == s.length || s[j] == ' ') {
	// 			rotate(s, i, j-1);
	// 			i = j+1;
	// 		}
	// 		j++;
	// 	}
	// }

	private void rotate(char[] s, int lo, int hi) {
		if (lo > hi) { return; }
		for (int j = lo; j <= (lo+hi)/2 ;j++ ) {
			char temp = s[j];
			s[j] = s[hi-(j-lo)];
			s[hi-(j-lo)] = temp;
		}
	}

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		ReverseWordsInAString rw = new ReverseWordsInAString();
		char[] s;
		while (sc.hasNextLine()) {
			s = sc.nextLine().toCharArray();
			rw.reverseWords(s);
			System.out.println(s);
		}
	}
}
