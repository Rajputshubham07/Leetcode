class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> map = new HashMap<>();

        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }

        StringBuilder ans = new StringBuilder();
        StringBuilder key = new StringBuilder();
        boolean inside = false;

        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                inside = true;
                key.setLength(0);
            } 
            else if (ch == ')') {
                inside = false;
                ans.append(map.getOrDefault(key.toString(), "?"));
            } 
            else {
                if (inside) {
                    key.append(ch);
                } else {
                    ans.append(ch);
                }
            }
        }

        return ans.toString();
    }
}