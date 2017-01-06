import java.util.*;

public class Trie {
    public static class TrieNode {
        // Initialize your data structure here.
        boolean isWord;
        TrieNode[] children;
        public TrieNode() {
          isWord = false;
          children = new TrieNode[26];
        }
    }
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
      TrieNode cur = root;
      for (int i = 0; i < word.length(); i++) {
        char c = word.charAt(i);
        int idx = c - 'a';
        if (cur.children[idx] == null) {
          cur.children[idx] = new TrieNode();
        }
        cur = cur.children[idx];
      }
      cur.isWord = true;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
      TrieNode cur = root;
      for (int i = 0; i < word.length(); i++) {
        char c = word.charAt(i);
        int idx = c - 'a';
        if (cur.children[idx] == null) {
          return false;
        }
        cur = cur.children[idx];
      }
      return cur.isWord;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
      TrieNode cur = root;
      for (int i = 0; i < prefix.length(); i++) {
        char c = prefix.charAt(i);
        int idx = c - 'a';
        if (cur.children[idx] == null) {
          return false;
        }
        cur = cur.children[idx];
      }
      return true;
    }

    //Return the last TrieNode when searching word,
    //if not return null
    public TrieNode getNode(String word) {
      TrieNode cur = root;
      for (int i = 0; i < word.length(); i++) {
        char c = word.charAt(i);
        int idx = c - 'a';
        if (cur.children[idx] == null) {
          return null;
        }
        cur = cur.children[idx];
      }
      return cur;
    }
}

// Your Trie object will be instantiated and called as such:
// Trie trie = new Trie();
// trie.insert("somestring");
// trie.search("key");
