
import java.util.Arrays;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        Integer[] arr = new Integer[nums.length];

        for (int i = 0; i < nums.length; i++) {
            arr[i] = nums[i];
        }

        Arrays.sort(arr, (a, b) ->
            Integer.compare(Math.abs(a), Math.abs(b)));

        long sum = 0;
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            long square = (long) arr[i] * arr[i];

            if (i < n / 2) {
                sum -= square;
            } else {
                sum += square;
            }
        }

        return sum;
    }
}