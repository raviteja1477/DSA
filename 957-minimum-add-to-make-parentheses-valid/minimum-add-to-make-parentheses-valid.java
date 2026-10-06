class Solution {
    public int minAddToMakeValid(String s) {

        int count = 0;
        int ans = 0;

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '(') {
                count++;
            }

            if (s.charAt(i) == ')') {
                count--;

                
                if (count < 0) {
                    ans++;
                    count = 0;
                }
            }
        }

        
        ans = ans + count;

        return ans;
    }
}