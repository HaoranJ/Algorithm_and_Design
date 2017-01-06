/*LeetCode - 275.
Use binary search.
*/
public int hIndex(int[] citations) {
  int paperSum = 0;
  int lo = 0, hi = citations.length-1;
  while (lo <= hi) {
    int mid = lo + (hi - lo) / 2;
    int curPapers = hi - mid + 1;
    int tmpPaperSum = curPapers + paperSum;
    if (citations[mid] == tmpPaperSum) {
      return tmpPaperSum;
    } else if (citations[mid] > tmpPaperSum) {
      hi = mid - 1;
      paperSum = tmpPaperSum;
    } else {
      lo = mid + 1;
    }
  }
  return paperNum;
}
