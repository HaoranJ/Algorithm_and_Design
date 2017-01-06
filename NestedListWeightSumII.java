/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class Solution {
  //get the largest depth first, then go recursively to get the weighted sum.
  public int depthSumInverse(List<NestedInteger> nestedList) {
    int maxDepth = getMaxDepth(nestedList);
    return depthSumInverse(nestedList, maxDepth);
  }

  private int depthSumInverse(List<NestedInteger> nestedList, int depth) {
    assert depth > 0;
    int sum = 0;
    for (NestedInteger ni : nestedList) {
      if (ni.isInteger()) {
        sum += depth * ni.getInteger();
      } else {
        sum += depthSumInverse(ni.getList(), depth-1);
      }
    }
    return sum;
  }

  private int getMaxDepth(List<NestedInteger> nestedList) {
    int ret = 1;
    for (NestedInteger ni : nestedList) {
      if (!ni.isInteger()) {
        ret = Math.max(ret, getMaxDepth(ni.getList()) + 1);
      }
    }
    return ret;
  }

  //Going from the top level to the bottom, add the unweighted sum in previous levels multiple times
  public int depthSumInverse(List<NestedInteger> nestedList) {
    int unweighted = 0, ans = 0;
    while (!nestedList.isEmpty()) {
      List<NestedInteger> nextLvl = new ArrayList<>();
      for (NestedInteger ni : nestedList) {
        if (ni.isInteger()) {
          unweighted += ni.getInteger();
        } else {
          nextLvl.addAll(ni.getList());
        }
      }
      ans += unweighted;
      nestedList = nextLvl;
    }
    return ans;
  }
}
