class Solution {
    static final int MOD = 1_000_000_007;

    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] rightDest = new int[m][n];
        int[][] downDest = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rightDest[i][j] = -1;
                downDest[i][j] = -1;
            }
        }

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (grid[i][j] == 0) continue;

                if (i + 1 < m) {
                    if (grid[i + 1][j] == 0) {
                        rightDest[i][j] = (i + 1) * n + j;
                    } else {
                        rightDest[i][j] = downDest[i + 1][j];
                    }
                }

                if (j + 1 < n) {
                    if (grid[i][j + 1] == 0) {
                        downDest[i][j] = i * n + (j + 1);
                    } else {
                        downDest[i][j] = rightDest[i][j + 1];
                    }
                }
            }
        }

        long[][] dp = new long[m][n];
        dp[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dp[i][j] == 0) continue;

                if (j + 1 < n) {
                    int ni = i;
                    int nj = j + 1;

                    if (grid[ni][nj] == 0) {
                        dp[ni][nj] = (dp[ni][nj] + dp[i][j]) % MOD;
                    } else {
                        int dest = rightDest[ni][nj];

                        if (dest != -1) {
                            int x = dest / n;
                            int y = dest % n;
                            dp[x][y] = (dp[x][y] + dp[i][j]) % MOD;
                        }
                    }
                }

                if (i + 1 < m) {
                    int ni = i + 1;
                    int nj = j;

                    if (grid[ni][nj] == 0) {
                        dp[ni][nj] = (dp[ni][nj] + dp[i][j]) % MOD;
                    } else {
                        int dest = downDest[ni][nj];

                        if (dest != -1) {
                            int x = dest / n;
                            int y = dest % n;
                            dp[x][y] = (dp[x][y] + dp[i][j]) % MOD;
                        }
                    }
                }
            }
        }

        return (int) dp[m - 1][n - 1];
    }
}