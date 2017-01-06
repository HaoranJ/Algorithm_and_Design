public class HouseRobber {
  public int rob(int[] nums) {
  	int len = nums.length;
  	if (len == 0) {
  		return 0;
  	}
  	int[] sums = new int[len + 1];
  	sums[0] = 0;
  	sums[1] = nums[0];
  	for(int j = 2; j < len + 1; j++) {
  		sums[j] = sums[j-1] > (sums[j-2]+nums[j-1]) ? sums[j-1] : (sums[j-2]+nums[j-1]);
  	}    
  	return sums[len];
  }

  public static void main(String[] args) {
  	HouseRobber hr = new HouseRobber();
  	int[] arr = {10};
  	System.out.println(hr.rob(arr));
  }
}