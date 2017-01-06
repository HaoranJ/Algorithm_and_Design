import java.util.*;

public class Codec {
  /*
  LeetCode 271
  */
  // Encodes a list of strings to a single string.
  public String encode(List<String> strs) {
    StringBuilder sb = new StringBuilder();
    for(String s : strs) {
      sb.append(s.length()).append("=").append(s);
    }
    return sb.toString();
  }

  // Decodes a single string to a list of strings.
  public List<String> decode(String s) {
    List<String> ret = new ArrayList<>();
    int i = 0;
    while(i < s.length()) {
      //find first occurence of '=' starting from index i
      int del = s.indexOf('=', i);
      int len = Integer.parseInt(s.substring(i, del));
      ret.add(s.substring(del+1, del+len+1));
      i = del+len+1;
    }
    return ret;
  }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(strs));
