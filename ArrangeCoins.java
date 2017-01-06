//LeetCode - 441
// Approach: Binary Search
//
// The problem is basically asking the maximum length of consecutive number that has the running sum lesser or equal to `n`. In other word, find `x` that satisfy the following condition:
//
// `1 + 2 + 3 + 4 + 5 + 6 + 7 + ... + x <= n`
// `sum_{i=1}^x i <= n`
// Running sum can be simplified,
//
// `(x * ( x + 1)) / 2 <= n`
// Binary search is used in this case to slowly narrow down the `x` that will satisfy the equation. Note that 0.5 * mid * mid + 0.5 * mid does not have overflow issue as the intermediate result is implicitly autoboxed to double data type.
public int arrangeCoins(int n) {
  int lo = 1, hi = n;
  int mid;
  while (lo  <= hi) {
    mid = lo + (hi - lo) / 2;
    if (0.5 * mid * (mid + 1) <= n) {
      lo = mid + 1;
    } else {
      hi = mid - 1;
    }
  }
  return lo - 1;
}

//Math (x * ( x + 1)) / 2 <= n, n < ((x+1) * (x+2)) / 2
// ===> a = (sqrt(8n + 1) - 1) / 2, a-1 < x <= a
public int arrangeCoins(int n) {
  return (int)(0.5 * (-1 + Math.sqrt(8.0d * n + 1)));
}
