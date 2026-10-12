class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int mod = 1000000007;
        int[] count = new int[32];

        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                if ((num & (1 << i)) != 0) {
                    count[i]++;
                }
            }
        }

        long ans = 0;

        for (int j = 0; j < k; j++) {
            long num = 0;

            for (int i = 0; i < 32; i++) {
                if (count[i] > 0) {
                    num |= (1L << i);
                    count[i]--;
                }
            }

            ans = (ans + num * num) % mod;
        }

        return (int) ans;
    }
}