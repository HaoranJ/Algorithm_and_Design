import java.util.*;

/*
Dataminr
You have a Character matrix n * m, given a set of words W from dictionary, find all valid words
connecting adjacent letters vertically or horizontally.
e.g.
  a, b, c, k
  g, i, n, a
  j, l, o, n
In the matrix, we have valid words "bag", "bin", "nano"...
First, we build a trie (prefix tree) from the words set W,
and DFS from every node in the matrix to get all possible sequences,
and pick up the valid sequences which are words in W.
For simplicity, we assume all letter are lowercase.
*/
public class SearchWordsInMatrix {
  public static void main(String[] args) {
    SearchWordsInMatrix swim = new SearchWordsInMatrix();
    String[][] mat = {
      {"a", "b", "c", "k"},
      {"g", "i", "n", "a"},
      {"j", "l", "o", "n"}
    };
    Set<String> words = new HashSet<>();
    words.add("bag");
    words.add("bin");
    words.add("nano");
    words.add("felix");
    words.add("emma");
    System.out.println(swim.searchWords(mat, words));
  }

  static int[][] moves = {{0,1}, {1,0}, {0, -1}, {-1, 0}};
  List<String> ans;
  Trie trie;
  boolean[][] path;
  int m;
  int n;
  public List<String> searchWords(String[][] mat, Set<String> words) {
    trie = new Trie();
    ans = new ArrayList<>();
    Iterator<String> itr = words.iterator();
    while (itr.hasNext()) {
      trie.insert(itr.next());
    }
    m = mat.length;
    n = mat[0].length;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        String s = mat[i][j];
        path = new boolean[m][n];
        dfs(s, i, j, mat);
      }
    }
    return ans;
  }

  private void dfs(String s, int i, int j, String[][] mat) {
    path[i][j] = true;
    TrieNode node = trie.getNode(s);
    if (node != null) {
      if (node.isWord) {
        ans.add(s);
      }
      for (int[] mv : moves) {
        int x = i + mv[0];
        int y = j + mv[1];
        if ((0 <= x && x < m && 0 <= y && y < n) && !path[x][y])  {
          String t = mat[x][y];
          dfs(s+t, x, y, mat);
        }
      }
    }
    path[i][j] = false;
  }
}
