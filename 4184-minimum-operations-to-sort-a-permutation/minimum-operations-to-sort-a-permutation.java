import java.util.*;

class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;

        if (n == 1) return 0;

        int k = nums[0];
        boolean inc = true, dec = true;

        for (int i = 0; i < n; i++) {
            if (nums[i] != (k + i) % n)
                inc = false;

            if (nums[i] != (k - i + n) % n)
                dec = false;
        }

        if (!inc && !dec) return -1;

        // States [0, n-1]: increasing cyclic order.
        // States [n, 2*n-1]: decreasing cyclic order.
        int start = inc ? k : n + k;
        int target = 0;

        int[] dist = new int[2 * n];
        Arrays.fill(dist, -1);
        Queue<Integer> q = new ArrayDeque<>();

        dist[start] = 0;
        q.add(start);

        while (!q.isEmpty()) {
            int u = q.poll();

            if (u == target)
                return dist[u];

            int offset, v;

            if (u < n) {
                offset = u;

                v = (offset + 1) % n;
                if (dist[v] == -1) {
                    dist[v] = dist[u] + 1;
                    q.add(v);
                }

                v = n + (offset - 1 + n) % n;
                if (dist[v] == -1) {
                    dist[v] = dist[u] + 1;
                    q.add(v);
                }
            } else {
                offset = u - n;

                v = n + (offset - 1 + n) % n;
                if (dist[v] == -1) {
                    dist[v] = dist[u] + 1;
                    q.add(v);
                }

                v = (offset + 1) % n;
                if (dist[v] == -1) {
                    dist[v] = dist[u] + 1;
                    q.add(v);
                }
            }
        }

        return -1;
    }
}