import java.util.*;

public class GenerateParentheses {
    public static void main(String[] args) {
        GenerateParentheses g = new GenerateParentheses();
        Scanner sc = new Scanner(System.in);
        g.display(g.generateParenthesis(sc.nextInt()));
    }
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        if(n <= 0) { return list; }
        int p = n, q = n;
        StringBuilder sb = new StringBuilder();
        dfs(n, n, 0, sb, list);
        return list;
    }
    private void dfs(int p, int q, int c, StringBuilder sb, List<String> list){
        if(p == 0 && q == 0){ 
            String s = new String(sb);
            list.add(s); 
        }else if(p > 0){ 
            if(c == 0){
                sb.append('(');
                dfs(--p, q, ++c, sb, list);
                sb.deleteCharAt(sb.length()-1);
            }else{
                sb.append('(');
                dfs(p-1, q, c+1, sb, list);
                sb.deleteCharAt(sb.length()-1);
                sb.append(')');
                dfs(p, q-1, c-1, sb, list);
                sb.deleteCharAt(sb.length()-1);
            }
        }else{
            sb.append(')');
            dfs(p, --q, --c, sb, list);
            sb.deleteCharAt(sb.length()-1);
        }
    }
    private void display(List<String> list){
        for(String s : list){
            System.out.println(s + " ");
        }
    }
}