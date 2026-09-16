import java.util.*;

class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {

        int n = intervals.size();

        int[][] a = new int[n][4];

        for (int i = 0; i < n; i++) {
            a[i][0] = intervals.get(i).get(0);
            a[i][1] = intervals.get(i).get(1);
            a[i][2] = intervals.get(i).get(2);
            a[i][3] = i;
        }

        // Sort by starting point
        Arrays.sort(a, (x, y) -> {
            if (x[0] != y[0])
                return Integer.compare(x[0], y[0]);

            return Integer.compare(x[1], y[1]);
        });

        // next[i] = first interval whose start > current end
        int[] next = new int[n];

        for (int i = 0; i < n; i++) {

            int low = i + 1;
            int high = n - 1;
            int ans = n;

            while (low <= high) {

                int mid = low + (high - low) / 2;

                if (a[mid][0] > a[i][1]) {
                    ans = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

            next[i] = ans;
        }

        /*
         * dp[i][k] = best score starting from i
         * using at most k intervals
         */
        long[][] dp = new long[n + 1][5];

        /*
         * store the actual indices
         */
        List<Integer>[][] path = new ArrayList[n + 1][5];

        for (int i = 0; i <= n; i++) {
            for (int k = 0; k <= 4; k++) {
                path[i][k] = new ArrayList<>();
            }
        }

        for (int i = n - 1; i >= 0; i--) {

            for (int k = 1; k <= 4; k++) {

                // Option 1: skip current interval
                dp[i][k] = dp[i + 1][k];
                path[i][k] = new ArrayList<>(path[i + 1][k]);

                // Option 2: take current interval
                long take = a[i][2];

                List<Integer> takePath = new ArrayList<>();
                takePath.add(a[i][3]);

                if (next[i] < n) {
                    take += dp[next[i]][k - 1];
                    takePath.addAll(path[next[i]][k - 1]);
                }

                // Choose better score
                if (take > dp[i][k]) {

                    dp[i][k] = take;
                    path[i][k] = takePath;

                } else if (take == dp[i][k]) {

                    // Sort before comparing
                    List<Integer> skipPath =
                        new ArrayList<>(path[i][k]);

                    Collections.sort(skipPath);
                    Collections.sort(takePath);

                    if (isSmaller(takePath, skipPath)) {
                        path[i][k] = takePath;
                    }
                }
            }
        }

        List<Integer> answer = path[0][4];

        Collections.sort(answer);

        int[] result = new int[answer.size()];

        for (int i = 0; i < answer.size(); i++) {
            result[i] = answer.get(i);
        }

        return result;
    }

    private boolean isSmaller(List<Integer> a, List<Integer> b) {

        for (int i = 0; i < Math.min(a.size(), b.size()); i++) {

            if (!a.get(i).equals(b.get(i))) {
                return a.get(i) < b.get(i);
            }
        }

        return a.size() < b.size();
    }
}