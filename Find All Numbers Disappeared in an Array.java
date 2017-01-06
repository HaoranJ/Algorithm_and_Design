//LeetCode 448 Find All Numbers Disappeared in an Array
// Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
//
// Find all the elements of [1, n] inclusive that do not appear in this array.
//
// Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.
//
// Example:
//
// Input:
// [4,3,2,7,8,2,3,1]
//
// Output:
// [5,6]

//1. Put num i to the position at index (i-1), then after a scan loop, return (idx+1) that at index idx,
//the num != idx + 1
//time = O(n), in place
public List<Integer> findDisappearedNumbers(int[] nums) {
  List<Integer> ans = new ArrayList<>();
  for(int i = 0; i < nums.length; i++) {
    while (nums[i] != i+1) {
      if(nums[i] == nums[nums[i]-1]) {
        break;
      } else {
        //every index will be swapped at most once.
        int temp = nums[nums[i]-1];
        nums[nums[i]-1] = nums[i];
        nums[i] = temp;
      }
    }
  }
  for(int i = 0; i < nums.length; i++) {
    if(nums[i] != i+1) {
      ans.add(i+1);
    }
  }
  return ans;
}

public List<Integer> findDisappearedNumbers(int[] nums) {
  List<Integer> ans = new ArrayList<>();
  for (int i = 0; i < nums.length; i++) {
    int curNum = Math.abs(nums[i]);
    if(nums[curNum - 1] > 0) {
      nums[curNum-1] = -nums[curNum-1];
    }
  }
  for (int i = 0; i < nums.length; i++) {
    if (nums[i] > 0) {
      ans.add(i+1);
    }
  }
  return ans;
}
