import java.util.*;

public class BestTimeToBuyAndSellStock {

  /** LeetCode 121 **/
  //We can convert stock price problem ==> maximum-subarray problem
  //time = O(n), space = O(n)
  public int maxProfit(int[] prices) {
    if (prices.length < 2) {
      return 0;
    }
    int n = prices.length;
    //trans[i] - the max profit if day i is involved in transaction.
    //rest[i] - the max profit if day i is NOT in transaction.
    //i.e. 7,  1,  4  ,  3  ,  6
    //       sell       buy           ==> 1,4,3 are in transaction, 7, 6 are not in transaction.
    int[] trans = new int[n], rest = new int[n];
    for(int i = 1; i < n; i++) {
      rest[i] = Math.max(rest[i-1], trans[i-1]);
      int change = prices[i] - prices[i-1];
      trans[i] = Math.max(0, trans[i-1]) + change;
    }
    return Math.max(trans[n-1], rest[n-1]);
  }

  //Optimize to constant space.
  //time = O(n), space = O(1)
  public int maxProfit(int[] prices) {
    if (prices.length < 2) {
      return 0;
    }
    int n = prices.length;
    int transHere = 0, restHere = 0;
    for(int i = 1; i < n; i++) {
      restHere = Math.max(restHere, transHere);
      int change = prices[i] - prices[i-1];
      transHere = Math.max(0, transHere) + change;
    }
    return Math.max(transHere, restHere);
  }

  //Optimize to constant space.
  //time = O(n), space = O(1)
  public int maxProfit(int[] prices) {
    int maxEndHere = 0, maxSofar = 0;
    for(int i = 1; i < prices.length; i++) {
      int change = prices[i] - prices[i-1];
      maxEndHere = change + maxEndHere > 0 ? change + maxEndHere : 0;
      maxSofar = Math.max(maxSofar, maxEndHere);
    }
    return maxSofar;
  }

  /**LeetCode - 122**/
  //do all the transactions if the change is positive.
  public int maxProfit(int[] prices) {
    int profit = 0;
    for(int i = 1; i < prices.length; i++) {
      int change = prices[i] - prices[i-1];
      profit += change > 0 ? change : 0;
    }
    return profit;
  }

  /** LeetCode - 188 **/
  //Say you have an array for which the ith element is the price of a given stock on day i.
  //Design an algorithm to find the maximum profit. You may complete at most k transactions.
  //similar to 0-1 knapsack problem.
  //dp[tr][i] = max(day i NOT in transaction, day i in transaction)
  //          = max(dp[tr][i-1], max{dp[tr-1][ii] + prices[i] - prices[ii]}), 0 <= ii < i
  //          = max(dp[tr][i-1], prices[i] + max{dp[tr-1][ii] - prices[ii]}), 0 <= ii < i
  //time = O(kn), space = O(kn)
  public int maxProfit(int k, int[] prices) {
    int n = prices.length;
    if(n <= 1) { return 0; }
    //the allowed transaction number is big enough for us to get all positive changes.
    if(k >= n/2) {
      int maxPft = 0;
      for(int i = 1; i < n; i++) {
        int change = prices[i] - prices[i-1];
        maxPft += change > 0 ? change : 0;
      }
      return maxPft;
    }
    int[][] dp = new int[k+1][n];
    for(int tr = 1; tr <= k; tr++) {
      int maxBlock = dp[tr-1][0] - prices[0];
      for(int i = 1; i < n; i++) {
        dp[tr][i] = Math.max(dp[tr][i-1], prices[i] + maxBlock);
        maxBlock = Math.max(maxBlock, dp[tr-1][i] - prices[i]);
      }
    }
    return dp[k][n-1];
  }


  /** LeetCode 309 **/
//    - Best Time to Buy and Sell Stocks with Cooldown.
//   first, convert prices to maximum-subarray problem (changes).
//   Use DP, time = O(n), space = O(1)
//   6, 1, 3, 2, 4, 7
// => -5, 2, -1, 2, 3
//                  k
//   When we comes to change k, two cases: 1. did k, cooldown - finish a transaction j, cannot do j+1 and j+2.
//   2. no k => noCur = Math.max(noPre, donePre)
  //time = O(n), space = O(n)
  public int maxProfit(int[] prices) {
    int n = prices.length;
    if(n < 2) { return 0; }
    //trans[k] - the max profit if kth change is in the transaction
    int[] trans = new int[n-1];
    //rest[k] - the max profit if kth change is NOT in the transaction
    int[] rest = new int[n-1];
    for(int c = 0; c < n-1; c++) {
      rest[c] = c >= 1 ? Math.max(trans[c-1], rest[c-1]) : 0;
      int change = prices[c+1] - prices[c];
      //if buy on day c & sell on day c+1, there is a cooldown before day c
      int coolDownProfit = change + (c >= 3 ? Math.max(trans[c-3], rest[c-3]) : 0);
      //if buy before day c & sell on day c+1, no cooldown needed.
      int continuousProfit = (c >= 1 ? trans[c-1] : 0) + change;
      trans[c] = Math.max(coolDownProfit, continuousProfit);
    }
    return Math.max(trans[n-2], rest[n-2]);
  }

  //Optimize to constant space.
  //time = O(n), space = O(1)
  public int maxProfit(int[] prices) {
    int n = prices.length;
    if(n < 2) { return 0; }
    /*
    pick change k as the current change, the following is max profit in different days.
    noCur - no transaction on k; doneCur - transaction did on k
    noPre - no transaction on k-1; doneCur - transaction did on k-1
    noPre2 - no transaction on k-2; doneCur2 - transaction did on k-2
    noPre3 - no transaction on k-3; doneCur3 - transaction did on k-3
    */
    int noCur = 0, doneCur = 0;
    int noPre = 0, donePre = 0;
    int noPre2 = 0, donePre2 = 0;
    int noPre3 = 0, donePre3 = 0;
    for (int k = 0; k < n-1; k++) {
      noPre3 = noPre2; donePre3 = donePre2;
      noPre2 = noPre; donePre2 = donePre;
      noPre = noCur; donePre = doneCur;
      //maximum-subarray
      //no k
      noCur = Math.max(donePre, noPre);
      //did k
      int change = prices[k+1] - prices[k];
      int done1 = change + Math.max(donePre3, noPre3);
      int done2 = change + donePre;
      doneCur = Math.max(done1, done2);
    }
    return Math.max(doneCur, noCur);
  }
}
