import java.util.*;

public class GasStation {
  /*Leetcode - 134
  1. If A can't reach B, any stations between A and B can't reach B
  2. If sum of gas > sum of cost, it must have solution.
  */
  //time = O(n)
  public int canCompleteCircuit(int[] gas, int[] cost) {
    int gasSum = 0, costSum = 0, start = 0, tank = 0;
    for (int i = 0; i < gas.length; i++) {
      gasSum += gas[i];
      costSum += cost[i];
      tank += gas[i] - cost[i];
      if (tank < 0) {
        start = i+1;
        tank = 0;
      }
    }
    if (gasSum >= costSum) {
      return start;
    } else {
      return -1;
    }
  }

  //time = o(n^2)(worst case)
  public int canCompleteCircuit(int[] gas, int[] cost) {
    int stationNum = gas.length;
    int i = 0, loc = 0, tank = 0;
    while(i < stationNum) {
      boolean completed = false;
      if(gas[i] >= cost[i]) {
        loc = i;
        tank = 0;
        do {
          tank += (gas[loc]-cost[loc]);
          if(tank < 0) {
            break;
          }
          loc = (loc+1) % stationNum;
        } while(loc != i);
        completed = loc == i ? true : false;
        if(completed) { return i; }
        //can't reach itself, no solution
        else if(i == loc + 1) { break; }
        //skip the stations between i and loc+1
        else { i = loc + 1; }
      } else {
        i++;
      }
    }
    return -1;
  }
}
