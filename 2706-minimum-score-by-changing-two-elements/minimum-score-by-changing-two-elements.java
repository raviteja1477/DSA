
class Solution {
    public int minimizeSum(int[] nums) {

        int n = nums.length;

        Arrays.sort(nums);

        if (n == 3) {
            return 0;
        }

        int ans = Integer.MAX_VALUE;

        ans = Math.min(ans, nums[n - 1] - nums[2]);
        ans = Math.min(ans, nums[n - 2] - nums[1]);
        ans = Math.min(ans, nums[n - 3] - nums[0]);

        return ans;
    }
}
