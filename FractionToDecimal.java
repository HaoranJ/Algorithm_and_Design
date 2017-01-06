public class FractionToDecimal {
  //LeetCode 166 - Fraction to recurring decimal
  public String fractionToDecimal(int numerator, int denominator) {
    assert denominator != 0;
    if(numerator == 0) { return "0"; }
    StringBuilder sb = new StringBuilder();
    //check sign
    if(numerator < 0 ^ denominator < 0) { sb.append("-"); }
    //First, convert int to long to avoid overflow, like -2^31
    //Then abs
    long num = Math.abs((long)numerator);
    long den = Math.abs((long)denominator);
    sb.append(num / den);
    long rem = num % den;
    if(rem == 0) { return sb.toString(); }
    else { sb.append("."); }

    Map<Long, Integer> map = new HashMap<>();
    while (rem > 0) {
      if (map.containsKey(rem)) {
        int offset = map.get(rem);
        sb.insert(offset, "(");
        sb.append(")");
        break;
      }
      //record the remainder and specific position
      map.put(rem, sb.length());
      rem *= 10;
      long quo = rem / den;
      sb.append(quo);
      rem %= den;
    }
    return sb.toString();
  }
}
