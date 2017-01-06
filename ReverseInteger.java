import java.util.*;

public class ReverseInteger {
  public int reverse(int x) {
    int ret = 0;
    while (x != 0) {
      int rm = x % 10;
      int temp = ret*10 + rm;
      //check overflow
      if ((temp - rm)/10 != ret) {
        return 0;
      }
      ret = temp;
      x = x / 10;
    }
    return ret;
  }
}