
class Solution {
    static final int MOD = 1000000007;
    int[][] comb = new int[1001][1001];

    public int numOfWays(int[] nums) {
        int n = nums.length;

        
        for (int i = 0; i <= n; i++) {
            comb[i][0] = comb[i][i] = 1;

            for (int j = 1; j < i; j++) {
                comb[i][j] =
                    (comb[i - 1][j - 1] + comb[i - 1][j]) % MOD;
            }
        }

        return (int) ((solve(nums) - 1 + MOD) % MOD);
    }

    private long solve(int[] nums) {
        if (nums.length <= 2) {
            return 1;
        }

        int root = nums[0];
        java.util.List<Integer> left = new java.util.ArrayList<>();
        java.util.List<Integer> right = new java.util.ArrayList<>();

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < root) {
                left.add(nums[i]);
            } else {
                right.add(nums[i]);
            }
        }

        int l = left.size();
        int r = right.size();

        int[] leftArr = left.stream().mapToInt(i -> i).toArray();
        int[] rightArr = right.stream().mapToInt(i -> i).toArray();

        long leftWays = solve(leftArr);
        long rightWays = solve(rightArr);

        return comb[l + r][l] * leftWays % MOD * rightWays % MOD;
    }
}
