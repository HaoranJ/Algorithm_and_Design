/* LeetCode 388 - Longest Absolute File Path
Suppose we abstract our file system by a string in the following manner:

The string "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext" represents:

dir
    subdir1
    subdir2
        file.ext
The directory dir contains an empty sub-directory subdir1 and a sub-directory subdir2 containing a file file.ext.

The string "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext" represents:

dir
    subdir1
        file1.ext
        subsubdir1
    subdir2
        subsubdir2
            file2.ext
The directory dir contains two sub-directories subdir1 and subdir2. subdir1 contains a file file1.ext and an empty second-level sub-directory subsubdir1. subdir2 contains a second-level sub-directory subsubdir2 containing a file file2.ext.

We are interested in finding the longest (number of characters) absolute path to a file within our file system. For example, in the second example above, the longest absolute path is "dir/subdir2/subsubdir2/file2.ext", and its length is 32 (not including the double quotes).

Given a string representing the file system in the above format, return the length of the longest absolute path to file in the abstracted file system. If there is no file in the system, return 0.

Note:
The name of a file contains at least a . and an extension.
The name of a directory or sub-directory will not contain a ..
Time complexity required: O(n) where n is the size of the input string.

Notice that a/aa/aaa/file1.txt is not the longest file path, if there is another path aaaaaaaaaaaaaaaaaaaaa/sth.png.
*/

/* Use stack to keep track of file hierarchy
time = O(n), space = O(depth of this file system)
*/
public static class fNode {
  int level;
  String name;
  fNode(int lvl, String nm) {
    level = lvl;
    name = nm;
  }
}

private int getLevel(String str) {
  return str.lastIndexOf("\t") + 1;
}

public int lengthLongestPath(String input) {
  Deque<fNode> stack = new ArrayDeque<>();
  String[] tks = input.split("\\n");
  int ans = 0;
  int pathSum = -1;
  for (String token : tks) {
    int lvl = getLevel(token);
    fNode cur = new fNode(lvl, token.substring(lvl));
    while (!stack.isEmpty() && stack.peek().level >= cur.level) {
      fNode removed = stack.pop();
      pathSum -= removed.name.length() + 1;
    }
    if (token.contains(".")) {
      ans = Math.max(ans, pathSum + 1 + token.substring(lvl).length());
    } else {
      stack.push(cur);
      pathSum += cur.name.length() + 1;
    }
  }
  return ans;
}


/*
1. convert input to a tree-like file system structure, then use DFS to get the longest path.
*/
public static class Node {
  String name;
  List<Node> children;
  boolean isFile;

  Node(String name, boolean isFile) {
    this.name = name;
    this.isFile = isFile;
    children = new ArrayList<>();
  }
}

private int longestPath = 0;
public int lengthLongestPath(String input) {
  if (input.trim().isEmpty()) {
    return 0;
  }
  String[] tks = input.split("\\n");
  Node root = new Node("", false);
  getFileSystem(tks, 0, tks.length-1, root);
  dfs(root, -2);
  return longestPath;
}

private void dfs(Node root, int parentPath) {
  if (root.isFile) {
    longestPath = Math.max(longestPath, parentPath + 1 + root.name.length());
  } else {
    for (Node child : root.children) {
      dfs(child, parentPath + 1 + root.name.length());
    }
  }
}

private void getFileSystem(String[] tks, int startIdx, int endIdx, Node parent) {
  if (endIdx < startIdx) {
    return;
  }
  if (tks[startIdx].startsWith("\t") || tks[i].startsWith("    ")) {
    for (int i = startIdx; i <= endIdx; i++) {
      if (tks[i].startsWith("\t")) {
        tks[i] = tks[i].substring(1);
      } else if (tks[i].startsWith("    ")) {
        tks[i] = tks[i].substring(4);
      }
    }
  }

  int lo = startIdx, hi = startIdx + 1;
  while (hi <= endIdx) {
    while (hi <= endIdx && (tks[hi].startsWith("\t") || tks[hi].startsWith("    "))) {
      hi++;
    }
    String rootName = tks[lo];
    Node root = new Node(rootName, rootName.contains("."));
    parent.children.add(root);
    getFileSystem(tks, lo+1, hi-1, root);
    lo = hi;
    hi++;
  }
  if (hi == endIdx + 1) {
    String rootName = tks[lo];
    Node root = new Node(rootName, rootName.contains("."));
    parent.children.add(root);
    getFileSystem(tks, lo+1, hi-1, root);
  }
}
