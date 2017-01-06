public int previousPermutation(int[] digits) {
    int n = digits.length;
    int pivot = n - 1;
    //find the pivot - the smallest idx that make digits[pivot, n-1] is an descending subarray.
    while(pivot > 0 && digits[pivot] >= digits[pivot-1]) {
        pivot--;
    }
    //already the smallest permutation.
    if(pivot == 0) {
      return -1;
    }
    //the previous permutation => find the minimum decrease => find the largest num that is smaller than headNum in right side
    int headNum = digits[pivot - 1];
    int swapIdx = pivot;
    while(swapIdx < n && headNum > digits[swapIdx]) {
        swapIdx++;
    }
    swap(digits, pivot-1, swapIdx-1);
    reverse(digits, pivot, n-1);
    //assume the input is not overflow for integer.
    return toInt(digits);
}

public int nextPermutation(int[] digits) {
    int n = digits.length;
    int pivot = n - 1;
    //find the pivot - the smallest idx that make digits[pivot, n-1] is an descending subarray.
    while(pivot > 0 && digits[pivot] <= digits[pivot-1]) {
        pivot--;
    }
    //already the largest permutation.
    if(pivot == 0) {
      return -1;
    }
    //the next permutation => find the minimum increase => find the smallest num that is larger than headNum in right side
    int headNum = digits[pivot - 1];
    int swapIdx = pivot;
    while(swapIdx < n && headNum < digits[swapIdx]) {
        swapIdx++;
    }
    swap(digits, pivot-1, swapIdx-1);
    reverse(digits, pivot, n-1);
    //assume the input is not overflow for integer.
    return toInt(digits);
}

private void reverse(int[] nums, int lo, int hi) {
    while(lo < hi) {
        swap(nums, lo, hi);
        lo++;
        hi--;
    }
}

private void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
}

private toInt(int[] digits) {
  assert digits.length > 0;
  int ans = digits[0];
  for (int i = 1; i < digits.length; i++) {
    ans = ans * 10 + digits[i];
  }
  return ans;
}

//Assume we have a input which is an integer array, and every element is a digit.
//Now we want to get the kth smallest permutation.
//I use a DFS to recursively get the higher digits first and then go to lower digits based on
//the number of possible permuations for the right-side digits.
//i.e. {1,2,3,4}, if we put the first(highest) digit to 1, then we could have 3! = 6 possible permuations for 2,3,4 on
//its right side.
int[] factorials;
public String getPermutation(int[] input, int k) {
  Arrays.sort(input);
  List<Integer> digits = new ArrayList<>(Arrays.asList(input));
  for (int i = 1; i <= n; i++) {
    digits.add(i);
  }
  factorials = new int[n+1];
  factorials[0] = 1;
  for (int i = 1; i <= n; i++) {
    factorials[i] = factorials[i-1] * i;
  }
  assert k <= factorials[n];
  return dfs(digits, k);
}

private String dfs(List<Integer> digits, int k) {
  for (int i = 0; i < digits.size(); i++) {
    int digit = digits.get(i);
    int permNum = factorials[digits.size() - 1];
    if (permNum >= k) {
      List<Integer> digitsCopy = new ArrayList<>(digits);
      digitsCopy.remove(i);
      return String.valueOf(digit) + dfs(digitsCopy, k);
    } else {
      k -= permNum;
    }
  }
  return "";
}
