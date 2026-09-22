class Solution {

    int n, k;
    Node[] tree;

    class Node {
        int product;
        int[] cnt;

        Node() {
            cnt = new int[k];
        }
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {

        this.n = nums.length;
        this.k = k;

        tree = new Node[4 * n];

        build(1, 0, n - 1, nums);

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            int index = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            update(1, 0, n - 1, index, value);

            Node result = query(1, 0, n - 1, start, n - 1);

            ans[i] = result.cnt[x];
        }

        return ans;
    }

    void build(int node, int left, int right, int[] nums) {

        if (left == right) {

            tree[node] = new Node();

            int rem = nums[left] % k;

            tree[node].product = rem;
            tree[node].cnt[rem] = 1;

            return;
        }

        int mid = (left + right) / 2;

        build(node * 2, left, mid, nums);
        build(node * 2 + 1, mid + 1, right, nums);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    Node merge(Node left, Node right) {

        Node result = new Node();

        // Product of the complete segment
        result.product = (left.product * right.product) % k;

        // Prefixes completely inside left
        for (int r = 0; r < k; r++) {
            result.cnt[r] = left.cnt[r];
        }

        // Prefixes that start in left and continue into right
        for (int r = 0; r < k; r++) {

            int newRem = (left.product * r) % k;

            result.cnt[newRem] += right.cnt[r];
        }

        return result;
    }

    void update(int node, int left, int right,
                int index, int value) {

        if (left == right) {

            tree[node] = new Node();

            int rem = value % k;

            tree[node].product = rem;
            tree[node].cnt[rem] = 1;

            return;
        }

        int mid = (left + right) / 2;

        if (index <= mid) {
            update(node * 2, left, mid, index, value);
        } else {
            update(node * 2 + 1, mid + 1, right, index, value);
        }

        tree[node] = merge(tree[node * 2],
                           tree[node * 2 + 1]);
    }

    Node query(int node, int left, int right,
               int ql, int qr) {

        if (ql <= left && right <= qr) {
            return tree[node];
        }

        int mid = (left + right) / 2;

        if (qr <= mid) {
            return query(node * 2, left, mid, ql, qr);
        }

        if (ql > mid) {
            return query(node * 2 + 1, mid + 1, right, ql, qr);
        }

        Node a = query(node * 2, left, mid, ql, qr);
        Node b = query(node * 2 + 1, mid + 1, right, ql, qr);

        return merge(a, b);
    }
}