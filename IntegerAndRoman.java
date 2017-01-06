import java.util.*;

public class IntegerAndRoman {
	//time = O(n)
  public int romanToInt(String s) {
    Map<Character, Integer> table = new HashMap<>();
    table.put('M',1000);
    table.put('D',500);
    table.put('C',100);
    table.put('L',50);
    table.put('X',10);
    table.put('V',5);
    table.put('I',1);
      
    int ret = 0;
    int preNum = 0;
    for (char c : s.toCharArray()) {
    	int curNum = table.get(c);
    	ret += preNum >= curNum ? curNum : (curNum - 2 * preNum);
    	preNum = curNum;
    }
    return ret;
  }

  private static final int[] values = {
  	1000, 900, 500, 400, 
  	100, 90, 50, 40,
  	10, 9, 5, 4,
  	1
  };

  private static final String[] symbols = {
  	"M", "CM", "D", "CD",
  	"C", "XC", "L", "XL",
  	"X", "IX", "V", "IV",
  	"I"
  };

  public String intToRoman(int num) {
  	StringBuilder sb = new StringBuilder();
  	for (int i = 0; i < values.length; i++) {
  		int k = values[i];
  		int quo = num / k;
  		num = num % k;
  		for (int j = 0; j < quo; j++) {
  			sb.append(symbols[i]);
  		}
  		if (num == 0) { break; }
  	}
  	return sb.toString();
  }
}