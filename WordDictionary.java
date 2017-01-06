import java.util.*;

public class WordDictionary {
  private static class TrieNode {
    boolean isWord;
    TrieNode[] children;
    private TrieNode() {
      isWord = false;
      children = new TrieNode[26];
      Arrays.fill(children, null);
    }
  }

  private TrieNode root = new TrieNode();
  public void addWord(String word) {
    TrieNode curNode = root;
    for (int i = 0; i < word.length(); i++) {
      int idx = word.charAt(i) - 'a';
      if(curNode.children[idx] == null) {
          curNode.children[idx] = new TrieNode();
      }
      curNode = curNode.children[idx];
    }
    curNode.isWord = true;
  }

  public boolean search(String word) {
    List<TrieNode> nodes = new ArrayList<>();
    nodes.add(root);
    for (int i = 0; i < word.length(); i++) {
      if(nodes.isEmpty()) { return false; }
      char ch = word.charAt(i);
      List<TrieNode> temp = new ArrayList<>();
      int idx = ch - 'a';
      if (ch == '.') {
        for (TrieNode node : nodes) {
          for (TrieNode child : node.children) {
            if(child != null) { temp.add(child); }
          }
        }
      } else {
        for (TrieNode node : nodes) {
          if (node.children[idx] != null) {
            temp.add(node.children[idx]);
          }
        }
      }
      nodes = temp;
    }
    for (TrieNode node : nodes) {
      if(node.isWord) { return true; }
    }
    return false;
  }
}

// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");
