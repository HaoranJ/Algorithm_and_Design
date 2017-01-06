public class PowerOfX {
  //Determine power of two
  public boolean isPowerOfTwo(int n) {
    return Integer.bitCount(n) == 1 && n > 0;
  }

  //减一相与
  public boolean isPowerOfTwo(int n) {
    return (n & (n-1)) == 0 && n > 0
  }

  //Determine power of three
  public boolean isPowerOfThree(int n) {
    if(n == 0) { return false; }
    return (n == 1) || (n % 3 == 0 && isPowerOfThree(n / 3));
  }

  public boolean isPowerOfThree(int n) {
    int maxPowerOfThree = (int)Math.pow(3, (int)(Math.log(0x7FFFFFFF)/Math.log(3)));
    return n > 0 && maxPowerOfThree % 3 == 0;
  }

  //Determine power of four
  //0x or 0X for hexadecimal, 0 for octal, and nothing for decimal.
  //at least n should be power of two, then check if the one-bit is on the odd bit position by & with 0x55555555
  public boolean isPowerOfFour(int n) {
    return (n > 0) && (n & (n-1)) == 0 && (n & 0x55555555) == n;
  }

}
