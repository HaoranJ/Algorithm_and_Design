import java.util.*;

public class WordSearchII {
  /*
  LeetCode 212 Word Search II
  First build up a prefix tree (trie) based on the dictionary words.
  And do Depth First Search from every node of the board to find out
  all possible sequences. In the process of DFS, we check with the trie about
  the current sequence, if there is no prefix or word for current sequence, we stop
  DFS of this direction.
  time = exponential, but the search (getNode) operation in trie is O(depth of the tree).
  space = (worst case) exponential, (actually has more overlapped prefix)
  */
  public static void main(String[] args) {
    char[][] board = {
      {'o','a','a','n'},
      {'e','t','a','e'},
      {'i','h','k','r'},
      {'i','f','l','v'}
    };
    String[] words = {"oath","pea","eat","rain"};
    WordSearchII ws = new WordSearchII();
    List<String> ret = ws.findWords(board, words);
    for (String s : ret) {
      System.out.print(s + ", ");
    }
  }
  private static class TrieNode {
    boolean isWord;
    TrieNode[] next;

    TrieNode() {
      isWord = false;
      next = new TrieNode[26];
    }
  }
  TrieNode root;
  char[][] board;
  int m, n;
  List<String> ans;
  public List<String> findWords(char[][] board, String[] words) {
    ans = new ArrayList<>();
    root = new TrieNode();
    for (String s : words) {
      TrieNode cur = root;
      for (int i = 0; i < s.length(); i++) {
        int idx = s.charAt(i) - 'a';
        if (cur.next[idx] == null) {
          cur.next[idx] = new TrieNode();
        }
        cur = cur.next[idx];
      }
      cur.isWord = true;
    }
    this.board = board;
    m = board.length; n = board[0].length;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        String w = "";
        dfs(w, i, j);
      }
    }
    return ans;
  }

  private TrieNode getNode(String w) {
    TrieNode cur = root;
    for (int i = 0; i < w.length(); i++) {
      int idx = w.charAt(i) - 'a';
      if (cur.next[idx] == null) { return null; }
      else { cur = cur.next[idx]; }
    }
    return cur;
  }

  private void dfs(String w, int i, int j) {
    if (i < 0 || i >= m || j < 0 || j >= n) { return; }
    boolean visit = (board[i][j] >> 8) == 1;
    if (visit) { return; }
    w = w + Character.toString(board[i][j]);
    board[i][j] ^= (1 << 8);
    TrieNode node = getNode(w);
    if (node != null) {
      if(node.isWord && !ans.contains(w)) { ans.add(w); }
      dfs(w, i+1, j);
      dfs(w, i-1, j);
      dfs(w, i, j+1);
      dfs(w, i, j-1);
    }
    board[i][j] ^= (1 << 8);
  }

  /*
  LeetCode 79, Word Search
  Use Depth First Search to find out all possible sequences, and check the predix
  of the target word in each step. Since ASCII take up only 8 bits in char, we can
  use the bit left in char to store the visted states to save space.
  time = exponential, space = in place
  */
  char[][] board;
  int m, n;
  String word;
  public boolean exist(char[][] board, String word) {
    this.board = board;
    m = board.length; n = board[0].length;
    this.word = word;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        boolean e = dfs(i, j, 0);
        if(e) { return true; }
      }
    }
    return false;
  }
  private boolean dfs(int i, int j, int idx) {
    if(idx == word.length()) { return true; }
    if (i < 0 || i >= m || j < 0 || j >= n) { return false; }
    boolean visit = (board[i][j] >> 8) == 1;
    if (visit) { return false; }
    char ch = board[i][j];
    //to note the visited
    board[i][j] ^= (1 << 8);
    boolean ret = false;
    if(ch == word.charAt(idx)) {
        ret = dfs(i+1, j, idx+1) || dfs(i-1, j, idx+1) || dfs(i, j+1, idx+1) || dfs(i, j-1, idx+1);
    }
    //clear the visited spot
    board[i][j] ^= (1 << 8);
    return ret;
  }
}
