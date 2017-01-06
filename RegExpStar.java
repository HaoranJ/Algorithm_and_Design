import java.util.*;

public class RegExpStar {
  /*
  re = ab*cde*xz
  abc - false;

  */
  public static void main(String[] args) {
    String re = "ab*cde*xz*jkl*qwe";
    String s1 = "abqwe";
    String s2 = "aabqwek";
    String s3 = "abababbbcdexxxxjkuxzjklqwe";
    System.out.println(isValid(re, s1));
    System.out.println(isValid(re, s2));
    System.out.println(isValid(re, s3));
  }
  public static boolean isValid(String re, String s) {
    String[] arr = re.split("\\*");
    int n  = arr.length;
    int p = 0;
    for (int i = 0; i < n; i++) {
      if (i == n-1) {
        int idx = s.lastIndexOf(arr[i], s.length()-1);
        if (idx + arr[i].length() < n) {
          return false;
        }
      } else {
        int idx = s.indexOf(arr[i], p);
        if (i == 0 && idx != 0) {
          return false;
        }
        if (idx < 0) {
          return false;
        }
        p = idx + arr[i].length();
      }
    }
    return true;
  }
}
