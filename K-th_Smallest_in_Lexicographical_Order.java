/*LeetCode - 440
*/
// Constructs resulting number digit by digit
// starting with the most significant.
public int findKthNumber(int n, int k) {
  int i = 0;
  String prefix = "";
  while (k > 0) {
    for (i = 0; i <= 9; i++) {
      int count = countForPrefix(n, prefix + i);
      if (count < k) {
        k -= count;
      } else {
        break;
      }
    }
    prefix += i;
    k--;
  }
  return Integer.parseInt(prefix);
}

//get the count of nums that start with prefix.
/*prefix = "1"
1,
10,11,...,19
100,101,....109
110,111,....119
.
.
190,191,...199
*/
private int countForPrefix(int n, String prefix) {
  long x = Long.parseLong(prefix);
  if (x == 0 || x > n) {
    return 0;
  }
  int count = 0;
  long ceiling = x + 1;
  do {
    count += Math.min(n+1, ceiling) - x;
    x *= 10;
    ceiling *= 10;
  } while(x <= n);
  return count;
}
