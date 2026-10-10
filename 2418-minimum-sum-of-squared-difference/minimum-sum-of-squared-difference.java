
class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long k = (long) k1 + k2;

        int[] diff = new int[n];
        int max = 0;
        long total = 0;

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            max = Math.max(max, diff[i]);
            total += diff[i];
        }

        if (k >= total) {
            return 0;
        }

        int[] freq = new int[max + 1];

        for (int d : diff) {
            freq[d]++;
        }

        for (int d = max; d > 0 && k > 0; d--) {
            if (freq[d] == 0) {
                continue;
            }

            long count = freq[d];
            long move = Math.min(k, count);

            freq[d] -= move;
            freq[d - 1] += move;
            k -= move;
        }

        long answer = 0;

        for (int d = 1; d <= max; d++) {
            answer += (long) d * d * freq[d];
        }

        return answer;
    }
}