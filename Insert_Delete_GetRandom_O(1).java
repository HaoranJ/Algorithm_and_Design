/*LeetCode - 380
Besides idxMap, we need a valList to make getRandom O(1).
For ArrayList, remove(int idx) is linear time.
*/
public class RandomizedSet {
  Map<Integer, Integer> idxMap;
  List<Integer> valList;
  Random random;
  /** Initialize your data structure here. */
  public RandomizedSet() {
    idxMap = new HashMap<>();
    valList = new ArrayList<>();
    random = new Random();
  }

  /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
  public boolean insert(int val) {
    if (idxMap.containsKey(val)) {
      return false;
    }
    idxMap.put(val, valList.size());
    valList.add(val);
    return true;
  }

  /** Removes a value from the set. Returns true if the set contained the specified element. */
  public boolean remove(int val) {
    if (!idxMap.containsKey(val)) {
      return false;
    }
    int idx = idxMap.get(val);
    if (idx != valList.size()-1) {
      //key!! swap the removed one with last one ===> O(1)
      int lastInt = valList.get(valList.size()-1);
      valList.set(idx, lastInt);
      idxMap.put(lastInt, idx);
    }
    valList.remove(valList.size()-1);
    idxMap.remove(val);
    return true;
  }

  /** Get a random element from the set. */
  public int getRandom() {
    return valList.get(random.nextInt(valList.size()));
  }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */

/*LeetCode - 381
*/
public class RandomizedCollection {
  List<Integer> numList;
  Map<Integer, Set<Integer>> idxMap;
  Random rand;
  /** Initialize your data structure here. */
  public RandomizedCollection() {
    numList = new ArrayList<>();
    idxMap = new HashMap<>();
    rand = new Random();
  }

  /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
  public boolean insert(int val) {
    int idx = numList.size();
    numList.add(val);
    if (idxMap.get(val) == null || idxMap.get(val).isEmpty()) {
      if (!idxMap.containsKey(val)) {
        //key!! use LinkedHashSet to quickly pick up one idx for some num.
        //Because it has a lower-cost iterator.
        idxMap.put(val, new LinkedHashSet<Integer>());
      }
      idxMap.get(val).add(idx);
      return true;
    } else {
      idxMap.get(val).add(idx);
      return false;
    }
  }

  /** Removes a value from the collection. Returns true if the collection contained the specified element. */
  public boolean remove(int val) {
    if (!idxMap.containsKey(val) || idxMap.get(val).isEmpty()) {
      return false;
    }
    Iterator<Integer> itr = idxMap.get(val).iterator();
    int idx = itr.next();
    itr.remove();
    if (idx != numList.size()-1) {
      int lastOne = numList.get(numList.size()-1);
      numList.set(idx, lastOne);
      Set<Integer> lastOneIdxSet = idxMap.get(lastOne);
      lastOneIdxSet.remove(new Integer(numList.size()-1));
      lastOneIdxSet.add(idx);
    }
    numList.remove(numList.size()-1);
    return true;
  }

  /** Get a random element from the collection. */
  public int getRandom() {
    return numList.get(rand.nextInt(numList.size()));
  }
}
