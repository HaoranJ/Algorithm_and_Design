public class Coordinate {
  int row;
  int col;
  public Coordinate(int a, int b) {
    row = a;
    col = b;
  }

  @Override
  public boolean equals(Object ob) {
    if (!(ob instanceof Coordinate)) {
      return false;
    }
    Coordinate p = (Coordinate)ob;
    return this.row == p.row && this.col == p.col;
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + row;
    result = 31 * result + col;
    return result;
  }
}
