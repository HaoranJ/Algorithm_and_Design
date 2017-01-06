public class TrieNode {
    // Initialize your data structure here.
    boolean isWord;
    TrieNode[] children;
    public TrieNode() {
      isWord = false;
      children = new TrieNode[26];
    }
}
