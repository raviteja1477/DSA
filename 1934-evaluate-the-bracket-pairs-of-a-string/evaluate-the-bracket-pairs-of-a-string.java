import java.util.*;

class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {

        HashMap<String, String> map = new HashMap<>();

        // Store knowledge in HashMap
        for (List<String> k : knowledge) {
            map.put(k.get(0), k.get(1));
        }

        StringBuilder ans = new StringBuilder();

        int i = 0;

        while (i < s.length()) {

            if (s.charAt(i) == '(') {

                int j = i + 1;

                // Find closing bracket
                while (s.charAt(j) != ')') {
                    j++;
                }

                // Get key between brackets
                String key = s.substring(i + 1, j);

                // Check key in HashMap
                if (map.containsKey(key)) {
                    ans.append(map.get(key));
                } else {
                    ans.append("?");
                }

                // Move after ')'
                i = j + 1;

            } else {

                // Normal character
                ans.append(s.charAt(i));
                i++;
            }
        }

        return ans.toString();
    }
}