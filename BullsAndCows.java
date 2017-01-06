//Leetcode 299 - Bulls and Cows
//One pass, maintain an array to store the appearances of 0 - 9.
public String getHint(String secret, String guess) {
  int bulls = 0, cows = 0;
  int[] nums = new int[10];
  for (int i = 0; i < secret.length(); i++) {
    int sc = secret.charAt(i) - '0';
    int gs = guess.charAt(i) - '0';
    if (sc == gs) {
      bulls++;
    } else {
      if (nums[sc]-- > 0) { cows++; }
      if (nums[gs]++ < 0) { cows++; }
    }
  }
  return bulls + "A" + cows + "B";
}


public String getHint(String secret, String guess) {
  int bulls = 0, cows = 0;
  Map<Character, Integer> map = new HashMap<>();
  for (int i = 0; i < guess.length(); i++) {
    char sc = secret.charAt(i), gc = guess.charAt(i);
    if (sc == gc) {
      bulls++;
    } else {
      map.put(sc, map.getOrDefault(sc, 0) + 1);
    }
  }
  for (int i = 0; i < guess.length(); i++) {
    char sc = secret.charAt(i), gc = guess.charAt(i);
    if (sc != gc && map.containsKey(gc)) {
      cows++;
      if (map.get(gc) == 1) {
        map.remove(gc);
      } else {
        map.put(gc, map.get(gc) - 1);
      }
    }
  }
  return bulls + "A" + cows + "B";
}
