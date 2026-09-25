class Solution {
    int index = 0;

    public List<String> braceExpansionII(String expression) {
        Set<String> result = parse(expression);

        List<String> ans = new ArrayList<>(result);
        Collections.sort(ans);

        return ans;
    }

    private Set<String> parse(String s) {
        Set<String> result = new HashSet<>();

        while (index < s.length() && s.charAt(index) != '}') {

            Set<String> current = new HashSet<>();

            // Expression inside { }
            if (s.charAt(index) == '{') {
                index++; // skip {

                current = parse(s);

                index++; // skip }
            }
            // Letter
            else if (s.charAt(index) >= 'a' && s.charAt(index) <= 'z') {
                current.add(String.valueOf(s.charAt(index)));
                index++;
            }

            // If result is empty, just take current
            if (result.isEmpty()) {
                result = current;
            } 
            // Otherwise concatenate
            else {
                Set<String> temp = new HashSet<>();

                for (String a : result) {
                    for (String b : current) {
                        temp.add(a + b);
                    }
                }

                result = temp;
            }

            // Comma means UNION
            if (index < s.length() && s.charAt(index) == ',') {
                index++;

                Set<String> next = parse(s);

                result.addAll(next);
            }
        }

        return result;
    }
}