import java.util.*;

public class RectangleArea {
  public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
     
  }

  private int computeArea(int A, int B, int C, int D) {
    if (A <= C && B <= D) {
      return (C-A) * (D - B);
    } else {
      return 0;
    }
  }
}
