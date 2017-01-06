/* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */

public class Solution extends Reader4 {
  /**
   * @param buf Destination buffer
   * @param n   Maximum number of characters to read
   * @return    The number of characters read
   */
  public int read(char[] buf, int n) {
    char[] r4Buf = new char[4];
    int count = 0;
    boolean eof = false;
    while (!eof && count < n) {
      int r4Num = read4(r4Buf);
      eof = r4Num < 4;
      int insert = Math.min(r4Num, n - count);
      System.arraycopy(r4Buf, 0, buf, count, insert);
      count += insert;
    }
    return count;
  }

  /** Leetcode - 158
  a file: abcdef,
  read(buf, 2) - buf:ab, and after call read4(), "cd" has been read.
  read(buf, 3) - buf:cde, should first comsume "cd" from last call, and read file to get "e".
  read(buf, 4) - buf:f
  */
  private boolean eof = false;
  private Deque<Character> lastBuf = new ArrayDeque<>();
  public int read(char[] buf, int n) {
    int count = 0, r4Num = 0, insertLen = 0;
    char[] r4Buf = new char[4];
    while (!lastBuf.isEmpty() && count < n) {
      char ch = lastBuf.poll();
      buf[count++] = ch;
    }
    while (count < n && !eof) {
      r4Num = read4(r4Buf);
      if(r4Num < 4) { eof = true; }
      insertLen = Math.min(r4Num, n - count);
      System.arraycopy(r4Buf, 0, buf, count, insertLen);
      count += insertLen;
    }
    if (insertLen < r4Num) {
      for (int i = insertLen; i < r4Num; i++) {
        lastBuf.add(r4Buf[i]);
      }
    }
    return count;
  }

  //improved version.
  char[] r4Buf = new char[4];
  int r4Ptr = 0;
  int r4Num = 0;
  public int read(char[] buf, int n) {
    int count = 0;
    while (count < n && r4Ptr < r4Num) {
      buf[count++] = r4Buf[r4Ptr++];
    }
    while (count < n) {
      r4Num = read4(r4Buf);
      if(r4Num == 0) { break; }
      r4Ptr = 0;
      while (count < n && r4Ptr < r4Num) {
        buf[count++] = r4Buf[r4Ptr++];
      }
    }
    return count;
  }
}
