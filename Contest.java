
// IPv6 addresses are represented as eight groups of four hexadecimal digits, each group representing 16 bits. The groups are separated by colons (":"). For example, the address 2001:0db8:85a3:0000:0000:8a2e:0370:7334 is a legal one. Also, we could omit some leading zeros among four hexadecimal digits and some low-case characters in the address to upper-case ones, so 2001:db8:85a3:0:0:8A2E:0370:7334 is also a valid IPv6 address(Omit leading zeros and using upper cases).
//
// However, we don't replace a consecutive group of zero value with a single empty group using two consecutive colons (::) to pursue simplicity. For example, 2001:0db8:85a3::8A2E:0370:7334 is an invalid IPv6 address.
//
// Besides, you need to keep in mind that extra leading zeros in the IPv6 is also illegal. For example, the address 02001:0db8:85a3:0000:0000:8a2e:0370:7334 is also illegal.
"172.16.254.1"
public String validIPAddress(String IP) {
  final String IPV4_Pattern = "(([0-9]|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5])\\.){3,3}([0-9]|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5])";
  // "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
  // "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
  // "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
  // "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

  Pattern ipv4Pat = Pattern.compile(IPV4_Pattern);
  if (ipv4Pat.matcher(IP).matches()) {
    return "IPv4";
  }

  Pattern ipv6Pat = Pattern.compile("([0-9a-fA-F]{1,4}:){7,7}[0-9a-fA-F]{1,4}");
  if (ipv6Pat.matcher(IP).matches()) {
    return "IPv6";
  }
  return "Neither";
}

public int totalHammingDistance(int[] nums) {
  int n = nums.length;
  int[] bits = new int[31];
  for (int num : nums) {
    for (int i = 0; i < 31; i++) {
      int mask = 1 << i;
      int bit = num & mask;
      bits[i] += (bit == 0 ? 0 : 1);
    }
  }
  int ans = 0;
  for (int b : bits) {
    ans += (n - b) * b;
  }
  return ans;
}



public static class TrieNode {
    // Initialize your data structure here.
    boolean isWord;
    int len;
    TrieNode[] children;
    public TrieNode(int l) {
      isWord = false;
      children = new TrieNode[26];
      len = l;
    }
}

public void insert(String word) {
  TrieNode cur = root;
  for (int i = 0; i < word.length(); i++) {
    char c = word.charAt(i);
    int idx = c - 'a';
    if (cur.children[idx] == null) {
      cur.children[idx] = new TrieNode(i+1);
    }
    cur = cur.children[idx];
  }
  cur.isWord = true;
}

public TrieNode getNode(String word, TrieNode node) {
  TrieNode cur = node;
  for (int i = node.len; i < word.length(); i++) {
    char c = word.charAt(i);
    int idx = c - 'a';
    if (cur.children[idx] == null || cur.children[idx].isWord) {
      return cur.children[idx];
    }
    cur = cur.children[idx];
  }
  return cur;
}

private TrieNode root = new TrieNode(0);
public List<String> findAllConcatenatedWordsInADict(String[] words) {
  for (String word : words) {
    insert(word);
  }
  List<String> ans = new ArrayList<>();
  for (String word : words) {
    if (find(word, true)) {
      ans.add(word);
    }
  }
  return ans;
}

private boolean find(String word, boolean initial) {
  TrieNode node = getNode(word, root);
  if(node == null) { return false; }
  while(node.len < word.length()) {
    if(find(word.substring(node.len), false)) {
      return true;
    }
    node = getNode(word, node);
    if (node == null) {
      return false;
    }
  }
  return !initial;
}
