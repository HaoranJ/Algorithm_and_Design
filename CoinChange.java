//LeetCode - 322 Coin Change.
// You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
//
// Example 1:
// coins = [1, 2, 5], amount = 11
// return 3 (11 = 5 + 5 + 1)
//
// Example 2:
// coins = [2], amount = 3
// return -1.
//
// Note:
// You may assume that you have an infinite number of each kind of coin.
//DP - use two arrays to keep track of fewest number of coins and which coin to change at any cents.
//time = O(coins.length * cents), space = O(cents)
public int getCoins(int[] coins, int cents) {
  //minCoins[i] = the fewest coins for i cents
  int[] minCoins = new int[cents+1];
  //coinToChange[j] = at j cents, what denomination of coin can be changed for the fewest coins.
  //i.e. coinToChange[100] = 25 means from 100 cents, we should change it to a quarter then go to 75 cents.
  int[] coinToChange = new int[cents+1];
  Arrays.sort(coins);
  minCoins[0] = 0;
  //One-dimensional DP.
  for(int amt = 1; amt <= cents; amt++) {
    //fewest number of coins
    int minRes = Integer.MAX_VALUE;
    //some cents can't be changed.
    boolean changeable = false;
    //best coin to change at amt cents
    int bestCoin = -1;
    for(int coin : coins) {
      if(coin > amt) {
        break;
      } else if (coin == amt) {
        minRes = 1;
        bestCoin = coin;
        changeable = true;
        break;
      } else {
        if(minCoins[amt-coin] != -1) {
          int curRes = minCoins[amt-coin] + 1;
          if(curRes < minRes) {
            minRes = curRes;
            bestCoin = coin;
            changeable = true;
          }
        }
      }
    }
    coinToChange[amt] = bestCoin;
    minCoins[amt] = changeable ? minRes : -1;
  }
  //if this amount of cents can be changed, we use a map to keep track of the combinations of optimal coins.
  if(coinToChange[cents] != -1) {
    Map<Integer, Integer> coinNumMap = new HashMap<>();
    int c = cents;
    while(c > 0) {
      int coinToPut = coinToChange[c];
      coinNumMap.put(coinToPut, coinNumMap.getOrDefault(coinToPut, 0) + 1);
      c -= coinToPut;
    }
  }
  //the fewest number of coins for the amount of cents to change.
  return minCoins[cents];
}
