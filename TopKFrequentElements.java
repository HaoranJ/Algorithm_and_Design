public class TopKFrequentElements {
  public List<Integer> topKFrequent(int[] nums, int k) {
    //LeetCode - 347 - Top K Frequent Elements.
    //time = O(n), space = O(n)
    List<Integer> ans = new ArrayList<>();
    Map<Integer, Integer> freqMap = new HashMap<>();
    for (int x : nums) {
      freqMap.put(x, freqMap.getOrDefault(x, 0) + 1);
    }
    List<Integer>[] buckets = new ArrayList[nums.length+1];
    for (Map.Entry<Integer, Integer> e : freqMap.entrySet()) {
      int num = e.getKey();
      int freq = e.getValue();
      if (buckets[freq] == null) {
        buckets[freq] = new ArrayList<Integer>();
      }
      buckets[freq].add(num);
    }
    for (int i = nums.length; ans.size() < k && i >= 0; i--) {
      if(buckets[i] != null) {
        ans.addAll(buckets[i]);
      }
    }
    return ans;
  }
}
