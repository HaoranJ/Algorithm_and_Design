import java.util.*;

public class CountPrimes {
  /*
  LeetCode 204 - Count Primes.
  Sieve of Eratosthenes
  time = O(nloglogn), space = O(n)
  */
  public int countPrimes(int n) {
    boolean[] isPrime = new boolean[n+1];
    Arrays.fill(isPrime, true);
    //use i * i <= n as the loop end condition instead of i <= sqrt(n)
    //to avoid the expensive function call of sqrt.
    for (int i = 2; i * i <= n; i++) {
      if (!isPrime[i]) {
        continue;
      }
      for (int j = i * i; j <= n; j += i) {
        isPrime[j] = false;
      }
    }
    int ans = 0;
    for (int p = 2; p < n; p++) {
      if(isPrime[p]) { ans++; }
    }
    return ans;
  }
}
