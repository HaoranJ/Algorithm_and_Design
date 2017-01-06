public String simplifyPath(String path) {
  Deque<String> deque = new ArrayDeque<>();
  String[] tokens = path.trim().substring(1).split("/");
  for (String tk : tokens) {
    if (tk.equals(".") || tk.equals("")) { continue; }
    if (tk.equals("..")) {
      if (!deque.isEmpty()) {
        deque.pop();
      }
    } else {
      deque.push(tk);
    }
  }
  StringBuilder sb = new StringBuilder();
  while (!deque.isEmpty()) {
    sb.append("/");
    sb.append(deque.removeLast());
  }
  if(sb.length() == 0) { sb.append("/"); }
  return sb.toString();
}
