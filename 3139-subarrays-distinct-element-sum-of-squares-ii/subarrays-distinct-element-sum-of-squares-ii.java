
class Solution {
    static final long MOD = 1000000007;

    long[] sum, sq, lazy;

    void update(int node, int l, int r, int ql, int qr) {
        if (ql > r || qr < l) return;

        if (ql <= l && r <= qr) {
            lazy[node]++;
            sq[node] += 2 * sum[node] + (r - l + 1);
            sum[node] += r - l + 1;
            return;
        }

        int mid = (l + r) / 2;
        push(node, l, r);

        update(node * 2, l, mid, ql, qr);
        update(node * 2 + 1, mid + 1, r, ql, qr);

        sum[node] = sum[node * 2] + sum[node * 2 + 1];
        sq[node] = sq[node * 2] + sq[node * 2 + 1];
    }

    void push(int node, int l, int r) {
        if (lazy[node] == 0 || l == r) return;

        int mid = (l + r) / 2;
        apply(node * 2, l, mid, lazy[node]);
        apply(node * 2 + 1, mid + 1, r, lazy[node]);
        lazy[node] = 0;
    }

    void apply(int node, int l, int r, long v) {
        sq[node] += 2 * v * sum[node] + v * v * (r - l + 1);
        sum[node] += v * (r - l + 1);
        lazy[node] += v;
    }

    public int sumCounts(int[] nums) {
        int n = nums.length;
        sum = new long[4 * n];
        sq = new long[4 * n];
        lazy = new long[4 * n];

        int[] last = new int[100001];
        long ans = 0;

        for (int i = 0; i < n; i++) {
            int prev = last[nums[i]];
            update(1, 1, n, prev + 1, i + 1);
            ans = (ans + sq[1]) % MOD;
            last[nums[i]] = i + 1;
        }

        return (int) ans;
    }
}
