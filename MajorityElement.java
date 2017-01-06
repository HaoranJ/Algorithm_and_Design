public class MajorityElement {
  //Divide & Conquer, Recursive.
  //time = O(nlogn)
  public int majorityElement(int[] nums) {
    return majorityElement(nums, 0, nums.length-1);
  }

  private int majorityElement(int[] nums, int lo, int hi) {
    //base case
    if(lo == hi) { return nums[lo]; }
    int mid = (lo + hi) / 2;
    int lMar = majorityElement(nums, lo, mid);
    int rMar = majorityElement(nums, mid+1, hi);
    if(lMar == rMar) { return lMar; }
    return occurence(nums, lo, hi, lMar) > occurence(nums, lo, hi, rMar) ? lMar : rMar;
  }

  private int occurence(int[] nums, int lo, int hi, int ele) {
    int sum = 0;
    for (int i = lo; i <= hi; i++) {
      if(nums[i] == ele) { sum++; }
    }
    return sum;
  }

  //Bit manipulation. time = O(n)
  public int majorityElement(int[] nums) {
    int ans = 0;
    for (int i = 0, mask = 1; i < 32; i++, mask <<= 1) {
      int onesCount = 0;
      for (int num : nums) {
        if ((num & mask) != 0) {
          onesCount++;
          if(onesCount > nums.length/2) {
            ans |= mask;
            break;
          }
        }
      }
    }
    return ans;
  }

  //Moore Voting Algorithm, time = O(n)
  public int majorityElement(int[] nums) {
    int cand = 0, counter = 0;
    for (int num : nums) {
      if (counter == 0) {
        cand = num;
        counter++;
      } else {
        counter += cand == num ? 1 : -1;
      }
    }
    return cand;
  }

  //LeetCode - 229. time = O(n), space = O(1)
  // 1. there are no elements that appears more than n/3 times, then whatever the algorithm
  //  got from 1st round wound be rejected in the second round.
  // 2. there are only one elements that appears more than n/3 times, after 1st round one of
  //  the candicate must be that appears more than n/3 times(<2n/3 other elements could only
  //  pair out for <n/3 times), the other candicate is not necessarily be the second most frequent
  //  but it would be rejected in 2nd round.
  // 3. there are two elments appears more than n/3 times, candicates would contain both of
  //  them. (<n/3 other elements couldn't pair out any of the majorities.)
  public List<Integer> majorityElementII(int[] nums) {
    int cand1 = 0, cand2 = 0, counter1 = 0, counter2 = 0;
    for (int num : nums) {
      if (num == cand1) {
        counter1++;
      } else if (num == cand2) {
        counter2++;
      } else if (counter1 == 0) {
        cand1 = num;
        counter1++;
      } else if (counter2 == 0) {
        cand2 = num;
        counter2++;
      } else {
        counter1--;
        counter2--;
      }
    }
    List<Integer> ans = new ArrayList<>();
    if (occurence(nums, cand1) > nums.length / 3) {
      ans.add(cand1);
    }
    if (cand1 != cand2 && occurence(nums, cand2) > nums.length / 3) {
      ans.add(cand2);
    }
    return ans;
  }

  private int occurence(int[] nums, int x) {
    int sum = 0;
    for (int num : nums) {
      if (x == num) {
        sum++;
      }
    }
    return sum;
  }
}
