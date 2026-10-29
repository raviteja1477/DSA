import java.util.*;

class Solution {
    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }

        int LOG = 17;
        int[][] up = new int[LOG][n];
        int[] depth = new int[n];
        int[] tin = new int[n];
        int[] tout = new int[n];
        int[] timer = {0};

        dfs(0, 0, graph, up, depth, tin, tout, timer);

        Fenwick bit = new Fenwick(n + 2);
        char[] chars = s.toCharArray();

        for (int i = 0; i < n; i++) {
            int mask = 1 << (chars[i] - 'a');
            bit.update(tin[i], mask);
            bit.update(tout[i] + 1, mask);
        }

        List<Boolean> ans = new ArrayList<>();

        for (String q : queries) {
            String[] p = q.split(" ");
            int u = Integer.parseInt(p[1]);

            if (p[0].equals("update")) {
                char c = p[2].charAt(0);

                int change = (1 << (chars[u] - 'a')) ^ (1 << (c - 'a'));

                bit.update(tin[u], change);
                bit.update(tout[u] + 1, change);

                chars[u] = c;
            } else {
                int v = Integer.parseInt(p[2]);
                int lca = lca(u, v, up, depth);

                int mask = bit.query(tin[u])
                         ^ bit.query(tin[v])
                         ^ (1 << (chars[lca] - 'a'));

                ans.add((mask & (mask - 1)) == 0);
            }
        }

        return ans;
    }

    private void dfs(int u, int parent, List<Integer>[] graph,
                     int[][] up, int[] depth, int[] tin,
                     int[] tout, int[] timer) {

        tin[u] = ++timer[0];
        up[0][u] = parent;

        for (int i = 1; i < up.length; i++) {
            up[i][u] = up[i - 1][up[i - 1][u]];
        }

        for (int v : graph[u]) {
            if (v == parent) continue;

            depth[v] = depth[u] + 1;
            dfs(v, u, graph, up, depth, tin, tout, timer);
        }

        tout[u] = timer[0];
    }

    private int lca(int u, int v, int[][] up, int[] depth) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];

        for (int i = 0; i < up.length; i++) {
            if ((diff & (1 << i)) != 0) {
                u = up[i][u];
            }
        }

        if (u == v) return u;

        for (int i = up.length - 1; i >= 0; i--) {
            if (up[i][u] != up[i][v]) {
                u = up[i][u];
                v = up[i][v];
            }
        }

        return up[0][u];
    }

    static class Fenwick {
        int[] tree;

        Fenwick(int n) {
            tree = new int[n + 1];
        }

        void update(int i, int val) {
            for (; i < tree.length; i += i & -i) {
                tree[i] ^= val;
            }
        }

        int query(int i) {
            int res = 0;
            for (; i > 0; i -= i & -i) {
                res ^= tree[i];
            }
            return res;
        }
    }
}