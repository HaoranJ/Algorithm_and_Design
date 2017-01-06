//LeetCode 68. Text Justification
// Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified.
//
// You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly L characters.
//
// Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.
//
// For the last line of text, it should be left justified and no extra space is inserted between words.
//
// For example,
// words: ["This", "is", "an", "example", "of", "text", "justification."]
// L: 16.
//
// Return the formatted lines as:
// [
//    "This    is    an",
//    "example  of text",
//    "justification.  "
// ]
// Note: Each word is guaranteed not to exceed L in length.
//
// click to show corner cases.
//
// Corner Cases:
// A line other than the last line might contain only one word. What should you do in this case?
// In this case, that line should be left-justified.

//time = O(n)
public List<String> fullJustify(String[] words, int maxWidth) {
  List<String> ans = new ArrayList<>();
  int i = 0;
  while(i < words.length) {
    int ptr = i;
    int count = -1;
    while(ptr < words.length && count <= maxWidth) {
      if(count + words[ptr].length() + 1 > maxWidth) {
        break;
      }
      count += words[ptr++].length() + 1;
    }
    ans.add(getLine(words, i, ptr, maxWidth));
    i = ptr;
  }
  return ans;
}

private String getLine(String[] words, int startIdx, int endIdx, int maxWidth) {
  StringBuilder sb = new StringBuilder();
  int spaces = maxWidth + 1;
  for(int i = startIdx; i < endIdx; i++) {
    spaces -= words[i].length() + 1;
  }
  sb.append(words[startIdx]);
  //A line other than the last line might contain only one word || the last line.
  if(startIdx + 1 == endIdx || endIdx == words.length) {
    for(int i = startIdx + 1; i < endIdx; i++) {
      sb.append(getSpaces(1));
      sb.append(words[i]);
    }
    sb.append(getSpaces(spaces));
  } else {
    //regular line
    int slotSps = spaces / (endIdx - startIdx - 1);
    int remain = spaces % (endIdx - startIdx - 1);
    for(int i = startIdx + 1; i < endIdx; i++) {
      sb.append(getSpaces(1 + slotSps + (remain-- > 0 ? 1 : 0)));
      sb.append(words[i]);
    }
  }
  return sb.toString();
}

private String getSpaces(int x) {
  StringBuilder sb = new StringBuilder();
  for(int i = 0; i < x; i++) {
    sb.append(" ");
  }
  return sb.toString();
}
