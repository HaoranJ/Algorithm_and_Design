import java.util.*;

public class MultiplyStrings {
  /*
  LeetCode - 43
  time = O(len1 * len2), space = O(len1+len2)
  */
  public String multiply(String s1, String s2) {
    assert s1 != null;
    assert s2 != null;
    int len1 = s1.length(), len2 = s2.length();
    int[] digits = new int[len1 + len2];
    //compute the products of every digit pair
    for (int i = 0; i < len1; i++) {
      for (int j = 0; j < len2; j++) {
        int d1 = s1.charAt(i) - '0';
        int d2 = s2.charAt(j) - '0';
        digits[i+j+1] += d1 * d2;
      }
    }
    StringBuilder sb = new StringBuilder();
    int carry = 0;
    //carry every digit over
    for (int p = len1+len2-1; p >= 0 ; p--) {
      int num = carry + digits[p];
      int stay = num % 10;
      carry = num / 10;
      digits[p] = stay;
    }
    //trim the leading zeros
    int begin = 0;
    while (begin < len1+len2-1 && digits[begin] == 0) {
      begin++;
    }
    for (int i = begin; i < len1+len2; i++) {
      sb.append(digits[i]);
    }
    return sb.toString();
  }
}
