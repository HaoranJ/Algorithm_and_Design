/*LeetCode 475 - Heaters.
Winter is coming! Your first job during the contest is to design a standard heater with fixed warm radius to warm all the houses.

Now, you are given positions of houses and heaters on a horizontal line, find out minimum radius of heaters so that all houses could be covered by those heaters.

So, your input will be the positions of houses and heaters seperately, and your expected output will be the minimum radius standard of heaters.

Note:
Numbers of houses and heaters you are given are non-negative and will not exceed 25000.
Positions of houses and heaters you are given are non-negative and will not exceed 10^9.
As long as a house is in the heaters' warm radius range, it can be warmed.
All the heaters follow your radius standard and the warm radius will the same.
Example 1:
Input: [1,2,3],[2]
Output: 1
Explanation: The only heater was placed in the position 2, and if we use the radius 1 standard, then all the houses can be warmed.
Example 2:
Input: [1,2,3,4],[1,4]
Output: 1
Explanation: The two heater was placed in the position 1 and 4. We need to use radius 1 standard, then all the houses can be warmed.
*/

/* Binary search, iterate every house, for house x find out the insertion position in heaters, and then get the
smaller warm distance between two heaters as a candidate radius. The final result shoule be the max of all the
candidate radius.
m = houses.length, n = heaters.length
time = O(m * logn)
*/
public int findRadius(int[] houses, int[] heaters) {
  Arrays.sort(houses);
  Arrays.sort(heaters);
  int ans = 0;
  for (int ho : houses) {
    int idx = Arrays.binarySearch(heaters, ho);
    if (idx < 0) {
      int insertPos = -idx - 1;
      int leftDist = insertPos == 0 ? Integer.MAX_VALUE : Math.abs(ho - heaters[insertPos - 1]);
      int rightDist = insertPos == heaters.length ? Integer.MAX_VALUE : Math.abs(ho - heaters[insertPos]);
      int dist = Math.min(leftDist, rightDist);
      ans = Math.max(ans, dist);
    }
  }
  return ans;
}

public int findRadius(int[] houses, int[] heaters) {
  if (houses.length == 0 || heaters.length == 0) {
    return 0;
  }
  Arrays.sort(houses);
  Arrays.sort(heaters);
  int headRadius = (heaters[0] - houses[0] > 0) ? heaters[0] - houses[0] : 0;
  int tailRadius = (heaters[heaters.length-1] < houses[houses.length-1]) ? houses[houses.length-1] - heaters[heaters.length-1] : 0;
  int minRadius = Math.max(headRadius, tailRadius);
  for (int i = 0; i < heaters.length - 1; i++) {
    int lo = heaters[i], hi = heaters[i+1];
    int mid = lo + (hi - lo) / 2;
    int idx = Arrays.binarySearch(houses, mid);
    int radius = -1;
    if (idx >= 0) {
      radius = houses[idx] - lo;
    } else {
      int insertPos = -idx - 1;
      if (insertPos != 0 && houses[insertPos-1] >= lo) {
        radius = houses[insertPos-1] - lo;
      }

      if (insertPos != houses.length && houses[insertPos] <= hi) {
        radius = Math.max(radius, hi - houses[insertPos]);
      }
    }
    minRadius = Math.max(minRadius, radius);
  }
  return minRadius;
}

/* Two pointers.
for every house, keep track of the nearest heater for this house.
time = O(m + n)
*/
public int findRadius(int[] houses, int[] heaters) {
  Arrays.sort(houses);
  Arrays.sort(heaters);
  int ho = 0, he = 0;
  int radius = -1;
  while (ho < houses.length) {
    while (he < heaters.length - 1 && Math.abs(heaters[he+1] - houses[ho]) < Math.abs(heaters[he] - houses[ho])) {
      he++;
    }
    radius = Math.max(radius, Math.abs(heaters[he] - houses[ho]));
    ho++;
  }
  return radius;
}
