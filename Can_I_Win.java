//LeetCode - 464 Can I win
//DFS with memorization, time = O(n!) without memorization
//https://en.wikipedia.org/wiki/Minimax
//The game tree is in the view of only ONE player.
//      player1 ->  0
//               /| ...\
//   player2 -> 1 2 ....max
//             /|\ ..../ | \
// player1 -> 2 3...  1  2 ..max-1
//            ...                \
// player1 ->   /      |     \   lose
// player2 -> lose   win   lose
//mustWin - the player can pick up a num from the pool, then the opponent mustLose (in this branch all leaves are "win").
//mustLose - No matter which num the player picks, then opponent mustWin.

//memorization - use bit to memory,
//for nums 1...10, the possibility number = 2^10
//-1 = false; 0 = undefined; 1 = true
private int[] mem;
public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
  int caseNum = 1<<maxChoosableInteger;
  mem = new int[caseNum];
  return move(maxChoosableInteger, desiredTotal, caseNum-1);
}

private boolean move(int maxChoosableInteger, int desiredTotal, int caseIdx) {
  if(mem[caseIdx] != 0) {
    return mem[caseIdx] == 1;
  }
  int min = 21, max = 0, sum = 0;
  for(int i = 0; i < maxChoosableInteger; i++) {
    int num = i + 1;
    int mask = 1 << i;
    if((caseIdx & mask) == mask) {
      min = Math.min(min, num);
      max = Math.max(max, num);
      sum += num;
    }
  }
  //easy to win
  if(max >= desiredTotal) {
    mem[caseIdx] = 1;
    return true;
  }
  //must lose || no body can win
  if(min + max >= desiredTotal || sum < desiredTotal) {
    mem[caseIdx] = -1;
    return false;
  }
  for(int i = 0; i < maxChoosableInteger; i++) {
    int num = i + 1;
    int mask = 1 << i;
    if((caseIdx & mask) == mask) {
      int nextCaseIdx = caseIdx ^ mask;
      // mustWin is the exact opposite of mustLose ==> two players can use the same "move" method.
      boolean oppoWin = move(maxChoosableInteger, desiredTotal - num, nextCaseIdx);
      if(!oppoWin) {
        mem[caseIdx] = 1;
        return true;
      }
    }
  }
  mem[caseIdx] = -1;
  return false;
}
