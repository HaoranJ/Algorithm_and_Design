private static String[] DIGITS = { "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
private static String[] TENS = {"Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety" };
private static String[] UNDER20 = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen" };
public String numberToWords(int num) {
  if(num == 0) { return "Zero"; }
  StringBuilder sb = new StringBuilder();
  String chunkStr;

  int chunk = num / 1000000000;
  num = num % 1000000000;
  if(chunk > 0) { sb.append(getChunkStr(chunk) + " Billion "); }

  chunk = num / 1000000;
  num = num % 1000000;
  if(chunk > 0) { sb.append(getChunkStr(chunk) + " Million "); }

  chunk = num / 1000;
  num = num % 1000;
  if(chunk > 0) { sb.append(getChunkStr(chunk) + " Thousand "); }

  chunk = num;
  if(chunk > 0) { sb.append(getChunkStr(chunk)); }

  return sb.toString().trim();
}

private String getChunkStr(int chunk) {
  StringBuilder sb = new StringBuilder();
  int hun = chunk / 100;
  chunk = chunk % 100;
  if (hun > 0) {
    sb.append(DIGITS[hun]);
    sb.append(" Hundred ");
  }
  if (chunk < 20 && chunk >= 10) {
    sb.append(UNDER20[chunk-10]);
    return sb.toString().trim();
  }
  int ten = chunk / 10;
  chunk = chunk % 10;
  if (ten > 1){
    sb.append(TENS[ten-2] + " ");
  }
  sb.append(DIGITS[chunk]);
  return sb.toString().trim();
}
