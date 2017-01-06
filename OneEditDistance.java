public class OneEditDistance {
    //LeetCode 161
    //time = O(n), constant space
    public boolean isOneEditDistance(String s, String t) {
        int sL = s.length();
        int tL = t.length();
        int editDis = 0;
        if (sL > tL) {
            return isOneEditDistance(t,s);
        }
        if (tL - sL >= 2) { return false; }
        else if (tL == sL) {
            for (int j = 0; j < sL; j++) {
                if (s.charAt(j) != t.charAt(j)) {
                    editDis++;
                    if (editDis >= 2) { return false; }
                }
            }
        } else {
            int sp = 0;
            int tp = 0;
            while (sp < sL && tp < tL) {
                if (s.charAt(sp) != t.charAt(tp)) {
                    tp++;
                    editDis++;
                    if (editDis >=2) { return false; }
                } else {
                    sp++;
                    tp++;
                }
            }
            return true;
        }
        return editDis == 1;
    }

    public boolean isOneEditDistance_Concise(String s, String t) {
        int sL = s.length();
        int tL = t.length();
        if (sL > tL) {
            return isOneEditDistance_Concise(t, s);
        }
        for (int j = 0; j < sL; j++) {
            if (s.charAt(j) != t.charAt(j)) {
                if (sL == tL) {
                    return s.substring(j+1).equals(t.substring(j+1));
                } else {
                    return s.substring(j).equals(t.substring(j+1));
                }
            }
        }
        return tL - sL == 1;
    }
}