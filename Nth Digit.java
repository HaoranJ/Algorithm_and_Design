//LeetCode 400 - Nth dight
// 1 ~ 9: 9 * 1
// 10 ~ 99: 90 * 2
// 100 ~ 999: 900 * 3
// 1000 ~ 9999: 9000 * 4
// ...

public int findNthDigit(int n) {
  if(n < 10) { return n; }

  long lvlSize = 9;
  int numSize = 1, base = 1;
  while(n > 0) {
    //key!!! if lvlSize and numSize both are int, lvlSize * numSize -> int -> overflow -> negative num -> long
    //to avoid overflow, we need at least one of them to be long.
    long lvlSum = lvlSize * numSize;
    if(lvlSum == n) {
      return 9;
    } else if(lvlSum < n) {
      n -= lvlSum;
    } else {
      int numIdx = n / numSize;
      int digitIdx = n % numSize;
      int target = digitIdx == 0 ? base + numIdx - 1 : base + numIdx;
      if(digitIdx == 0) {
        return target % 10;
      } else {
        return Integer.parseInt(String.valueOf(target).substring(digitIdx-1, digitIdx));
      }

    }
    lvlSize *= 10;
    numSize++;
    base *= 10;
  }
  return 1;
}
