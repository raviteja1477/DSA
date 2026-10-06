class Solution {
    public int removeDuplicates(int[] nums) {

        int n = nums.length;

        for (int i = 1; i < n; i++) {

            if (nums[i] == nums[i - 1]) {

                for (int j = i; j < n - 1; j++) {
                    nums[j] = nums[j + 1];
                }

                n--;
                i--;
            }
        }

        return n;
    }
}