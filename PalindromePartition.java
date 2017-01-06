import java.util.*;

public class PalindromePartition {
    public static void main(String[] args) {
        PalindromePartition p = new PalindromePartition();
        String s = "aabaa";
        //p.printAll(p.partition(s));
        p.printAll(p.partition(s));

    }
    private void printAll(List<List<String>> ans){
        for(List<String> list : ans){
            for(String s : list){
                System.out.print(s + " ");
            }
            System.out.println();
        }
    }
    public List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        for(boolean[] row : dp){
            Arrays.fill(row, false);
        }
        for(int i = n-1; i >=0; i--){
            for(int j = i; j < n; j++){
                dp[i][j] = s.charAt(i) == s.charAt(j) && ((j-i < 2) || dp[i+1][j-1]);
            }
        } 
        ArrayList<String> list = new ArrayList<>();
        dfs(ans, list, s, dp, 0);
        return ans;
    }
    private void dfs(List<List<String>> ans, ArrayList<String> list, String s, boolean[][] dp, int i){
        int n = dp.length;
        if(i == n){
            ArrayList<String> temp = new ArrayList<>(list);
            ans.add(temp);
            return;
        }
        int x = i;
        for(int y = x; y < n; y++){
            if(dp[x][y]){
                list.add(s.substring(x, y+1));
                dfs(ans, list, s, dp, y+1);
                list.remove(list.size()-1);
            }
        }
        
    }
}