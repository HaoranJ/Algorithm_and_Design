/*
371. Sum of Two Integers 
*/

public int getSum(int a, int b) {
  int sum = a, carry = b, preSum = 0;
  while (carry != 0) {
    preSum = sum;
    sum = sum ^ carry; //calculate without carrys
    carry = (preSum & carry) << 1; //get the carries of the last line
  }
  return sum;
}

//go through every bit from lowest to highest.
public int getSum(int a, int b) {
  int carry = 0, sum = 0;
  for (int i = 0; i < 32; i++) {
    int aBit = a & 1;
    a = a >> 1;
    int bBit = b & 1;
    b = b >> 1;
    if (carry == 1) {
      if(aBit == 0 && bBit == 0) { sum |= (1 << i); carry = 0; }
      else { sum |= ((aBit & bBit) << i); }
    } else {
      if(aBit == 1 && bBit == 1) { carry = 1; }
      else { sum |= ((aBit | bBit) << i); }
    }
  }
  return sum;
}
