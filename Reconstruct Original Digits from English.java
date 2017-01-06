//LeetCode - 423
//use "unique" characters in every num to extract info.
//"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"
public String originalDigits(String s) {
  int[] digitNums = new int[10];
  int[] records = new int[5];
  for (char ch : s.toCharArray()) {
    switch(ch) {
      case 'z': digitNums[0]++; break;
      case 'x': digitNums[6]++; break;
      case 'g': digitNums[8]++; break;
      case 'u': digitNums[4]++; break;
      case 'w': digitNums[2]++; break;
      case 's': records[0]++; break;
      case 'o': records[1]++; break;
      case 'f': records[2]++; break;
      case 'i': records[3]++; break;
      case 'r': records[4]++; break;
      default: break;
    }
  }
  digitNums[7] = records[0] - digitNums[6];
  digitNums[1] = records[1] - digitNums[0] - digitNums[2] - digitNums[4];
  digitNums[5] = records[2] - digitNums[4];
  digitNums[9] = records[3] - digitNums[6] - digitNums[8] - digitNums[5];
  digitNums[3] = records[4] - digitNums[0] - digitNums[4];
  StringBuilder sb = new StringBuilder();
  for (int digit = 0; digit < digitNums.length; digit++) {
    for (int i = 0; i < digitNums[digit]; i++) {
      sb.append(digit);
    }
  }
  return sb.toString();
}
