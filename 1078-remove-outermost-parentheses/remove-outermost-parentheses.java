class Solution {
    public String removeOuterParentheses(String s) {

        StringBuilder sb = new StringBuilder();
        int count = 0;

        for(int i = 0; i < s.length(); i++) {

            if(s.charAt(i) == '(') {
                count++;
            } else {
                count--;
            }

            if(count == 1 && s.charAt(i) == '(') {
                continue;
            }

            if(count == 0 && s.charAt(i) == ')') {
                continue;
            }

            sb.append(s.charAt(i));
        }

        return sb.toString();
    }
}