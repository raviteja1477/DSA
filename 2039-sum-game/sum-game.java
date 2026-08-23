class Solution {
    public boolean sumGame(String num) {

        int n = num.length();

        int diff = 0;
        int q1 = 0;
        int q2 = 0;

        // First half
        for (int i = 0; i < n / 2; i++) {
            if (num.charAt(i) == '?') {
                q1++;
            } else {
                diff += num.charAt(i) - '0';
            }
        }

        // Second half
        for (int i = n / 2; i < n; i++) {
            if (num.charAt(i) == '?') {
                q2++;
            } else {
                diff -= num.charAt(i) - '0';
            }
        }

        // Odd number of '?' -> Alice can always win
        if ((q1 + q2) % 2 == 1) {
            return true;
        }

        // Bob wins only if the existing difference can be exactly cancelled
        return diff != 9 * (q2 - q1) / 2;
    }
}