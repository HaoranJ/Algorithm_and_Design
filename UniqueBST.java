import java.util.*;
//Leetcode 95, 96
public class UniqueBST {
    public int numTrees(int n) {
        //DP, bottom-up. time = O(n^2), space = O(n)
        int[] nums = new int[n+1];
        nums[0] = 1;
        for(int j = 1; j <=n; j++) {
            int sum = 0;
            for(int p = 1; p <= j; p++) {
                sum += nums[p-1] * nums[j-p];
            }
            nums[j] = sum;
            sum = 0;
        }
        return nums[n];
    }

    private static class Pair {
    	private int position;
    	private int length;
    	private Pair(int p, int l) {
    		position = p;
    		length = l;
    	}

    	@Override
    	public boolean equals(Object o) {
    		if (!(o instanceof Pair)) {
    			return false;
    		}
    		Pair p = (Pair)o;
    		return p.position == this.position && p.length == this.length;
    	}

    	@Override
    	public int hashCode() {
    		int result = 17;
    		result = 31 * result + position;
    		result = 31 * result + length;
    		return result;
    	}
    }

    public static void main(String[] args) {
    	HashMap<Pair, String> map = new HashMap<>();
    	Pair p1 = new Pair(2,1);
    	map.put(p1, "p1");
    	Pair p2 = new Pair(2,1);
    	map.put(p2, "p2");
    	List<TreeNode> list = new ArrayList<>();
    	list.add(null);
    	ListIterator<TreeNode> litr = list.listIterator();
    	while (litr.hasNext()) {
    		System.out.println(litr.next());
    	}

    }

    //DP, time = O(n^3), space ??
    public List<TreeNode> generateTrees(int n) {
    	HashMap<String, List<TreeNode>> map = new HashMap<>();
    	List<TreeNode> list = new ArrayList<>();
    	if (n == 0) { 
  	    list.add(null);
  	    return list; 
    	}
    	for (int j = 1; j <= n; j++) {
    		List<TreeNode> result = new ArrayList<>();
    		result.add(new TreeNode(j));
    		//key = "2,1" means 2th element, length is 1.
    		String key = j + "," + 1;
    		map.put(key, result);
    	}
    	for (int l = 2; l <= n; l++ ) {
    		for (int i = 1; i <= n-l+1 ; i++) {
    			String key = i + "," + l;
    			map.putIfAbsent(key, new ArrayList<TreeNode>());
    			for (int p = i; p <= i+l-1; p++) {
    				String rkey = String.valueOf(p+1) + "," + String.valueOf(i+l-p-1);
    				String lkey = i + "," + String.valueOf(p-i);
    				
    				if (p == i) {
    					ListIterator<TreeNode> rgit = map.get(rkey).listIterator();
    					while (rgit.hasNext()) {
								buildTree(p, null, rgit.next(), map.get(key));	
	    				}
    				} else if (p == i+l-1) {
    					ListIterator<TreeNode> lfit = map.get(lkey).listIterator();
    					while (lfit.hasNext()) {
    						buildTree(p, lfit.next(), null, map.get(key));
	    				}
    				} else {
    					ListIterator<TreeNode> lfit = map.get(lkey).listIterator();
		    			while (lfit.hasNext()) {
		    				TreeNode lnode = lfit.next();
		    				ListIterator<TreeNode> rgit = map.get(rkey).listIterator();
		    				while (rgit.hasNext()) {
		    					buildTree(p, lnode, rgit.next(), map.get(key));
		    				}
		    			}
    				}	
    			}
    		}
    	}
    	return map.get("1," + n);
    }
    private boolean buildTree(int p, TreeNode lnode, TreeNode rnode, List<TreeNode> list) {
    	TreeNode root = new TreeNode(p);
			root.left = lnode;
			root.right = rnode;
			return list.add(root);
    }

    //Recursive method, time = O(n^3)
    public List<TreeNode> generateTreesRecursive(int n) {
    	return recursive(1, n);
    }

    private List<TreeNode> recursive(int start, int len) {
    	List<TreeNode> list = new ArrayList<>();
    	if (len == 0) {
    		list.add(null);
    		return list;
    	}
    	for (int j = start; j < start+len; j++) {
    		List<TreeNode> leftList = recursive(start, j-start);
    		List<TreeNode> rightList = recursive(j+1, len-(j-start)-1);
    		for (TreeNode nodeL : leftList) {
    			for (TreeNode nodeR : rightList) {
    				TreeNode node = new TreeNode(j);
    				node.left = nodeL;
    				node.right = nodeR;
    				list.add(node);
    			}
    		}
    	}
    	return list;
    }
}