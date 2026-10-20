class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int ans = 0;

        for (int k = 1; k <= n; k++) {
            if (n % k != 0) continue;

            boolean valid = true;
            int prevMax = Integer.MIN_VALUE;

            for (int start = 0; start < n && valid; start += k) {
                int count = 0;
                int min = nums[start];
                int max = nums[start];

                for (int i = start; i < start + k; i++) {
                    int next = (i + 1 - start) % k + start;
                    if (nums[i] > nums[next]) {
                        count++;
                        // after rotation, the sorted block starts at next and ends at i
                        min = nums[next];
                        max = nums[i];
                    }
                }

                if (count > 1 || prevMax > min) {
                    valid = false;
                } else {
                    prevMax = max;
                }
            }

            if (valid) ans += k;
        }

        return ans;
    }
}