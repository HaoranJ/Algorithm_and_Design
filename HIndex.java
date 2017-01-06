import java.util.*;

public class HIndex {
  //LeetCode 274.
  //start with simple examples.
  //time = O(nlogn)
  public int hIndex(int[] citations) {
    assert citations != null;
    Arrays.sort(citations);
    int n = citations.length;
    int h = n;
    while(h > 0 && citations[n-h] < h) {
      h--;
    }
    return h;
  }

  //thinking similar to counting sort.
  //use extra array to record the occurence times of each citation.
  //time = O(n), space = O(n)
  public int hIndex_LinearTime(int[] citations) {
    int paperNum = citations.length;
    int[] counts = new int[paperNum+1];
    for (int c : citations) {
      if (c >= paperNum) {
        counts[paperNum]++;
      } else {
        counts[c]++;
      }
    }
    int papers = 0;
    int hIndex = 0;
    for (int i = paperNum; i >= 0; i--) {
      papers += counts[i];
      int citation = i;
      if (citation >= papers) {
        hIndex = papers;
      } else {
        hIndex = Math.max(citation, hIndex); //{2,3,2}  or {1,1,1}
        break;
      }
    }
    return hIndex;
  }
}
