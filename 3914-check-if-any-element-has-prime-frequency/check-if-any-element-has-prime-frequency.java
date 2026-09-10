class Solution {
    public boolean checkPrimeFrequency(int[] nums) {

        for (int i = 0; i < nums.length; i++) {

            int count = 0;

            for (int j = 0; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    count++;
                }
            }

            boolean prime = true;

            if (count < 2) {
                prime = false;
            } else {
                for (int j = 2; j < count; j++) {
                    if (count % j == 0) {
                        prime = false;
                        break;
                    }
                }
            }

            if (prime) {
                return true;
            }
        }

        return false;
    }
}