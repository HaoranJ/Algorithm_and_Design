import java.util.*;

public class RoundRobin {
  public static void main(String[] args) {
    int[] arrival = {0,100,200,300};
    int[] run = {400,300,200,100};
    System.out.println(waitingTimeRobin1(arrival, run, 20));
  }
  public static float waitingTimeRobin1(int[] arrival, int[] run, int q) {
    int processNum = arrival.length;
    int[] wait = new int[processNum];
    Deque<Integer> readyQ = new ArrayDeque<>();
    int nextProIdx = 0;
    int clock = 0;
    while (!readyQ.isEmpty() || nextProIdx < processNum) {
      if (readyQ.isEmpty()) {
        readyQ.push(nextProIdx++);
      }
      int pIdx = readyQ.removeLast();
      int nextArr = arrival[pIdx];
      if (clock < nextArr) {
        clock = nextArr;
      } else {
        wait[pIdx] += clock - nextArr;
      }
      int cpu = Math.min(q, run[pIdx]);
      run[pIdx] -= cpu;
      clock += cpu;
      while (nextProIdx < processNum && arrival[nextProIdx] <= clock) {
        readyQ.push(nextProIdx++);
      }
      if (run[pIdx] > 0) {
        arrival[pIdx] = clock;
        readyQ.push(pIdx);
      }
    }
    float waitingTime = 0f;
    for (int num : wait) {
      System.out.println(num);
      waitingTime += (float)num;
    }
    return waitingTime / processNum;
  }
}
