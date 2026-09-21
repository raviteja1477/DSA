class Solution {
    public long[] resultArray(int[] nums, int k) {

        long[] result = new long[k];
        long[] dp = new long[k];

        for (int num : nums) {

            long[] next = new long[k];

            for (int r = 0; r < k; r++) {
                int index = (int)(((long) r * num) % k);

                next[index] += dp[r];
            }

            next[num % k]++;

            for (int r = 0; r < k; r++) {
                result[r] += next[r];
            }

            dp = next;
        }

        return result;
    }
}