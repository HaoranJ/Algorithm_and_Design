  /*
  LeetCode 280, Wiggle Sort
  iterate over the unsorted array, for some number in index i, if i is even, nums[i] should <= nums[i+1];
  if i is odd, nums[i] should >= nums[i+1], otherwise swap i and i+1.

  Can use recursion to prove it correct:
    assume 0 - (i-1) is already wiggle-sorted, then this operation would make 0 - i wiggle-sorted.
  time = O(n), in place.
  */

  public void wiggleSort(int[] nums) {
    int n = nums.length;
    if(n < 2) return;
    for(int i = 1; i < n; i++) {
      if (i % 2 == 0 && nums[i-1] < nums[i]) {
        swap(nums, i-1, i);
      }
      if (i % 2 == 1 && nums[i-1] > nums[i]) {
        swap(nums, i-1, i);
      }
    }
  }

  private void swap(int[] nums, int i1, int i2) {
    int tmp = nums[i1];
    nums[i1] = nums[i2];
    nums[i2] = tmp;
  }

  public void wiggleSort(int[] nums) {
     int median = findKthLargest(nums, (nums.length + 1) / 2);
     int n = nums.length;

     int left = 0, i = 0, right = n - 1;

     while (i <= right) {

         if (nums[newIndex(i,n)] >= median) {
             swap(nums, newIndex(left++,n), newIndex(i++,n));
         }
         else if (nums[newIndex(i,n)] < median) {
             swap(nums, newIndex(right--,n), newIndex(i,n));
         }
         else {
             i++;
         }
     }


  }

  private int newIndex(int index, int n) {
     return (1 + 2*index) % (n | 1);
  }
