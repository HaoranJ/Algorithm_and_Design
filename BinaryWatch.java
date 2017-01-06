//LeetCode 401. Binary Watch
public List<String> readBinaryWatch(int num) {
  List<String> ans = new ArrayList<>();
  for (int hr = 0; hr < 12; hr++) {
    for (int min = 0; min < 60; min++) {
      //key!!!!!
      if (Integer.bitCount(hr * (1 << 6) + min) == num) {
        ans.add(String.format("%d:%02d", hr, min));
      }
    }
  }
  return ans;
}

/**
split the input num into two parts - hour and minute.
*/
private static int[] tops = {1, 2, 4, 8};
private static int[] bots = {1, 2, 4, 8, 16, 32};
private Map<Integer, List<String>> hourMap = new HashMap<>();
private Map<Integer, List<String>> minMap = new HashMap<>();

public Solution() {
  for (int i = 0; i < (1 << tops.length); i++) {
    int count = 0;
    int mask = 1;
    int sum = 0;
    for (int j = 0; j < tops.length; j++) {
      int led = i & (mask << j);
      if (led > 0) {
        sum += tops[j];
        count++;
      }
    }
    if (sum <= 11) {
      hourMap.putIfAbsent(count, new ArrayList<>());
      hourMap.get(count).add(String.valueOf(sum));
    }
  }

  for (int i = 0; i < (1 << bots.length); i++) {
    int count = 0;
    int mask = 1;
    int sum = 0;
    for (int j = 0; j < bots.length; j++) {
      int led = i & (mask << j);
      if (led > 0) {
        sum += bots[j];
        count++;
      }
    }
    if (sum <= 59) {
      minMap.putIfAbsent(count, new ArrayList<>());
      String minute = sum < 10 ? ("0" + sum) : String.valueOf(sum);
      minMap.get(count).add(minute);
    }
  }
}

public List<String> readBinaryWatch(int num) {
  assert num >= 0 && num <= 10;
  List<String> ans = new ArrayList<>();
  for (int top = 0; top <= Math.min(num, 3); top++) {
    int bot = num - top;
    if (bot < 6) {
      List<String> hours = hourMap.get(top);
      List<String> mins = minMap.get(bot);
      for (String hr : hours) {
        for (String mt : mins) {
          ans.add(hr + ":" + mt);
        }
      }
    }
  }
  return ans;
}
